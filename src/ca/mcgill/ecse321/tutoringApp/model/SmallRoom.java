package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class SmallRoom extends Room{
   private TutoringApp tutoringApplication;
   
   @ManyToOne(optional=false)
   public TutoringApp getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   public void setTutoringApplication(TutoringApp tutoringApplication) {
      this.tutoringApplication = tutoringApplication;
   }
   
   private Set<PrivateSession> privateSession;
   
   @OneToMany(mappedBy="smallRoom" )
   public Set<PrivateSession> getPrivateSession() {
      return this.privateSession;
   }
   
   public void setPrivateSession(Set<PrivateSession> privateSessions) {
      this.privateSession = privateSessions;
   }
   
   }
