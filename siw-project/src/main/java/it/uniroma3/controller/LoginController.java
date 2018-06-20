package it.uniroma3.controller;


import it.uniroma3.model.User;
import it.uniroma3.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"user"})
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();

        boolean noUsers;
        if (userService.findAll().isEmpty())
            noUsers=true;
        else
            noUsers=false;

        modelAndView.addObject("noUsers", noUsers);

        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute("user") User user, Model model) {
        this.userService.saveUser(user);
        model.addAttribute("successMessage", "User has been registered successfully");

        boolean noUsers;
        if (userService.findAll().isEmpty())
            noUsers=true;
        else
            noUsers=false;

        model.addAttribute("noUsers", noUsers);


        //modelAndView.addObject("user", user);
        return "login";
    }


    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(Model model, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        session.setAttribute("currentUser", user);

        session.setAttribute("userName",   user.getName() + " " + user.getLastName());

        modelAndView.setViewName("index");
        return modelAndView;
    }


}
