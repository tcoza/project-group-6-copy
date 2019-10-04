import java.util.Set;
import java.util.Date;
import java.util.HashSet;

public class ScheduledSession {
private Date start;
   
   public void setStart(Date value) {
      this.start = value;
   }
   
   public Date getStart() {
      return this.start;
   }
   
   private Date end;
   
   public void setEnd(Date value) {
      this.end = value;
   }
   
   public Date getEnd() {
      return this.end;
   }
   
   
   private Integer id;
   
   public void setId(Integer value) {
      this.id = value;
   }
   
   public Integer getId() {
      return this.id;
   }
   
   private Boolean isFull;
   
   public void setIsFull(Boolean value) {
      this.isFull = value;
   }
   
   public Boolean getIsFull() {
      return this.isFull;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * ScheduledSession ------------------------- Tutor
    *           scheduledSession        &gt;       tutor
    * </pre>
    */
   private Tutor tutor;
   
   public void setTutor(Tutor value) {
      this.tutor = value;
   }
   
   public Tutor getTutor() {
      return this.tutor;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * ScheduledSession ------------------------- Evaluation
    *           scheduledSession        &lt;       evaluation
    * </pre>
    */
   private Set<Evaluation> evaluation;
   
   public Set<Evaluation> getEvaluation() {
      if (this.evaluation == null) {
         this.evaluation = new HashSet<Evaluation>();
      }
      return this.evaluation;
   }
   
   }
