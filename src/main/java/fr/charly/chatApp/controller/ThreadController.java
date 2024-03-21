package fr.charly.chatApp.controller;

import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.utils.FlashMessage;
import fr.charly.chatApp.utils.FlashMessageBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ThreadController {

private ThreadService threadService;
private CategoryService categoryService;



    @GetMapping(UrlRoute.URL_FORUM_NEW)
    public ModelAndView create(ModelAndView mav) {
        mav.setViewName("forum/show");
        mav.addObject("threadDTO", new ThreadDTO());
        mav.addObject("categories", categoryService.findAll());
        return mav;
    }

    @PostMapping(UrlRoute.URL_FORUM_NEW)
    public ModelAndView create(
                            ModelAndView mav,
                            @Valid @ModelAttribute("threadDTO") ThreadDTO threadDTO,
                            BindingResult result,
                            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("forum/show"); //"vue" avant mais wrong
            return mav;
        }


        Thread thread = threadService.createThread(threadDTO);

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage(
                        "success",
                        "Votre fil a été créé avec succès !"
                ));
        mav.setViewName("redirect:" + UrlRoute.URL_FORUM+ "/" + thread.getSlug());
        return mav;
    }






}
