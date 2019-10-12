package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.StudentEvaluation;

public interface StudentEvaluationRepository extends CrudRepository<StudentEvaluation,Integer>{

}
