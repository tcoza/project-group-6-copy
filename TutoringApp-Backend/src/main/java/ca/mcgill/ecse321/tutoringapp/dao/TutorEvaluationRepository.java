package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;
import ca.mcgill.ecse321.tutoringapp.model.TutorEvaluation;

/**
 * 
 * @author Alba
 *
 */
public interface TutorEvaluationRepository extends CrudRepository<TutorEvaluation, Integer> {
	List<TutorEvaluation> findByTutor(Tutor tutorName);

	boolean existsByStudentAndTutor(Student student, Tutor tutor);

	TutorEvaluation findByStudentAndTutor(Student student, Tutor tutor);
}
