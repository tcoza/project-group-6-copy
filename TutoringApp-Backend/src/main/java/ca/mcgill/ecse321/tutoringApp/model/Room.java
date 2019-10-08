package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;

@Entity
public abstract class Room{
   private Integer id;

public void setId(Integer value) {
    this.id = value;
}
public Integer getId() {
    return this.id;
}
}
