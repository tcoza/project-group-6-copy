import java.util.Set;
import java.util.HashSet;

public class TutoringApp {
   /**
    * <pre>
    *           1..1     0..*
    * TutoringApp ------------------------- AppUser
    *           tutoringApplication        &gt;       appUser
    * </pre>
    */
   private Set<AppUser> appUser;
   
   public Set<AppUser> getAppUser() {
      if (this.appUser == null) {
         this.appUser = new HashSet<AppUser>();
      }
      return this.appUser;
   }
   
   /**
    * <pre>
    *           1..1     3..3
    * TutoringApp ------------------------- SmallRoom
    *           tutoringApplication        &gt;       smallRoom
    * </pre>
    */
   private Set<SmallRoom> smallRoom;
   
   public Set<SmallRoom> getSmallRoom() {
      if (this.smallRoom == null) {
         this.smallRoom = new HashSet<SmallRoom>();
      }
      return this.smallRoom;
   }
   
   /**
    * <pre>
    *           1..1     10..10
    * TutoringApp ------------------------- ClassRoom
    *           tutoringApplication        &gt;       classRoom
    * </pre>
    */
   private Set<ClassRoom> classRoom;
   
   public Set<ClassRoom> getClassRoom() {
      if (this.classRoom == null) {
         this.classRoom = new HashSet<ClassRoom>();
      }
      return this.classRoom;
   }
   
   }
