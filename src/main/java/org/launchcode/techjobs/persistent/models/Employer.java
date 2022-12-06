package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotNull(message = "location required")
    @Size(min = 3, max = 15, message = "size must be between 3 and 15")
    public String location;

    @OneToMany
    @JoinColumn(name = "employer_id")
    public List<Job> jobs = new ArrayList<>();

    public Employer() {
    }

//    public Employer(String location) {// this screwed it all up
//        super();
//        this.location = location;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}