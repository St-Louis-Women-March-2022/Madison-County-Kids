package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {//tag

    @ManyToMany (mappedBy = "skills")//from skills field in job
    @NotNull
    public List<Job>jobs = new ArrayList<>();//list of indv. jobs

    @Size(min =3, max = 250, message = "size must be between 3 and 250")
    @NotNull
    public String description;

    public Skill(){}

    public Skill (String description, List<Job>jobs){
        super();
        this.description=description;
        this.jobs=jobs;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}