package fr.charly.chatApp.controller;

import fr.charly.chatApp.DTO.CommentDTO;
import fr.charly.chatApp.DTO.ThreadDTO;
import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.CommentService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.utils.FlashMessage;
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
public class CommentsController {

private ThreadService threadService;
private CategoryService categoryService;
private CommentService commentService;



    @GetMapping(UrlRoute.URL_FORUM_NAME_COMMENTS)
    public ModelAndView create(
            @PathVariable String threadSlug,
            ModelAndView mav) {
        mav.setViewName("comments/index");
        mav.addObject("commentDTO", new CommentDTO());
        mav.addObject("thread", threadService.findBySlug(threadSlug));
        return mav;
    }

    @PostMapping(UrlRoute.URL_FORUM_NAME_COMMENTS)
    public ModelAndView create(
            @PathVariable String threadSlug,
            ModelAndView mav,
            Principal principal,
            @Valid @ModelAttribute("commentDTO") CommentDTO commentDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("comments/index");
            return mav;
        }


        Comment comment = commentService.createComment(
                commentDTO,
                threadService.findBySlug(threadSlug),
                principal.getName()
                );

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage(
                        "success",
                        "Votre message a été créé avec succès !"
                ));
        mav.setViewName("redirect:" + UrlRoute.URL_FORUM_NAME+ "/" + threadSlug);
        return mav;
    }






}
