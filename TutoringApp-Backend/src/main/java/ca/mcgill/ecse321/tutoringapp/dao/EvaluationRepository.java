package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.Evaluation;

/**
 * 
 * @author Alba
 *
 */
@RepositoryRestResource(collectionResourceRel = "evaluations", path = "evaluations")
public interface EvaluationRepository extends CrudRepository<Evaluation,Integer>{

	Evaluation findById(int id);
	
	List<Evaluation> findByRating(int rating);

}
