package be.bstorm.demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode(callSuper = true,of = {"title"}) @ToString(of = {"title","content"})
public class Post extends BaseEntity<Long>{

    @Column(nullable = false,length = 100)
    @Setter
    private String title;

    @Column(nullable = false)
    @Setter
    private String content;

    @Column
    @Setter
    private String image;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @Setter
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    private final Set<User> likers;

    public Post() {
        this.likers = new HashSet<>();
    }

    public Post(String title, String content, String image, User owner) {
        this();
        this.title = title;
        this.content = content;
        this.image = image;
        this.owner = owner;
    }

    public void addLiker(User liker) {
        this.likers.add(liker);
    }

    public void removeLiker(User liker) {
        this.likers.remove(liker);
    }
}
