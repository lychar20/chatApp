package fr.charly.chatApp.controller;

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
public class HomeController {

private CategoryService categoryService;

    @GetMapping
    public ModelAndView index(
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("flashMessage") FlashMessage flashMessage

    ) {
        mav.addObject("categoryChoice", categoryService.findAll());
        //mav.addObject("flashMessage", flashMessage);
        mav.setViewName("index");
        return mav;
    }



}
