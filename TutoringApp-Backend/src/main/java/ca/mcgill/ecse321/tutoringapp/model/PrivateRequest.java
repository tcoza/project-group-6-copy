package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PrivateRequest extends SessionRequest{
   private ScheduledPrivateSession scheduledPrivateSession;
   
   @OneToOne(mappedBy="privateRequest" )
   public ScheduledPrivateSession getScheduledPrivateSession() {
      return this.scheduledPrivateSession;
   }
   
   public void setScheduledPrivateSession(ScheduledPrivateSession scheduledPrivateSession) {
      this.scheduledPrivateSession = scheduledPrivateSession;
   }
   
   }
