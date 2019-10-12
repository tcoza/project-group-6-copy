package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.TutorEvaluation;

public interface TutorEvaluationRepository extends CrudRepository<TutorEvaluation, Integer> {

}
