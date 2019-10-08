package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class GroupSession extends ScheduledSession{
   private Set<GroupSessionRequest> groupSessionRequest;
   
   @OneToMany(mappedBy="groupSession" )
   public Set<GroupSessionRequest> getGroupSessionRequest() {
      return this.groupSessionRequest;
   }
   
   public void setGroupSessionRequest(Set<GroupSessionRequest> groupSessionRequests) {
      this.groupSessionRequest = groupSessionRequests;
   }
   
   private ClassRoom classRoom;
   
   @ManyToOne(optional=false)
   public ClassRoom getClassRoom() {
      return this.classRoom;
   }
   
   public void setClassRoom(ClassRoom classRoom) {
      this.classRoom = classRoom;
   }
   
   }
