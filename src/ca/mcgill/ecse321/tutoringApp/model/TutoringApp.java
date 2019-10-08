package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class TutoringApp{
   private Set<AppUser> appUser;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<AppUser> getAppUser() {
      return this.appUser;
   }
   
   public void setAppUser(Set<AppUser> appUsers) {
      this.appUser = appUsers;
   }
   
   private Set<SmallRoom> smallRoom;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<SmallRoom> getSmallRoom() {
      return this.smallRoom;
   }
   
   public void setSmallRoom(Set<SmallRoom> smallRooms) {
      this.smallRoom = smallRooms;
   }
   
   private Set<ClassRoom> classRoom;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<ClassRoom> getClassRoom() {
      return this.classRoom;
   }
   
   public void setClassRoom(Set<ClassRoom> classRooms) {
      this.classRoom = classRooms;
   }
   
   }
