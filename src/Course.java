import java.util.Set;
import java.util.HashSet;

public class Course {
   private String courseCode;
   
   public void setCourseCode(String value) {
      this.courseCode = value;
   }
   
   public String getCourseCode() {
      return this.courseCode;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Course ------------------------- Subject
    *           course        &gt;       subject
    * </pre>
    */
   private Subject subject;
   
   public void setSubject(Subject value) {
      this.subject = value;
   }
   
   public Subject getSubject() {
      return this.subject;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Course ------------------------- University
    *           course        &gt;       university
    * </pre>
    */
   private University university;
   
   public void setUniversity(University value) {
      this.university = value;
   }
   
   public University getUniversity() {
      return this.university;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Course ------------------------- Tutor
    *           course        &lt;       tutor
    * </pre>
    */
   private Set<Tutor> tutor;
   
   public Set<Tutor> getTutor() {
      if (this.tutor == null) {
         this.tutor = new HashSet<Tutor>();
      }
      return this.tutor;
   }
   
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   /**
    * <pre>
    *           1..1     0..1
    * Course ------------------------- SessionRequest
    *           course        &gt;       sessionRequest
    * </pre>
    */
   private SessionRequest sessionRequest;
   
   public void setSessionRequest(SessionRequest value) {
      this.sessionRequest = value;
   }
   
   public SessionRequest getSessionRequest() {
      return this.sessionRequest;
   }
   
   }
