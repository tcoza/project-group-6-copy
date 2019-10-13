package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.EvaluationComment;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Alba
 *
 */
public interface EvaluationCommentRepository extends CrudRepository<EvaluationComment, Integer> {
	List<EvaluationComment> findbyEvaluation (Student student, Tutor tutor);	
	EvaluationComment findByEvaluation(Student student,Tutor tutor);
	}
