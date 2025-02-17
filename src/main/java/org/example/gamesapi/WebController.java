package org.example.gamesapi;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class WebController {
    @Autowired
    JuegoRepository juegoRepository;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        var juegos = juegoRepository.findAll();
        model.addAttribute("titulo","Listado de juegos");
        model.addAttribute("juegos", juegos);
        if(session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("usuario", user);
        }
        return "index";
    }

    @GetMapping("/{id}")
    public String single(Model model, @PathVariable String id) {
        var juego = juegoRepository.findById(id);
        if(juego.isEmpty()) return "404";

        else {
            model.addAttribute("juego", juego.get());
            return "single";
        }
    }

    @GetMapping("/new")
    public String createNew(HttpSession session) {
        if(session.getAttribute("user") != null) {
            return "new";
        }else{
            return "redirect:/login";
        }
    }

    @PostMapping("/new")
    public String create(HttpSession session, @ModelAttribute Juego juego) {
        if(session.getAttribute("user") != null) {
            juegoRepository.save(juego);
            return "redirect:/web/";
        }else{
            return "redirect:/login/";
        }
    }

}
