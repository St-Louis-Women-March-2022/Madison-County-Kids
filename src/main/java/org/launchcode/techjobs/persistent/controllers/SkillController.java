package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static javax.swing.text.html.parser.DTDConstants.ID;

@Controller
@RequestMapping("skills")//this works.  DON"T TOUCH
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("add")//this works.  DON"T TOUCH
    public String displayAddSkillForm(Model model) {
        model.addAttribute("title", "Add Skill");
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")//this works.  DON"T TOUCH
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("skills", skillRepository.findAll());
            return "skills/add";
        }

        skillRepository.save(newSkill);
        return "redirect:";
    }

    @GetMapping("view/{skillId}")//this works.  DON"T TOUCH
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";

        } else {
            return "redirect:../";
        }
    }

    @GetMapping("")//DON"T TOUCH//displaySkillsIndex
    public String index(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }
}