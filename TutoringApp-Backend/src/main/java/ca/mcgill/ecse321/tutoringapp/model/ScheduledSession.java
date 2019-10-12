package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ScheduledSession{
   private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
private Boolean isFull;

public void setIsFull(Boolean value) {
    this.isFull = value;
}
public Boolean getIsFull() {
    return this.isFull;
}
private String/*No type specified!*/ date;

public void setDate(String/*No type specified!*/ value) {
    this.date = value;
}
public String/*No type specified!*/ getDate() {
    return this.date;
}
private String/*No type specified!*/ startTime;

public void setStartTime(String/*No type specified!*/ value) {
    this.startTime = value;
}
public String/*No type specified!*/ getStartTime() {
    return this.startTime;
}
private String/*No type specified!*/ endTime;

public void setEndTime(String/*No type specified!*/ value) {
    this.endTime = value;
}
public String/*No type specified!*/ getEndTime() {
    return this.endTime;
}
   private Tutor assignedTutor;
   
   @ManyToOne(optional=false)
   public Tutor getAssignedTutor() {
      return this.assignedTutor;
   }
   
   public void setAssignedTutor(Tutor assignedTutor) {
      this.assignedTutor = assignedTutor;
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
