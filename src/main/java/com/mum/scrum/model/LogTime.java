package com.mum.scrum.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private DateFormat format = new SimpleDateFormat("MM-dd-yyyy");

    private long id;
    //@NotEmpty
    private int lockedTime;
    private Date assignedDate = new Date();
    private String assignedDateStr;


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
    public int getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(int lockedTime) {
        this.lockedTime = lockedTime;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "assigned_date")
    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Column(name = "assigned_date_str")
    public String getAssignedDateStr() {
        return format.format(this.getAssignedDate());
    }

    public void setAssignedDateStr(String assignedDateStr) {
        this.assignedDateStr = assignedDateStr;
    }
}
