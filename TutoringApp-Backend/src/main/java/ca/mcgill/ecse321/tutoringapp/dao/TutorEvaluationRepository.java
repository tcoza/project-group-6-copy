package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;
import ca.mcgill.ecse321.tutoringapp.model.TutorEvaluation;

/**
 * This interface is the CRUD repository for all TutorEvaluations in the
 * Tutoring System. It is mapped as a RepositoryRestResource that can be viewed
 * with GET requests at /tutorevaluations
 * 
 * @author Alba
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "tutorevauations", path = "tutorevaluations")
public interface TutorEvaluationRepository extends CrudRepository<TutorEvaluation, Integer> {
	List<TutorEvaluation> findByRecipient(Tutor tutor);

	boolean existsByAuthorAndRecipient(Student student, Tutor tutor);

	TutorEvaluation findByAuthorAndRecipient(Student student, Tutor tutor);

}
