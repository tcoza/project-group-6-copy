
public class ClassRoom extends Room {
   /**
    * <pre>
    *           10..10     1..1
    * ClassRoom ------------------------- TutoringApplication
    *           classRoom        &lt;       tutoringApplication
    * </pre>
    */
   private TutoringApplication tutoringApplication;
   
   public void setTutoringApplication(TutoringApplication value) {
      this.tutoringApplication = value;
   }
   
   public TutoringApplication getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   }
