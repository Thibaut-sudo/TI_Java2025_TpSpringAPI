package be.bstorm.demospringapi.dal.initializers;

import be.bstorm.demospringapi.dal.repositories.ProductRepository;
import be.bstorm.demospringapi.dal.repositories.StockRepository;
import be.bstorm.demospringapi.dal.repositories.UserRepository;
import be.bstorm.demospringapi.dl.entities.Product;
import be.bstorm.demospringapi.dl.entities.Stock;
import be.bstorm.demospringapi.dl.entities.User;
import be.bstorm.demospringapi.dl.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Utilisateur();
        Produit();
        Stock();

    }

    private void Stock() {
        if (stockRepository.count() == 0) {
            List<Stock> stocks = List.of(
                    new Stock(1000, productRepository.findById(1L).orElse(null)),
                    new Stock(800, productRepository.findById(2L).orElse(null)),
                    new Stock(1200, productRepository.findById(3L).orElse(null)),
                    new Stock(500, productRepository.findById(4L).orElse(null)),
                    new Stock(950, productRepository.findById(5L).orElse(null)),
                    new Stock(300, productRepository.findById(6L).orElse(null)),
                    new Stock(400, productRepository.findById(7L).orElse(null)),
                    new Stock(600, productRepository.findById(8L).orElse(null)),
                    new Stock(700, productRepository.findById(9L).orElse(null)),
                    new Stock(850, productRepository.findById(10L).orElse(null)),
                    new Stock(1100, productRepository.findById(11L).orElse(null)),
                    new Stock(750, productRepository.findById(12L).orElse(null)),
                    new Stock(920, productRepository.findById(13L).orElse(null)),
                    new Stock(430, productRepository.findById(14L).orElse(null)),
                    new Stock(310, productRepository.findById(15L).orElse(null)),
                    new Stock(580, productRepository.findById(16L).orElse(null)),
                    new Stock(670, productRepository.findById(17L).orElse(null)),
                    new Stock(250, productRepository.findById(18L).orElse(null)),
                    new Stock(150, productRepository.findById(19L).orElse(null)),
                    new Stock (200,productRepository.findById(20L).orElse(null))

            );
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
                    new User(
                            "admin@admin.be", password, "admin", "admin", LocalDate.now(), UserRole.ADMIN, null),
                    new User(
                            "user@user.be", password, "user", "user", LocalDate.now(), UserRole.USER, null),
                    new User(
                            "modo@modo.be", password, "modo", "modo", LocalDate.now(), UserRole.MODERATOR, null)
                    );
            userRepository.saveAll(users);
        }
    }
}
