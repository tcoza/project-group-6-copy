package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Evaluation;

/**
 * 
 * @author Alba
 *
 */
public interface EvaluationRepository extends CrudRepository<Evaluation,Integer>{

	Evaluation findById(int id);
	
	List<Evaluation> findByRating(int rating);

}
