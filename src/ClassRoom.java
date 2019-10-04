import java.util.Set;
import java.util.HashSet;

public class ClassRoom extends Room {
   /**
    * <pre>
    *           10..10     1..1
    * ClassRoom ------------------------- TutoringApp
    *           classRoom        &lt;       tutoringApplication
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
    * ClassRoom ------------------------- GroupSession
    *           classRoom        &lt;       groupSession
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
