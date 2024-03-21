package fr.charly.chatApp.controller;

import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.utils.FlashMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {

private CategoryService categoryService;
private ThreadService threadService;


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


//    @GetMapping(UrlRoute.URL_FORUM_NAME)
//    public ModelAndView show(ModelAndView mav) {
//        mav.setViewName("forum/show");
//        mav.addObject("threadDTO", new ThreadDTO());
//        mav.addObject("categories", categoryService.findAll());
//        return mav;
//    }


//    @PostMapping(UrlRoute.URL_FORUM_NAME)
//    public ModelAndView show(
//            ModelAndView mav,
//            @Valid @ModelAttribute("threadDTO") ThreadDTO threadDTO,
//            BindingResult result,
//            RedirectAttributes redirectAttributes
//    ) {
//        if (result.hasErrors()) {
//            mav.setViewName("forum/show"); //"vue" avant mais wrong
//            return mav;
//        }
//
//
//        Thread thread = threadService.createThread(threadDTO);
//
//        redirectAttributes.addFlashAttribute(
//                "flashMessage",
//                new FlashMessage(
//                        "success",
//                        "Votre fil a été créé avec succès !"
//                ));
//        mav.setViewName("redirect:" + UrlRoute.URL_FORUM+ "/" + thread.getSlug());
//        return mav;
//    }


}
