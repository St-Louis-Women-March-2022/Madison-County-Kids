package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.JobData;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.launchcode.techjobs.persistent.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")////DON"T TOUCH
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")//NOT
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Job> jobs;

        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
            jobs = jobRepository.findAll();

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
        }
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("value", "searchTerm");
            model.addAttribute("allJobs", jobRepository.findAll());
            return "search";
        }
}
//if we have nothing or all
//jobs is the whole job repository
//we get jobs, employers, and skills