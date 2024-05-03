package fr.charly.chatApp.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class ChatNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private String chatterSenderId;

    @ManyToOne
    private String chatterReceiverId;

    private String content;

}
