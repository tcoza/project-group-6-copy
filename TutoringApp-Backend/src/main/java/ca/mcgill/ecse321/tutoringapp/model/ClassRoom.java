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
   
   }
