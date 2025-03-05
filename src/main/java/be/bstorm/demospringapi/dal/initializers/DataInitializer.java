package be.bstorm.demospringapi.dal.initializers;

import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dal.repositories.StockRepository;
import be.bstorm.demospringapi.dal.repositories.UserRepository;
import be.bstorm.demospringapi.dal.repositories.WarehouseRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.dl.entities.Warehouse;
import be.bstorm.demospringapi.dl.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public void run(String... args) throws Exception {

        Utilisateur();
        Produit();
        Stock();
        Warehouse();

    }

    private void Stock() {
        if (stockRepository.count() == 0) {

            // Récupération des utilisateurs ayant le rôle COMMERCIAL
            List<User> commerciaux = userRepository.findAll()
                    .stream()
                    .filter(user -> user.getRole() == UserRole.COMERCIAL)
                    .toList();

            if (commerciaux.isEmpty()) {
                System.out.println("Erreur : Aucun commercial trouvé.");
                return;
            }

            // Récupération de tous les produits
            List<Product> produits = productRepository.findAll();

            if (produits.isEmpty()) {
                System.out.println("Erreur : Aucun produit trouvé.");
                return;
            }

            // Création des stocks et attribution aux commerciaux de manière équilibrée
            List<Stock> stocks = new ArrayList<>();
            int commercialIndex = 0;

            for (Product produit : produits) {
                User commercialAttribue = commerciaux.get(commercialIndex);
                stocks.add(new Stock((int) (Math.random() * 100) + 1, produit, commercialAttribue));
                //randon entre 1 et 100


                // Passer au commercial suivant en boucle
                commercialIndex = (commercialIndex + 1) % commerciaux.size();
            }

            // Sauvegarde des stocks
            stockRepository.saveAll(stocks);
        }
    }


    private void Produit() {
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product("Banndana","Fruit", "https://www.google.com", new BigDecimal(10)),
                    new Product("Banana", "Fruit", "https://www.google.com", new BigDecimal(10)),
                    new Product("Apple", "Fruit", "https://www.google.com", new BigDecimal(12)),
                    new Product("Orange", "Fruit", "https://www.google.com", new BigDecimal(8)),
                    new Product("Mango", "Fruit", "https://www.google.com", new BigDecimal(15)),
                    new Product("Strawberry", "Fruit", "https://www.google.com", new BigDecimal(20)),
                    new Product("Laptop", "Electronics", "https://www.google.com", new BigDecimal(1200)),
                    new Product("Smartphone", "Electronics", "https://www.google.com", new BigDecimal(900)),
                    new Product("Headphones", "Electronics", "https://www.google.com", new BigDecimal(150)),
                    new Product("Smartwatch", "Electronics", "https://www.google.com", new BigDecimal(250)),
                    new Product("Bluetooth Speaker", "Electronics", "https://www.google.com", new BigDecimal(100)),
                    new Product("T-shirt", "Clothing", "https://www.google.com", new BigDecimal(25)),
                    new Product("Jeans", "Clothing", "https://www.google.com", new BigDecimal(50)),
                    new Product("Sneakers", "Clothing", "https://www.google.com", new BigDecimal(80)),
                    new Product("Backpack", "Accessories", "https://www.google.com", new BigDecimal(45)),
                    new Product("Sunglasses", "Accessories", "https://www.google.com", new BigDecimal(60)),
                    new Product("Gaming Mouse", "Accessories", "https://www.google.com", new BigDecimal(35)),
                    new Product("Wireless Keyboard", "Accessories", "https://www.google.com", new BigDecimal(70)),
                    new Product("Desk Chair", "Furniture", "https://www.google.com", new BigDecimal(200)),
                    new Product("Standing Desk", "Furniture", "https://www.google.com", new BigDecimal(500))

            );
            productRepository.saveAll(products);
        }
    }

    private void Utilisateur() {
        if (userRepository.count() == 0) {

            String password = passwordEncoder.encode("Test1234=");

            List<User> users = List.of(
                    new User("admin@admin.be", password, "admin", "admin", LocalDate.now(), UserRole.ADMIN, null),
                    new User("user@user.be", password, "user", "user", LocalDate.now(), UserRole.USER, null),
                    new User("modo@modo.be", password, "modo", "modo", LocalDate.now(), UserRole.COMERCIAL, null),
                    new User("com1@company.be", password, "Alice", "Martin", LocalDate.now(), UserRole.COMERCIAL, null),
                    new User("com2@company.be", password, "Bob", "Durand", LocalDate.now(), UserRole.COMERCIAL, null),
                    new User("com3@company.be", password, "Charlie", "Dubois", LocalDate.now(), UserRole.COMERCIAL, null),
                    new User("com4@company.be", password, "Diane", "Leroy", LocalDate.now(), UserRole.COMERCIAL, null)
            );
            userRepository.saveAll(users);
        }
    }
    private void Warehouse(){
        if(warehouseRepository.count() == 0 ){
            List<Warehouse>warehouses = List.of(
                    new Warehouse("Warehouse1",stockRepository.findById(1L).orElseThrow(()->new RuntimeException("User not found")),userRepository.findById(1L).orElseThrow(()->new RuntimeException("User not found")))
            );
            warehouseRepository.saveAll(warehouses);
        }

    }

}
