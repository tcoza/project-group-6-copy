import java.util.Set;
import java.util.HashSet;

public class SmallRoom extends Room {
   /**
    * <pre>
    *           3..3     1..1
    * SmallRoom ------------------------- TutoringApp
    *           smallRoom        &lt;       tutoringApplication
    * </pre>
    */
   private TutoringApp tutoringApplication;
   
   public void setTutoringApplication(TutoringApp value) {
      this.tutoringApplication = value;
   }
   
   public TutoringApp getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * SmallRoom ------------------------- PrivateSession
    *           smallRoom        &lt;       privateSession
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
