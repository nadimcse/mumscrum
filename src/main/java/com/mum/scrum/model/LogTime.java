package com.mum.scrum.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nadim
 * Date: 4/5/16
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "log_time")
public class LogTime {

    private long id;
    private String lockedTime;
    private User user;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "locked_time")
    public String getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(String lockedTime) {
        this.lockedTime = lockedTime;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
