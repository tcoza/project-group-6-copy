package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.sql.Date;
import javax.persistence.Id;

@Entity
public class Evaluation{
   private Student author;
   
   @ManyToOne(optional=false)
   public Student getAuthor() {
      return this.author;
   }
   
   public void setAuthor(Student author) {
      this.author = author;
   }
   
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private EvaluationComment evaluationComment;
   
   @OneToOne(mappedBy="evaluation" , cascade={CascadeType.ALL})
   public EvaluationComment getEvaluationComment() {
      return this.evaluationComment;
   }
   
   public void setEvaluationComment(EvaluationComment evaluationComment) {
      this.evaluationComment = evaluationComment;
   }
   
   private int rating;

public void setRating(int value) {
    this.rating = value;
}
public int getRating() {
    return this.rating;
}
private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
}
