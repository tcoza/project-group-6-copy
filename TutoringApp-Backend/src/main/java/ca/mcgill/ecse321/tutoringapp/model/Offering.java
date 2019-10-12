package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Offering{
   private String/*No type specified!*/ weekday;

public void setWeekday(String/*No type specified!*/ value) {
    this.weekday = value;
}
public String/*No type specified!*/ getWeekday() {
    return this.weekday;
}
private String/*No type specified!*/ endTime;

public void setEndTime(String/*No type specified!*/ value) {
    this.endTime = value;
}
public String/*No type specified!*/ getEndTime() {
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

private String/*No type specified!*/ startTime;

public void setStartTime(String/*No type specified!*/ value) {
    this.startTime = value;
}
public String/*No type specified!*/ getStartTime() {
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
