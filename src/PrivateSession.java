
public class PrivateSession extends ScheduledSession {
   /**
    * <pre>
    *           0..*     1..1
    * PrivateSession ------------------------- SmallRoom
    *           privateSession        &gt;       smallRoom
    * </pre>
    */
   private SmallRoom smallRoom;
   
   public void setSmallRoom(SmallRoom value) {
      this.smallRoom = value;
   }
   
   public SmallRoom getSmallRoom() {
      return this.smallRoom;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * PrivateSession ------------------------- PrivateRequest
    *           privateSession        &lt;       privateRequest
    * </pre>
    */
   private PrivateRequest privateRequest;
   
   public void setPrivateRequest(PrivateRequest value) {
      this.privateRequest = value;
   }
   
   public PrivateRequest getPrivateRequest() {
      return this.privateRequest;
   }
   
   }
