package fr.charly.chatApp.entity;

import fr.charly.chatApp.entity.enumo.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
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

    private MessageType type;

}
