package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;

@Entity
public class ScheduledSession{
   private Integer id;

public void setId(Integer value) {
    this.id = value;
}
public Integer getId() {
    return this.id;
}
private Boolean isFull;

public void setIsFull(Boolean value) {
    this.isFull = value;
}
public Boolean getIsFull() {
    return this.isFull;
}
private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Time startTime;

public void setStartTime(Time value) {
    this.startTime = value;
}
public Time getStartTime() {
    return this.startTime;
}
private Time endTime;

public void setEndTime(Time value) {
    this.endTime = value;
}
public Time getEndTime() {
    return this.endTime;
}
}
