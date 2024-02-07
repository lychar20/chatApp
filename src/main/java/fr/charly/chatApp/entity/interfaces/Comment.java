package fr.charly.chatApp.entity.interfaces;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
