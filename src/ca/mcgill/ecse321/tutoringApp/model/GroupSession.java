package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class GroupSession extends ScheduledSession{
   private ClassRoom classRoom;
   
   @ManyToOne(optional=false)
   public ClassRoom getClassRoom() {
      return this.classRoom;
   }
   
   public void setClassRoom(ClassRoom classRoom) {
      this.classRoom = classRoom;
   }
   
   private GroupRequest groupRequest;
   
   @ManyToOne(optional=false)
   public GroupRequest getGroupRequest() {
      return this.groupRequest;
   }
   
   public void setGroupRequest(GroupRequest groupRequest) {
      this.groupRequest = groupRequest;
   }
   
   }
