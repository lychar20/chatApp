package fr.charly.chatApp.controller;


import ch.qos.logback.classic.util.LogbackMDCAdapter;
import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.*;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.service.UserService;
import fr.charly.chatApp.utils.FlashMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
import java.util.Map;

@AllArgsConstructor
@Controller
public class ChatController {

    private CategoryService categoryService;
    private ThreadService threadService;
    private UserService userService;




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

        Chatter chatter =  (Chatter)userService.findByNickname(chatMessage.getSender());
        Category category = categoryService.findBySlug(categorySlug) ;
        userService.saveUser(chatter , category);


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





//    @GetMapping(UrlRoute.URL_FORUM_NAME)
//    public ModelAndView index(
//            @PathVariable String slug,
//            ModelAndView mav
//    ){
//        mav.setViewName("forum/show");
//
//        mav.addObject("categoryChoice", categoryService.findBySlug(slug));
//        return mav;
//    }



    @GetMapping(UrlRoute.URL_FORUM_NAME)
    public ModelAndView create(
            @PathVariable String slug,
            ModelAndView mav) {
        mav.setViewName("forum/show");
        mav.addObject("threadDTO", new ThreadDTO());
        mav.addObject("category", categoryService.findBySlug(slug));
        return mav;
    }

    @PostMapping(UrlRoute.URL_FORUM_NAME)
    public ModelAndView create(
            @PathVariable String slug,
            ModelAndView mav,
            @Valid @ModelAttribute("threadDTO") ThreadDTO threadDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("forum/show");
            return mav;
        }


        Thread thread = threadService.createThread(
                threadDTO,
                categoryService.findBySlug(slug));

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage(
                        "success",
                        "Votre fil a été créé avec succès !"
                ));
        mav.setViewName("redirect:" + UrlRoute.URL_FORUM+ "/" + slug);
        return mav;
    }


}



