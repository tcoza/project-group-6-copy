
public class GroupSession extends ScheduledSession {
   /**
    * <pre>
    *           0..*     1..1
    * GroupSession ------------------------- ClassRoom
    *           groupSession        &gt;       classRoom
    * </pre>
    */
   private ClassRoom classRoom;
   
   public void setClassRoom(ClassRoom value) {
      this.classRoom = value;
   }
   
   public ClassRoom getClassRoom() {
      return this.classRoom;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * GroupSession ------------------------- GroupRequest
    *           groupSession        &gt;       groupRequest
    * </pre>
    */
   private GroupRequest groupRequest;
   
   public void setGroupRequest(GroupRequest value) {
      this.groupRequest = value;
   }
   
   public GroupRequest getGroupRequest() {
      return this.groupRequest;
   }
   
   }
