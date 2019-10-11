package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.EvaluationComment;

public interface EvaluationCommentRepository extends CrudRepository<EvaluationComment, Integer> {

}
