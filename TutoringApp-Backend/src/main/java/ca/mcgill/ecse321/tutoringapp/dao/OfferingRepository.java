package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Offering;

/**
 * 
 * @author Helen
 *
 */
public interface OfferingRepository extends CrudRepository<Offering, Integer> {

}
