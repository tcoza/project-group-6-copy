package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ClassRoom extends Room{
   private TutoringApp tutoringApplication;
   
   @ManyToOne(optional=false)
   public TutoringApp getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   public void setTutoringApplication(TutoringApp tutoringApplication) {
      this.tutoringApplication = tutoringApplication;
   }
   
   private Set<GroupSession> groupSession;
   
   @OneToMany(mappedBy="classRoom" )
   public Set<GroupSession> getGroupSession() {
      return this.groupSession;
   }
   
   public void setGroupSession(Set<GroupSession> groupSessions) {
      this.groupSession = groupSessions;
   }
   
   }
