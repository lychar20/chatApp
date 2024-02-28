package fr.charly.chatApp.controller;

import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.utils.FlashMessage;
import fr.charly.chatApp.utils.FlashMessageBuilder;
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

    @PostMapping(UrlRoute.URL_FORUM_NEW)
    public ModelAndView create(
                              ModelAndView mav,
                                RedirectAttributes redirectAttributes,
                              @ModelAttribute("threadDTO") ThreadDTO threadDTO
    ) {

        if (threadService.createThread(threadDTO) != null) {
        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage(
                        "success",
                        "Votre fil a été créé avec succès !"
                ));
        }

        mav.setViewName("redirect:" + UrlRoute.URL_FORUM+ "/" + threadService.createThread(threadDTO));
        return mav;
    }


}
