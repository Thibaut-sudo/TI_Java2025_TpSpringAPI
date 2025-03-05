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
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;


public Warehouse(){

}
    public Warehouse( Stock stock,User user) {
        this.id = id;
        this.nom = nom;
        this.stock = stock;

    }
}
