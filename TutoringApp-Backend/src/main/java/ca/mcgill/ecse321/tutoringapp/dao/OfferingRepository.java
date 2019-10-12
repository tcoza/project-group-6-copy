package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Offering;

public interface OfferingReposetory extends CrudRepository<Offering, Integer> {

}
