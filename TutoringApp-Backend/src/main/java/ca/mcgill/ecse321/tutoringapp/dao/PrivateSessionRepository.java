package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.PrivateSession;

public interface PrivateSessionReposetory extends CrudRepository<PrivateSession, Integer> {

}
