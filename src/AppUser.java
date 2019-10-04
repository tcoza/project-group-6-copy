
public abstract class AppUser {
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * AppUser ------------------------- TutoringApplication
    *           appUser        &lt;       tutoringApplication
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
