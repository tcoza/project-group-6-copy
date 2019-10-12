package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;

import java.sql.Time;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
private Set<SessionRequest> sessionRequest;

@ManyToMany
public Set<SessionRequest> getSessionRequest() {
   return this.sessionRequest;
}

public void setSessionRequest(Set<SessionRequest> sessionRequests) {
   this.sessionRequest = sessionRequests;
}

private Time startTime;

public void setStartTime(Time value) {
    this.startTime = value;
}
public Time getStartTime() {
    return this.startTime;
}
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
   private TutoringApplicationModel tutoringApplication;
   
   @ManyToOne(optional=false)
   public TutoringApplicationModel getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   public void setTutoringApplication(TutoringApplicationModel tutoringApplication) {
      this.tutoringApplication = tutoringApplication;
   }
   
   }
