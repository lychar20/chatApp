package fr.charly.chatApp.entity;

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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private Date createdAt;

    private Date updatedAt = null;

    @OneToMany
    private List<Comment> responses;

    @ManyToOne
    private Comment commentFrom;

    @ManyToOne
    private Chatter chatter;

    @ManyToOne
    private Thread thread;

    @OneToMany(mappedBy = "comment")
    private List<Reaction> reactions = new ArrayList<>();

}