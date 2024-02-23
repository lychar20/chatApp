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

    @MessageMapping("/chat.sendMessage/{categorySlug")
    @SendTo("/{categorySlug}/public")
    public ChatMessage sendMessage(
            @DestinationVariable String categorySlug,
            @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser/{categorySlug}")
    @SendTo("/{categorySlug}/public")
    public ChatMessage addUser(
            @DestinationVariable String categorySlug,
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
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
