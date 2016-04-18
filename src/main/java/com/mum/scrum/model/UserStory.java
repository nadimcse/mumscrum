package com.mum.scrum.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
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
    private String description;
    @NotEmpty
    private String estimation;
    @NotNull
    private Project project;
    private UserStoryStatus userStoryStatus;
    private List<Sprint> sprints = new ArrayList<>();
    private List<LogTime> logTimes = new ArrayList<>();

    public UserStory() {
    }

    public UserStory(long id) {
        this.id = id;
    }

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
    @JsonIgnore
    public List<Sprint> getSprints() {
        return sprints;
    }

    @JsonProperty
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

    @ManyToOne
    @JoinColumn(name = "user_story_status_id")
    public UserStoryStatus getUserStoryStatus() {
        return userStoryStatus;
    }

    public void setUserStoryStatus(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
    }
}
