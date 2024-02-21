package fr.charly.chatApp.controller;

import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.CommentService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class CommentController {

    private CommentService commentService;

    @MessageMapping(UrlRoute.URL_COMMENT_MESSAGE_MAPPING )
    @SendTo(UrlRoute.URL_COMMENT_SEND_TO)
    public ModelAndView index(ModelAndView mav,
                              Principal principal
                              ){
        mav.setViewName("comment/index");
        mav.addObject("comment", commentService.addUser(new Comment(), SimpMessageHeaderAccessor.create()));

        return mav;
    }

}
