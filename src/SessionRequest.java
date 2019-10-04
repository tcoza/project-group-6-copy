import java.util.Set;
import java.util.HashSet;

public class SessionRequest {
   /**
    * <pre>
    *           0..1     1..1
    * SessionRequest ------------------------- Course
    *           sessionRequest        &lt;       course
    * </pre>
    */
   private Course course;
   
   public void setCourse(Course value) {
      this.course = value;
   }
   
   public Course getCourse() {
      return this.course;
   }
   
   private Integer numStudents;
   
   public void setNumStudents(Integer value) {
      this.numStudents = value;
   }
   
   public Integer getNumStudents() {
      return this.numStudents;
   }
   
   /**
    * <pre>
    *           0..*     1..*
    * SessionRequest ------------------------- Student
    *           sessionRequest        &lt;       student
    * </pre>
    */
   private Set<Student> student;
   
   public Set<Student> getStudent() {
      if (this.student == null) {
         this.student = new HashSet<Student>();
      }
      return this.student;
   }
   
   }
