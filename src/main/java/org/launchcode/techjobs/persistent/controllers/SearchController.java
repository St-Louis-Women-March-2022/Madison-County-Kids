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
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm, @RequestParam List allJobs){
        Iterable<Job> jobs;

        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("") || searchTerm.isEmpty() || searchTerm.isBlank()){
            column = searchType;
            value = searchTerm;
            jobs = JobData.findByColumnAndValue(column, value, <Job> allJobs);

            return "search";

        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
            return "search";
        }
    }
}
//if we have nothing or all
//jobs is the whole job repository
//we get jobs, employers, and skills