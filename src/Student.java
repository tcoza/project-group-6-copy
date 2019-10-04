import java.util.Set;
import java.util.HashSet;

public class Student extends AppUser {
   /**
    * <pre>
    *           1..*     0..*
    * Student ------------------------- SessionRequest
    *           student        &gt;       sessionRequest
    * </pre>
    */
   private Set<SessionRequest> sessionRequest;
   
   public Set<SessionRequest> getSessionRequest() {
      if (this.sessionRequest == null) {
         this.sessionRequest = new HashSet<SessionRequest>();
      }
      return this.sessionRequest;
   }
   
   private Boolean isActiveAccount = true;
   
   public void setIsActiveAccount(Boolean value) {
      this.isActiveAccount = value;
   }
   
   public Boolean getIsActiveAccount() {
      return this.isActiveAccount;
   }
   
   }
