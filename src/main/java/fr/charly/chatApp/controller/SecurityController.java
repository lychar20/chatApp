package fr.charly.chatApp.controller;

import fr.charly.chatApp.DTO.RegisterPostDTO;
import fr.charly.chatApp.mapping.UrlRoute;
import fr.charly.chatApp.service.UserService;
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

@Controller
@AllArgsConstructor
public class SecurityController {

    private UserService userService;

    @GetMapping(UrlRoute.URL_LOGIN)
    public ModelAndView login(ModelAndView mav, String error) {
        if (error != null) {
            mav.addObject("error", "Identifiants sont incorrects !");
        }
        mav.setViewName("security/login");
        return mav;
    }


    @GetMapping(UrlRoute.URL_REGISTER)
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("security/register");
        mav.addObject("registerDto", new RegisterPostDTO());
        return mav;
    }


    @PostMapping(UrlRoute.URL_REGISTER)
    public ModelAndView login(
            ModelAndView mav,
            @Valid @ModelAttribute("registerDto") RegisterPostDTO registerDto,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            mav.setViewName("security/register");
            return mav;
        }
        if (userService.create(registerDto) != null) {
            redirectAttributes.addFlashAttribute(
                    "flashMessage",
                    new FlashMessage(
                            "success",
                            "Votre compte a été créé avec succès !"
                    )
            );
        }
        mav.setViewName("redirect:/login");
        return mav;
    }

}
