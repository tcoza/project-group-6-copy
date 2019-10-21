package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutoringapp.model.Offering;
import ca.mcgill.ecse321.tutoringapp.model.SessionRequest;

/**
 * 
 * @author Helen
 *
 */
@RepositoryRestResource(collectionResourceRel = "offerings", path = "offerings")
public interface OfferingRepository extends CrudRepository<Offering, Integer> {
	// total offerings by the company
	long count();

	// find one
	Offering findById(int id);

	// find many
	List<Offering> findAllById(List<Integer> ids);

	// find by session request
	List<Offering> findBySessionRequest(SessionRequest request);
	List<Offering> findBySessionRequest(List<SessionRequest> requests);

	// find all
	List<Offering> findAll();
}
