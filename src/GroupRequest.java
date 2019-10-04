import java.util.Set;
import java.util.HashSet;

public class GroupRequest extends SessionRequest {
   /**
    * <pre>
    *           1..1     0..*
    * GroupRequest ------------------------- GroupSession
    *           groupRequest        &lt;       groupSession
    * </pre>
    */
   private Set<GroupSession> groupSession;
   
   public Set<GroupSession> getGroupSession() {
      if (this.groupSession == null) {
         this.groupSession = new HashSet<GroupSession>();
      }
      return this.groupSession;
   }
   
   }
