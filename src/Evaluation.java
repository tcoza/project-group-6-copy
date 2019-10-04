import java.util.Date;

public class Evaluation {
private AppUser appUser;
   
   public void setAppUser(AppUser value) {
      this.appUser = value;
   }
   
   public AppUser getAppUser() {
      return this.appUser;
   }
   
   private Date date;
   
   public void setDate(Date value) {
      this.date = value;
   }
   
   public Date getDate() {
      return this.date;
   }
   
   /**
    * <pre>
    *           1..1     0..1
    * Evaluation ------------------------- EvaluationComment
    *           evaluation        &gt;       evaluationComment
    * </pre>
    */
   private EvaluationComment evaluationComment;
   
   public void setEvaluationComment(EvaluationComment value) {
      this.evaluationComment = value;
   }
   
   public EvaluationComment getEvaluationComment() {
      return this.evaluationComment;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * Evaluation ------------------------- EvaluationRating
    *           evaluation        &lt;       evaluationRating
    * </pre>
    */
   private EvaluationRating evaluationRating;
   
   public void setEvaluationRating(EvaluationRating value) {
      this.evaluationRating = value;
   }
   
   public EvaluationRating getEvaluationRating() {
      return this.evaluationRating;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Evaluation ------------------------- ScheduledSession
    *           evaluation        &gt;       scheduledSession
    * </pre>
    */
   private ScheduledSession scheduledSession;
   
   public void setScheduledSession(ScheduledSession value) {
      this.scheduledSession = value;
   }
   
   public ScheduledSession getScheduledSession() {
      return this.scheduledSession;
   }
   
   }
