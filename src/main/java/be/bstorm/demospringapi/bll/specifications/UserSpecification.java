package be.bstorm.demospringapi.bll.specifications;

import be.bstorm.demospringapi.dl.entities.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class UserSpecification {

    public static Specification<User> getByFirstName(String firstName) {
        return (root, query, cb) -> cb.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<User> getByLastName(String lastName) {
        return (root, query, cb) -> cb.like(root.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<User> getByAge(int ageMin, int ageMax) {

        return (root, query, cb) -> {
            LocalDate now = LocalDate.now();

            LocalDate startDate = now.minusYears(ageMax).plusDays(1);
            LocalDate endDate = now.minusYears(ageMin);

            return cb.between(cb.literal(now),startDate,endDate);
        };
    }
}
