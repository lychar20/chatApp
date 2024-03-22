package fr.charly.chatApp.entity;

import fr.charly.chatApp.entity.interfaces.NomenclatureInterface;
import fr.charly.chatApp.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Thread implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String title;

    private String slug;

    private Date createdAt;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(mappedBy = "thread")
    private List<Comment> comments = new ArrayList<>();


    @Override
    public String getField() {
        return title;
    }
}
