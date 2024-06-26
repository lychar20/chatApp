package fr.charly.chatApp.configuration;

import fr.charly.chatApp.entity.ChatMessage;
import fr.charly.chatApp.entity.Chatter;
import fr.charly.chatApp.entity.enumo.MessageType;
import fr.charly.chatApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private UserService userService;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        // rajouter la catégories pour séparer les chats
        String category = (String) headerAccessor.getSessionAttributes().get("category");

        if (username != null && category != null) {
            log.info("user disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messagingTemplate.convertAndSend("/topic/public/"+category, chatMessage);

            Chatter chatter = (Chatter)userService.findByNickname(username);
            userService.saveUser(chatter, null);
            // on mets la catégorie à null pour dire que le user est dans aucun chat
        }
    }

}
