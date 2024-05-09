package com.example.ykhaledcustomersupport.site;

import com.example.ykhaledcustomersupport.entities.UserPrincipal;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.security.Principal;
import java.util.logging.Logger;

@Controller
public class AuthenticationController {

    private static final Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @Inject
    AuthenticationService authenticationService;

    @GetMapping("logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("login", true, false);
    }

    @GetMapping("login")
    public ModelAndView loginForm(Model model, HttpSession session) {
        if (UserPrincipal.getPrincipal(session) != null) {
            return new ModelAndView(new RedirectView("/ticket/list", true, false));
        }
        model.addAttribute("loginFailed", false);
        return new ModelAndView("login", "loginForm", new LoginForm());
    }

    @PostMapping("login")
    public ModelAndView loginCheck(@ModelAttribute("loginForm") LoginForm form,
                                   Model model,
                                   HttpSession session,
                                   HttpServletRequest request) {
        // already logged in
        if (UserPrincipal.getPrincipal(session) != null) {
            return new ModelAndView(new RedirectView("/ticket/list", true, false));
        }

        Principal principal;
        try {
            principal = authenticationService.authenticate(form.getUsername(), form.getPassword());
        } catch (ConstraintViolationException e) {
            logger.severe("ConstraintViolationException occurred: " + e.getMessage());
            return new ModelAndView("login");
        }
        if (principal == null) {
            logger.info("Login failed for user: " + form.getUsername());
            form.setPassword(null);
            model.addAttribute("loginFailed", true);
            model.addAttribute("loginForm", form);
            return new ModelAndView("login");
        }
        // We are logged in successfully
        UserPrincipal.setPrincipal(session, principal);
        request.changeSessionId();
        logger.info("User " + form.getUsername() + " logged in successfully.");
        return new ModelAndView(new RedirectView("/ticket/list", true, false));
    }

    public static class LoginForm {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
