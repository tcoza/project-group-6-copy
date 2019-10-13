package ca.mcgill.ecse321.tutoringapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Offering;
import ca.mcgill.ecse321.tutoringapp.model.SessionRequest;

/**
 * 
 * @author Helen
 *
 */
public interface OfferingRepository extends CrudRepository<Offering, Integer> {
	// total offerings by the company
	long count();

	// find one
	Offering findById(int id);

	// find many
	List<Offering> findAllById(List<Integer> ids);

	// find by session request
	List<Offering> findByOneSessionRequest(SessionRequest request);
	List<Offering> findByManySessionRequests(List<SessionRequest> requests);

	// find all
	List<Offering> findAll();
}
