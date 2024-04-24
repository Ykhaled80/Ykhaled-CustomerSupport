package com.example.ykhaledcustomersupport.site;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class AuthenticationController {
    private static final Map<String, String> userDB = new Hashtable<>();

    static {
        userDB.put("youssef", "hello123");
        userDB.put("user", "password123");
        userDB.put("admin", "access123");
    }

    @GetMapping("logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("login", true, false);
    }

    @GetMapping("login")
    public ModelAndView loginForm(Model model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            // Invalidate the old session
            session.invalidate();
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
        if (session.getAttribute("username") != null) {
            return new ModelAndView(new RedirectView("/ticket/list", true, false));
        }

        String username = form.getUsername();
        String password = form.getPassword();

        String storedPassword = userDB.get(username.toLowerCase()); // Convert username to lowercase

        if (storedPassword == null || !password.equals(storedPassword)) {
            model.addAttribute("loginFailed", true);
            return new ModelAndView("login");
        }

        // We are logged in successfully
        session.setAttribute("username", username);
        request.changeSessionId();
        return new ModelAndView(new RedirectView("/ticket/list", true, false));
    }

    @PostMapping("/authenticateSignup")
    public String authenticateSignup(@ModelAttribute("signupForm") SignupForm form,
                                     Model model,
                                     HttpServletRequest request) {
        String username = form.getUsername();
        String password = form.getPassword();

        // Check if the username already exists
        if (userDB.containsKey(username)) {
            model.addAttribute("signupError", true);
            return "signup";
        }

        // Store username and password (convert username to lowercase)
        userDB.put(username.toLowerCase(), password);

        // Redirect to login page with signup success parameter
        return "redirect:/login?signupSuccess=true";
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

    public static class SignupForm {
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
