package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Tutor extends AppUser{
   private TutorStatus status = TutorStatus.Pending;

public void setStatus(TutorStatus value) {
    this.status = value;
}
public TutorStatus getStatus() {
    return this.status;
}
   private Set<Course> course;
   
   @ManyToMany(mappedBy="tutor" )
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private Set<ScheduledSession> scheduledSession;
   
   @OneToMany(mappedBy="tutor" )
   public Set<ScheduledSession> getScheduledSession() {
      return this.scheduledSession;
   }
   
   public void setScheduledSession(Set<ScheduledSession> scheduledSessions) {
      this.scheduledSession = scheduledSessions;
   }
   
   }
