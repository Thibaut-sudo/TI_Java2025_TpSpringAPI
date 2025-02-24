package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true) @ToString
public class Comment extends BaseEntity<Long> {

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private User user;
}
