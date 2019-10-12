package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ClassRoom extends Room{
   private Set<ScheduledGroupSession> scheduledGroupSession;
   
   @OneToMany(mappedBy="room" )
   public Set<ScheduledGroupSession> getScheduledGroupSession() {
      return this.scheduledGroupSession;
   }
   
   public void setScheduledGroupSession(Set<ScheduledGroupSession> scheduledGroupSessions) {
      this.scheduledGroupSession = scheduledGroupSessions;
   }
   
   private int studentCapacity = 15;

public void setStudentCapacity(int value) {
    this.studentCapacity = value;
}
public int getStudentCapacity() {
    return this.studentCapacity;
}
}
