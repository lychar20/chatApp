package fr.charly.chatApp.controller;

import fr.charly.chatApp.DTO.CommentDTO;
import fr.charly.chatApp.entity.Comment;
import fr.charly.chatApp.entity.Thread;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.CategoryService;
import fr.charly.chatApp.service.CommentService;
import fr.charly.chatApp.service.ThreadService;
import fr.charly.chatApp.utils.FlashMessage;
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
public class ResponseController {

private CategoryService categoryService;
private CommentService commentService;
private ThreadService threadService;




    @GetMapping(UrlRoute.URL_RESPONSES_COMMENT)
    public ModelAndView show(
            @PathVariable String threadSlug,
            ModelAndView mav,
            Principal principal,
            @ModelAttribute("flashMessage") FlashMessage flashMessage,
            @PageableDefault(
                    size = 12, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {

        Thread thread = threadService.findBySlug(threadSlug);
        mav.addObject("thread", thread);
        mav.addObject("commentDTO", new CommentDTO());
        mav.addObject("pageComments", commentService.getPageCommentOrdered(principal, thread, pageable));
        return mav;
    }






}
