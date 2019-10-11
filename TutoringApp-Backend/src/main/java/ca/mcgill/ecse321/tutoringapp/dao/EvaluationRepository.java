package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Evaluation;

public interface EvaluationRepository extends CrudRepository<Evaluation,Integer>{

}
