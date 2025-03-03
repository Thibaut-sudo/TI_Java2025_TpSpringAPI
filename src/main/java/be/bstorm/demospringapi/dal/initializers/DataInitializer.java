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

        if (userRepository.count() == 0) {

            String password = passwordEncoder.encode("Test1234=");

            List<User> users = List.of(
                    new User(
                            "admin@admin.be",
                            password,
                            "admin",
                            "admin",
                            LocalDate.now(),
                            UserRole.ADMIN,
                            null),
                    new User(
                            "user@user.be",
                            password,
                            "user",
                            "user",
                            LocalDate.now(),
                            UserRole.USER,
                            null),
                    new User(
                            "modo@modo.be",
                            password,
                            "modo",
                            "modo",
                            LocalDate.now(),
                            UserRole.MODERATOR,
                            null)
                    );
            userRepository.saveAll(users);
        }
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                    new Product("Banndana","Fruit", "https://www.google.com", new BigDecimal(10))
            );
            productRepository.saveAll(products);
        }
        if (stockRepository.count() == 0) {
            List<Stock> stocks = List.of(
                    new Stock(1000, new Product("Banndana","Fruit", "https://www.google.com", new BigDecimal(10)))

            );
            stockRepository.saveAll(stocks);
        }

    }
}
