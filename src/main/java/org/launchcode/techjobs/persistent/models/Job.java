package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Job extends AbstractEntity {

    @ManyToOne
    @NotNull (message="Jobs must have employers!")
    Employer employer;//changed type from Employer employer and redid getters\setters

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();//tags

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills){this.skills =skills;}

}
