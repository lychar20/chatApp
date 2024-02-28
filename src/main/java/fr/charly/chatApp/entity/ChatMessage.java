package fr.charly.chatApp.entity;

import fr.charly.chatApp.entity.enumo.MessageType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;



//    private Map<Category, List<User>> userInChatCategory;


}
