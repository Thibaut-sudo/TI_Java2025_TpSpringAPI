package be.bstorm.demospringapi.dl.entities;

import be.bstorm.demospringapi.dl.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user_")
@Getter
@EqualsAndHashCode(callSuper = true, of = {"email"})
@ToString(of = {"email", "role"})
public class User extends BaseEntity<Long> implements UserDetails {

    @Column(unique = true, nullable = false, length = 150)
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(length = 123, nullable = false)
    @Setter
    private String firstName;

    @Column(length = 80, nullable = false)
    @Setter
    private String lastName;

    @Column
    @Setter
    private LocalDate birthDate;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @Setter
    private UserRole role;

    @Column
    @Setter
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter
    private List<Stock> stockPackages = new ArrayList<>();

    public User() {}

    public User(String email, String password, String firstName, String lastName, LocalDate birthDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public User(String email, String password, String firstName, String lastName, LocalDate birthDate, UserRole role, String image) {
        this(email, password, firstName, lastName, birthDate);
        this.role = role;
        this.image = image;
    }

    public void addStockPackage(Stock stockPackage) {
        this.stockPackages.add(stockPackage);
        stockPackage.setUser(this);
    }

    public void removeStockPackage(Stock stockPackage) {
        this.stockPackages.remove(stockPackage);
        stockPackage.setUser(null);
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
