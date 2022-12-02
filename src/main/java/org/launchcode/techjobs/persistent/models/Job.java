package org.launchcode.techjobs.persistent.models;

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
        this.employers = newEmployer;
        this.skills= skills;
    }

    @ManyToOne
    private Employer employers;

    @ManyToMany
    @NotNull (message = "Skill is required.")
    private List<Skill> skills = new ArrayList<>();

    public Employer getEmployer() {
        return employers;
    }

    public void setEmployer(Employer employer) {
        this.employers = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
