package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Evaluation;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Alba
 *
 */
public interface EvaluationRepository extends CrudRepository<Evaluation,Integer>{
	List<Evaluation> findByTutorEvaluation(Student student, Tutor tutor);

	boolean existsByStudentAndTutor(Student student, Tutor tutor);

	Evaluation findByStudentAndTutor(Student student, Tutor tutor);
}
