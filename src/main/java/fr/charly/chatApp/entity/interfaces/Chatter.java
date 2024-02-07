package fr.charly.chatApp.entity.interfaces;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("CHATTER")
public class Chatter extends User {

    private LocalDate birthAt;

    @ManyToMany
    private List<Thread> threads = new ArrayList<>();

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "chatter")
    private List<Comment> comments = new ArrayList<>();


    @OneToMany (mappedBy = "chatter")
    private List<Reaction> reactions = new ArrayList<>();



}
