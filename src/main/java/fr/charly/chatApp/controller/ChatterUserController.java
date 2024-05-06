package fr.charly.chatApp.controller;


import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.*;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.ChatterUserService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.service.UserService;
import fr.charly.chatApp.utils.FlashMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
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
public class ChatterUserController {




    private ChatterUserService chatterUserService;
    private UserService userService;
    private CategoryService categoryService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public Chatter addUser(
            @Payload Chatter chatter
    ) {
        chatterUserService.saveUser(chatter);
        return chatter;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public Chatter disconnectUser(
            @Payload Chatter chatter
    ) {
        chatterUserService.disconnect(chatter);
        return chatter;
    }

    @GetMapping(UrlRoute.URL_USERS_CHAT_CATEGORY)
    public ResponseEntity<List<User>> findConnectedUsers(
            @PathVariable String slug
            ) {
        return ResponseEntity.ok(categoryService.findBySlug(slug));
    }



}



