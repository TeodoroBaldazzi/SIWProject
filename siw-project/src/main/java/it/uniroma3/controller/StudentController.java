package it.uniroma3.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.controller.validator.StudentValidator;
import it.uniroma3.converter.StringToDateUtilConverter;
import it.uniroma3.model.Student;
import it.uniroma3.service.ActivityService;
import it.uniroma3.service.StudentService;


@Controller
@SessionAttributes({"student","students"})
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentValidator validator;

    @RequestMapping("/home")
    public String home(){
        return "index";
    }


    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    @InitBinder("student")
	public void initBinderStudent(WebDataBinder binder) {
		//try {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
		//}
		//catch(Exception e) {
		//	 throw new IllegalArgumentException("Wrong Format");
		//}
	}

	@RequestMapping("/students")
	public String allievi(Model model) {
		model.addAttribute("students", this.studentService.findAll());
		return "studentsList";
	}

    @RequestMapping("/addStudent")
    public String addAllievo(Model model) {
        model.addAttribute("student", new Student());
        return "studentForm";
    }

	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public String getAllievo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("student", this.studentService.findById(id));
		return "showStudent";
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String newAllievo(@Valid @ModelAttribute("student") Student allievo, Model model, BindingResult bindingResult) {
		this.validator.validate(allievo, bindingResult);
		if (this.studentService.alreadyExists(allievo)) {
			model.addAttribute("exists", "Student already exists");
			model.addAttribute("esistente", this.studentService.findByNameAndSurnameAndEmail(allievo.getName(),
					allievo.getSurname(), allievo.getEmail()));
			return "studentForm";
		}
		else {
			if (!bindingResult.hasErrors()) {
				return "studentAccept";
			}
		}
		return "studentForm";
	}


	@RequestMapping(value="/confirmStudent", method = RequestMethod.GET)
	public String confirmAllievo(@ModelAttribute("student") Student allievo, Model model) {
		allievo.setDateTimeRegistration(new Date());
		this.studentService.save(allievo);
		model.addAttribute("students", this.studentService.findAll());
		return "studentsList";

	}




	@RequestMapping("/chooseStudent")
	public String chooseStudent(Model model) {
		//model.addAttribute("student", new Student());
		return "selectStudent";
	}


	@RequestMapping(value="/handleParticipations", method = RequestMethod.GET)
	public String handleParticipations(@RequestParam("email") String email, Model model) {
		if(email!=null && !email.equals("")) {
			Student current = this.studentService.findByEmail(email.toLowerCase());
			if(current==null) {
				model.addAttribute("exists", "Student does not exist");
				return "selectStudent";
			}
			model.addAttribute("student", current);
			return "activitiesList";
		}
		model.addAttribute("errorParam", "Insert email");
		return "showStudent";
	}

}

    

