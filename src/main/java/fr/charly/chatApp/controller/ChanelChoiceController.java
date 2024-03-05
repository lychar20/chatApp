package fr.charly.chatApp.controller;

import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.utils.FlashMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ChanelChoiceController {

private CategoryService categoryService;

    @GetMapping(UrlRoute.URL_SALON_CHAT)
    public ModelAndView chat(
            ModelAndView mav

    ) {
        mav.addObject("categoryChoice", categoryService.findAll());
        mav.setViewName("chatChoice");
        return mav;
    }



    @GetMapping(UrlRoute.URL_FORUM)
    public ModelAndView forum(
            ModelAndView mav

    ) {
        mav.addObject("categoryChoice", categoryService.findAll());
        mav.setViewName("forumChoice");
        return mav;
    }



}
