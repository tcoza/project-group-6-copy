package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class GroupRequest extends SessionRequest{
   private ScheduledGroupSession scheduledGroupSession;
   
   @ManyToOne
   public ScheduledGroupSession getScheduledGroupSession() {
      return this.scheduledGroupSession;
   }
   
   public void setScheduledGroupSession(ScheduledGroupSession scheduledGroupSession) {
      this.scheduledGroupSession = scheduledGroupSession;
   }
   
   }
