package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class SmallRoom extends Room{
   private Set<PrivateSession> privateSession;
   
   @OneToMany(mappedBy="smallRoom" )
   public Set<PrivateSession> getPrivateSession() {
      return this.privateSession;
   }
   
   public void setPrivateSession(Set<PrivateSession> privateSessions) {
      this.privateSession = privateSessions;
   }
   
   private int studentCapacity = 1;

public void setStudentCapacity(int value) {
    this.studentCapacity = value;
}
public int getStudentCapacity() {
    return this.studentCapacity;
}
}