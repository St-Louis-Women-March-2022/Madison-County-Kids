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

    @RequestMapping("")//DON"T TOUCH
    public String index(Model model) {//viewEntries
        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")//DON"T TOUCH
    public String displayAddJobForm(Model model) {//Took this out: , @ModelAttribute @Valid Job newJob, @RequestParam List<Integer> skills, Errors errors
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("jobs", jobRepository.findAll());
        return "add";
    }

    @PostMapping("add")//DON"T TOUCH
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model,
                                    @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            return "add";
        }

        Optional optEmployer = employerRepository.findById (employerId);
        if (optEmployer.isPresent()){
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employerId", employerId);
            newJob.setEmployer(employer);
        }//    find the employerId in the employerRepository and bring it back. Call that thing you bring back employer. set the Employer on newJob to employer

        List<Skill> wtf = (List<Skill>)skillRepository.findAllById(skills);
        newJob.setSkills(wtf);

        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")////DON"T TOUCH
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()){
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "view";
        }
        else {return "redirect:.../";}
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