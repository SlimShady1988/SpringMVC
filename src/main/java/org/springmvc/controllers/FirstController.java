package org.springmvc.controllers;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class FirstController {
    @GetMapping("/hello")
    public String helloSpring(HttpServletRequest request, HttpServletResponse response, Model model) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String message = "Hello, " + name + " " + surname;
        //it has been done for not nullable results
        if (name == null && surname == null) {
            name = request.getHeader("cookie");
            surname = request.getHeader("user-agent");
            message = "We don't know who are you, because u will have a name from your request headers";
        }

        model.addAttribute("message", message);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);

        return "first/hello";
    }
    @GetMapping("/goodbye")
    public String byeSpring() {
        return "first/bye";
    }
}
