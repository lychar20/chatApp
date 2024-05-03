package fr.charly.chatApp.entity;

import fr.charly.chatApp.entity.enumo.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ChatRoomMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chatId;
    private String chatterSenderId;
    private String chatterReceiverId;
    private String content;
    private Date timestamp;


//    private Map<Category, List<User>> userInChatCategory;


}
