import java.util.Set;
import java.util.HashSet;

public class PrivateRequest {
   /**
    * <pre>
    *           1..1     0..*
    * PrivateRequest ------------------------- PrivateSession
    *           privateRequest        &gt;       privateSession
    * </pre>
    */
   private Set<PrivateSession> privateSession;
   
   public Set<PrivateSession> getPrivateSession() {
      if (this.privateSession == null) {
         this.privateSession = new HashSet<PrivateSession>();
      }
      return this.privateSession;
   }
   
   }
