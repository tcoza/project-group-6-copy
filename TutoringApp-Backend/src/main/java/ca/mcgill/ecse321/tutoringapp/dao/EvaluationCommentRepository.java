package ca.mcgill.ecse321.tutoringapp.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.Evaluation;
import ca.mcgill.ecse321.tutoringapp.model.EvaluationComment;

/**
 * 
 * @author Alba
 *
 */
@RepositoryRestResource(collectionResourceRel = "evaluationcomments", path = "evaluationcomments")
public interface EvaluationCommentRepository extends CrudRepository<EvaluationComment, Integer> {

	EvaluationComment findByEvaluation(Evaluation evaluation);

}
