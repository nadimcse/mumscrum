package com.mum.scrum.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/5/16
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table(name = "user_story")
public class UserStory extends Persistent implements Serializable {

    private long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private String estimation;
    @NotNull
    private Project project;
    private List<Sprint> sprints = new ArrayList<>();
    private List<LogTime> logTimes = new ArrayList<>();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userStories")
    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //mappedby ?????
    public List<LogTime> getLogTimes() {
        return logTimes;
    }

    public void setLogTimes(List<LogTime> logTimes) {
        this.logTimes = logTimes;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
