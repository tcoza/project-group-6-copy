package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ClassRoom extends Room{
   private Set<GroupSession> groupSession;
   
   @OneToMany(mappedBy="classRoom" )
   public Set<GroupSession> getGroupSession() {
      return this.groupSession;
   }
   
   public void setGroupSession(Set<GroupSession> groupSessions) {
      this.groupSession = groupSessions;
   }
   
   private int studentCapacity = 15;

public void setStudentCapacity(int value) {
    this.studentCapacity = value;
}
public int getStudentCapacity() {
    return this.studentCapacity;
}
}
