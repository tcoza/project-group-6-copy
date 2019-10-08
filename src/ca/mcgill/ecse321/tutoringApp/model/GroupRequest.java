package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class GroupRequest extends SessionRequest{
   private Set<GroupSession> groupSession;
   
   @OneToMany(mappedBy="groupRequest" )
   public Set<GroupSession> getGroupSession() {
      return this.groupSession;
   }
   
   public void setGroupSession(Set<GroupSession> groupSessions) {
      this.groupSession = groupSessions;
   }
   
   }
