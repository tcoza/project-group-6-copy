import java.util.Set;
import java.util.HashSet;

public class Tutor extends AppUser {
   private TutorStatus status = TutorStatus.Pending;
   
   public void setStatus(TutorStatus value) {
      this.status = value;
   }
   
   public TutorStatus getStatus() {
      return this.status;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Tutor ------------------------- Course
    *           tutor        &gt;       course
    * </pre>
    */
   private Set<Course> course;
   
   public Set<Course> getCourse() {
      if (this.course == null) {
         this.course = new HashSet<Course>();
      }
      return this.course;
   }
   
   }
