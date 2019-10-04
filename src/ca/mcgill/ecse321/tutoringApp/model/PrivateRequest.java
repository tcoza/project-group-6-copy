package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class PrivateRequest{
   private Set<PrivateSession> privateSession;
   
   @OneToMany(mappedBy="privateRequest" )
   public Set<PrivateSession> getPrivateSession() {
      return this.privateSession;
   }
   
   public void setPrivateSession(Set<PrivateSession> privateSessions) {
      this.privateSession = privateSessions;
   }
   
   }
