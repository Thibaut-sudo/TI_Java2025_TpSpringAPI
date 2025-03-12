package be.bstorm.demospringapi.bll.services.impls;

import be.bstorm.demospringapi.api.models.security.dtos.ProductDTO;
import be.bstorm.demospringapi.api.models.security.forms.ProductForm;
import be.bstorm.demospringapi.bll.services.ProductService;
import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.il.utils.request.SearchParam;
import be.bstorm.demospringapi.il.utils.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository ;

    @Override
    public Page<Product> getProduct(List<SearchParam<Product>> searchParams, Pageable pageable) {

        if (searchParams.isEmpty()){
            return productRepository.findAll(pageable);
        }
        return productRepository.findAll(
                Specification.allOf(
                        searchParams.stream()
                                .map(SearchSpecification::search)
                                .toList()
                ),
                pageable
        );
    }

    @Override
    public List<ProductDTO> foundAll() {

        //voir en bas autre version
        return productRepository.findAll().stream()
                .map(ProductDTO::fromProduct)
                .toList();
    }

    @Override
    public ProductDTO foundOneDetails(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        return ProductDTO.fromProduct(product);
    }


    @Override
    public void insert(ProductForm productForm) {
        Product product = productForm.toProduct();
        productRepository.save(product);
    }
    @Override
    public ProductDTO update(Long id, ProductForm productForm) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        product.setNom(productForm.nom());
        product.setDescription(productForm.nom());
        product.setImageUrl(productForm.imageUrl());
        product.setPrix(productForm.prix());

        productRepository.save(product);

        return ProductDTO.fromProduct(product);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }




    @Override
    public List<Product> researchProducts(String query, String site) throws IOException {
        List<Product> productList = new ArrayList<>();
        String searchQuery = query.toLowerCase();
        int page = 1;

        while (true) {
            String pageUrl = site + "/catalogue/page-" + page + ".html";
            System.out.println("Scraping: " + pageUrl);

            Document doc;
            try {
                doc = Jsoup.connect(pageUrl)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                        .timeout(10 * 1000)
                        .get();
            } catch (IOException e) {
                System.out.println("No more pages. Stopping search.");
                break;  // Stop when a 404 error occurs
            }

            Elements books = doc.select("article.product_pod");
            if (books.isEmpty()) break; // Stop if no books found (last page reached)

            for (Element book : books) {
                String title = book.select("h3 a").attr("title");
                String priceText = book.select("p.price_color").text().replace("£", "").replace(",", ".");
                String description = book.select("p.product_description").text();

                String link = site + "/catalogue/" + book.select("h3 a").attr("href");

                BigDecimal price;
                try {
                    price = new BigDecimal(priceText);
                } catch (NumberFormatException e) {
                    price = BigDecimal.ZERO;
                }

                if (title.toLowerCase().contains(searchQuery.toLowerCase())) {
                    productList.add(new Product( title , description , price ));
                    System.out.println(doc.html());
                }

            }

            page++; // Move to the next page
        }

        return productList;
    }







    // Récupère les produits depuis un site web via web scraping
    /*public List<Product> researchProducts(String query, String site) throws IOException {
        List<Product> productList = new ArrayList<>();

        // Vérifier et corriger l'URL du site
        String fixedSite = fixSiteUrl(site);

        // Construire l'URL de recherche
        String searchUrl = fixedSite + "/s?k=" + query.replace(" ", "+");

        System.out.println("Recherche sur : " + searchUrl);

        // Charger la page avec Jsoup en simulant un vrai navigateur
        Document doc = Jsoup.connect(searchUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .header("Accept-Language", "fr-FR,fr;q=0.9")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .header("Referer", "https://www.google.com/")
                .timeout(10 * 1000)  // Timeout de 10 secondes
                .get();

        // Vérification si Amazon a redirigé vers un CAPTCHA
        if (doc.title().toLowerCase().contains("captcha") || doc.text().contains("Saisissez les caractères que vous voyez ci-dessous")) {
            System.out.println("Amazon a détecté un bot. Impossible de scraper.");
            return productList; // Retourne une liste vide
        }

        // Sélectionner les produits affichés sur la page
        Elements products = doc.select(".s-result-item"); // Modifier en fonction du site

        for (Element productElement : products) {
            String name = productElement.select(".a-text-normal").text();
            String price = productElement.select(".a-price-whole").text();
            String link = productElement.select("a.a-link-normal").attr("href");

            // Ajouter le produit à la liste
            productList.add(new Product(name, price, fixedSite + link));
        }

        return productList;
    }

    // Corrige l'URL du site cible
    private String fixSiteUrl(String site) {
        if (!site.startsWith("http")) {
            site = "https://" + site; // Ajouter https si absent
        }
        return site;
    }
*/
}