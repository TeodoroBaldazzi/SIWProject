package it.uniroma3.controller;


import it.uniroma3.controller.validator.UserValidator;
import it.uniroma3.model.Role;
import it.uniroma3.model.User;
import it.uniroma3.service.ManagerService;
import it.uniroma3.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes({"user"})
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private UserValidator validator;


    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        boolean noUsers;
        if (userService.findAll().isEmpty())
            noUsers=true;
        else
            noUsers=false;

        modelAndView.addObject("noUsers", noUsers);


        modelAndView.setViewName("login");

        if(session.getAttribute("userName")!=null)
            modelAndView.setViewName("index");

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
	public String createNewUser(@Valid @ModelAttribute("user") User user,
			Model model, BindingResult bindingResult) {
		validator.validate(user, bindingResult);

		if(this.userService.findUserByUsername(user.getUsername())!=null) {
			model.addAttribute("exists","User already exists");
			return "registration";
		}
		else
			if (!bindingResult.hasErrors()) {
				this.userService.saveAdminUser(user);
				model.addAttribute("successMessage", "User has been registered successfully");

				boolean noUsers;
				if (userService.findAll().isEmpty())
					noUsers=true;
				else
					noUsers=false;

				model.addAttribute("noUsers", noUsers);
				return "login";
			}
		return "registration";
	}


	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(Model model, HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUsername(auth.getName());


        boolean isAdmin=false;
        for (Role r : user.getRoles()) {
            if(r.getRole().equals("ADMIN"))
                isAdmin=true;
        }

        session.setAttribute("isAdmin", isAdmin);

        if(!isAdmin)
            session.setAttribute("currentFacility", this.managerService.findByUsername(user.getUsername()).getCentroDiAppartenenza());
        session.setAttribute("userName",   user.getName() + " " + user.getLastName());

        modelAndView.setViewName("index");
        return modelAndView;
    }


}
