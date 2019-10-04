package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PrivateSession extends ScheduledSession{
   private SmallRoom smallRoom;
   
   @ManyToOne(optional=false)
   public SmallRoom getSmallRoom() {
      return this.smallRoom;
   }
   
   public void setSmallRoom(SmallRoom smallRoom) {
      this.smallRoom = smallRoom;
   }
   
   private PrivateRequest privateRequest;
   
   @ManyToOne(optional=false)
   public PrivateRequest getPrivateRequest() {
      return this.privateRequest;
   }
   
   public void setPrivateRequest(PrivateRequest privateRequest) {
      this.privateRequest = privateRequest;
   }
   
   }
