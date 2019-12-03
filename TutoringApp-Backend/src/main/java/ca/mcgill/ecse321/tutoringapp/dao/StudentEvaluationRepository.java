package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.StudentEvaluation;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;

/**
 * This interface is the CRUD repository for all StudentEvaluations in the
 * Tutoring System. It is mapped as a RepositoryRestResource that can be viewed
 * with GET requests at /studentevaluations
 * 
 * @author Alba
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "studentevaluations", path = "studentevaluations")
public interface StudentEvaluationRepository extends CrudRepository<StudentEvaluation, Integer> {
	List<StudentEvaluation> findByRecipient(Student studentName);

	boolean existsByRecipientAndAuthor(Student student, Tutor tutor);

	StudentEvaluation findByRecipientAndAuthor(Student student, Tutor tutor);

}
