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
import fr.charly.chatApp.utils.FlashMessageBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

private FlashMessageBuilder flashMessageBuilder;



    @GetMapping(UrlRoute.URL_FORUM_NAME_COMMENTS)
    public ModelAndView create(
            @PathVariable String threadSlug,
            Principal principal,
            ModelAndView mav,
            @ModelAttribute("flashMessage") FlashMessage flashMessage,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
            ) {
        mav.setViewName("comments/index");
        Thread thread = threadService.findBySlug(threadSlug);
        mav.addObject("thread", thread);
        mav.addObject("commentDTO", new CommentDTO());
        mav.addObject("pageComments", commentService.getPageCommentOrdered(principal, thread, pageable));
        return mav;
    }

    @PostMapping(value = {
            UrlRoute.URL_FORUM_NAME_COMMENTS,
            UrlRoute.URL_FORUM_NAME_COMMENTS + "/{id}"
    }) // l id est optionelle, le commentaire peux etre une reponse ou non, donc on mets 2 routes pour créer un commentaire
    public ModelAndView create(
            @PathVariable String threadSlug,
            @PathVariable String slug,
            @PathVariable(required = false) Long id, // l id est optionelle

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
                principal.getName(),
                id != null ? id : -1 // si il y a une id on lui mets l id sinon on lui mets -1 pour qu il ne trouve rien comme commentaire parent (findById avec null ne marche pas)
        );

        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage(
                        "success",
                        "Votre message a été créé avec succès !"
                ));

        mav.setViewName("redirect:" + UrlRoute.URL_FORUM + "/" + slug + "/" + threadSlug);
        return mav;
    }



    @GetMapping(UrlRoute.URL_FORUM_COMMENT_MODERATE_PATH)
    public ModelAndView moderate(
            @PathVariable Long id,
            @PathVariable String threadSlug,
            @PathVariable String slug,
            ModelAndView modelAndView,
            RedirectAttributes redirectAttributes,
            Principal principal
    ) {
        commentService.moderateComment(principal.getName(), id);
        FlashMessage flashMessage = flashMessageBuilder.createSuccessFlashMessage("Le commentaire a bien été modéré !");

        redirectAttributes.addFlashAttribute("flashMessage", flashMessage);
        modelAndView.setViewName("redirect:" + UrlRoute.URL_FORUM + "/" + slug +  "/" + threadSlug);
        return modelAndView;
    }



}
