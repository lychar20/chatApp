package fr.charly.chatApp.entity;

import fr.charly.chatApp.entity.interfaces.NomenclatureInterface;
import fr.charly.chatApp.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Category implements SluggerInterface, NomenclatureInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String slug;

    @ManyToOne
    private Category parent;

    @OneToMany (mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @ManyToMany
    private List<Favorites> favorites = new ArrayList<>();

    @Override
    public String getField() {
        return name;
    }
}
