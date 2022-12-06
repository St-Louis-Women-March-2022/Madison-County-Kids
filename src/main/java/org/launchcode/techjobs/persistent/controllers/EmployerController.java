package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.Optional;

import static javax.swing.text.html.parser.DTDConstants.ID;

@Controller
@RequestMapping("employers")//this works.  DON"T TOUCH
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("add")//DON"T TOUCH
    public String displayAddEmployerForm(Model model) {
        model.addAttribute("title","Add Employer");
        model.addAttribute (new Employer());
        return "employers/add";
    }

    @PostMapping("add")//DON"T TOUCH
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("employers", employerRepository.findAll());
            return "employers/add";
        }

        employerRepository.save(newEmployer);
        return "redirect:";
    }

    @GetMapping("view/{employerId}")//DON"T TOUCH
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional <Employer> optEmployer = employerRepository.findById(employerId);//Optional is how reposity.findById comes back.
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";

        } else {
            return "redirect:../";
        }
    }

    @RequestMapping("")//DON"T TOUCH
    public String index (Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }
}