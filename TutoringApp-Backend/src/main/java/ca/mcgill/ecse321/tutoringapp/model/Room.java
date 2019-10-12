package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class Room{
   private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
   private TutoringApplicationModel tutoringApplication;
   
   @ManyToOne(optional=false)
   public TutoringApplicationModel getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   public void setTutoringApplication(TutoringApplicationModel tutoringApplication) {
      this.tutoringApplication = tutoringApplication;
   }
   
   }
