package fr.charly.chatApp.entity;

import fr.charly.chatApp.entity.enumo.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;

    @ManyToOne
    private String chatterSenderId;

    @ManyToOne
    private String chatterReceiverId;

}
