import java.util.Set;
import java.util.HashSet;

public class Subject {
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Subject ------------------------- Course
    *           subject        &lt;       course
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
