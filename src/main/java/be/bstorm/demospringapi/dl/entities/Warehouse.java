package be.bstorm.demospringapi.dl.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@AllArgsConstructor
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;


    @Column(nullable = false,length = 250)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = true)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

public Warehouse(){

}
    public Warehouse( String nom,Stock stock,User user) {
        this.nom = nom;
        this.stock = stock;
        this.user = user;

    }
}
