package fr.charly.chatApp.controller;


import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.ChatMessage;
import fr.charly.chatApp.entity.ChatNotification;
import fr.charly.chatApp.entity.ChatRoomMessage;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.ChatMessageRoomService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.utils.FlashMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
public class ChatRoomController {

    private SimpMessagingTemplate messagingTemplate;
    private ChatMessageRoomService chatMessageRoomService;




    @MessageMapping("/chat")
    public void processMessage(@Payload ChatRoomMessage chatRoomMessage) {
        ChatRoomMessage savedMsg = chatMessageRoomService.save(chatRoomMessage);
        messagingTemplate.convertAndSendToUser(
                chatRoomMessage.getChatterReceiverId(), "/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getChatterSenderId(),
                        savedMsg.getChatterReceiverId(),
                        savedMsg.getContent()
                )
        );
    }


    @GetMapping("/messages/{chatterSenderId}/{chatterReceiverId}")
    public ModelAndView show(@PathVariable String chatterSenderId,
                             @PathVariable String chatterReceiverId,
                             ModelAndView mav)
    {
        mav.setViewName("chatMessage/index");

        mav.addObject("chatMessageRoom", chatMessageRoomService.findChatMessages(chatterSenderId, chatterReceiverId));
        return mav;
    }


}



