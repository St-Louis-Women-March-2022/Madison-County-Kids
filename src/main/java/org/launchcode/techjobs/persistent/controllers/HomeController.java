package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
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

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {//I think I don't need an employerId here because I wouldn't have one yet.
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(Model model, @ModelAttribute @Valid Job newJob,
                                       Errors errors, @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {//double check skill or skills

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";}

//        List <Skill> skills = (List<Skill>)SkillRepository.findAllById(inputSkills);
//        newJob.setSkills(skills);
//
////        Employer employer = (Employer) optEmployer.get(employerId);
////        employer = employerRepository.findById(employerId);
////        Employer rightType = new Employer(employerId);
////
//////        newJob.setEmployer(employer);
//
////find the employerId in the employerRepository and bring it back
//// Call that thing you bring back employer
//        //set the Employer on newJob to employer
//
//
//
//        //do same with skills
//        //jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional optJob = jobRepository.findById (jobId);
        if (optJob.isPresent()){
            Job job = (Job) optJob.get();
            model.addAttribute("jobId", jobId);
            return "list-jobs";
        }
        return "redirect:.../";
    }

    public EmployerRepository getEmployerRepository() {
        return employerRepository;
    }

    public void setEmployerRepository(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public SkillRepository getSkillRepository() {
        return skillRepository;
    }

    public void setSkillRepository(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }
}
//HomeController should have an employerRepository field
//verify that the field is autowired
//Verifies that HomeController has an autowired EmployerRepository
//Verifies that HomeController.displayAddJobForm calls employerRepository.findAll()