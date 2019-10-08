package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Offering{
   private Weekday weekday;

public void setWeekday(Weekday value) {
    this.weekday = value;
}
public Weekday getWeekday() {
    return this.weekday;
}
private Time endTime;

public void setEndTime(Time value) {
    this.endTime = value;
}
public Time getEndTime() {
    return this.endTime;
}
private Set<GroupSessionRequest> groupSessionRequest;

@ManyToMany
public Set<GroupSessionRequest> getGroupSessionRequest() {
   return this.groupSessionRequest;
}

public void setGroupSessionRequest(Set<GroupSessionRequest> groupSessionRequests) {
   this.groupSessionRequest = groupSessionRequests;
}

private Time startTime;

public void setStartTime(Time value) {
    this.startTime = value;
}
public Time getStartTime() {
    return this.startTime;
}
}
