package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PrivateSession extends ScheduledSession{
   private Student student;
   
   @ManyToOne(optional=false)
   public Student getStudent() {
      return this.student;
   }
   
   public void setStudent(Student student) {
      this.student = student;
   }
   
   private SmallRoom smallRoom;
   
   @ManyToOne(optional=false)
   public SmallRoom getSmallRoom() {
      return this.smallRoom;
   }
   
   public void setSmallRoom(SmallRoom smallRoom) {
      this.smallRoom = smallRoom;
   }
   
   }
