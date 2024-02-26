package fr.charly.chatApp.controller;


import fr.charly.chatApp.entity.ChatMessage;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class ChatController {

    private CategoryService categoryService;


    // Rajouter les Slugs pour avoir la catégories
    @MessageMapping("/chat.sendMessage/{categorySlug}")
    @SendTo("/topic/public/{categorySlug}")
    public ChatMessage sendMessage(
            @DestinationVariable String categorySlug,
            @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    // Rajouter le slug pour avoir la category
    // le mettre dans le header pour pouvoir le passer à l'event de deco
    // pour séparer les chats
    @MessageMapping("/chat.addUser/{categorySlug}")
    @SendTo("/topic/public/{categorySlug}")
    public ChatMessage addUser(
            @DestinationVariable String categorySlug,
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("category", categorySlug);
        return chatMessage;
    }


    @GetMapping(UrlRoute.URL_SALON_NAME)
    public ModelAndView show(
            @PathVariable String slug,
            ModelAndView mav
    ){
        mav.setViewName("chatMessage/index");

        mav.addObject("categoryChoice", categoryService.findBySlug(slug));
        return mav;
    }
}



