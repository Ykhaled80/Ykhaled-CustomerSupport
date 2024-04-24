package com.example.ykhaledcustomersupport.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // This will return signup.jsp
    }
}
