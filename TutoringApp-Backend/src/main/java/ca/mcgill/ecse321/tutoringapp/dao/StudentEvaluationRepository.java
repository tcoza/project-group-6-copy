package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.StudentEvaluation;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * 
 * @author Alba
 *
 */
public interface StudentEvaluationRepository extends CrudRepository<StudentEvaluation,Integer>{
	List <StudentEvaluation> findByStudent (Student studentName);
	boolean existsByStudentAndTutor(Student student,Tutor tutor);
	StudentEvaluation findByStudentAndTutor(Student student,Tutor tutor);
}
