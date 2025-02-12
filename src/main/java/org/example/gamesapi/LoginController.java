package org.example.gamesapi;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    SecurityService securityService;

    @GetMapping
    public String login(Model model) {
        return "login";
    }
    @PostMapping
    public String processLogin(HttpSession session, Model model, @ModelAttribute User login) {
        var result = securityService.login(login.getUser(), login.getEmail());
        if(result.isPresent()){
            session.setAttribute("user", result.get());
            return "redirect:/web/";
        } else return "login";
    }

    @GetMapping("/exit")
    public String exit(HttpSession session, Model model) {
        session.removeAttribute("user");
        return "redirect:/web/";
    }

}
