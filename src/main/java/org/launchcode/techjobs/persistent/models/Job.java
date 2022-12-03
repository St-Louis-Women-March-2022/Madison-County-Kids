package org.launchcode.techjobs.persistent.models;

import javax.lang.model.element.Name;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Job extends AbstractEntity {

    public Job(){}

    public Job(Employer newEmployer, List<Skill> skills){
        super();
        this.employer = newEmployer;
        this.skills= skills;
    }

    @ManyToOne
    public Employer employer;

    @ManyToMany
    @NotNull (message = "Skill is required.")
    public List<Skill> skills = new ArrayList<>();

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
