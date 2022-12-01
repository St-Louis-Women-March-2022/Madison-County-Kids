package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Job extends AbstractEntity {

    @ManyToOne
    @NotNull (message = "Employer is required.")
    Optional<Employer> employer;

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    public Optional<Employer> getEmployer() {
        return employer;
    }

    public void setEmployer(Optional<Employer> employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
