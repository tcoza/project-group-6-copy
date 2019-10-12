package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.ScheduledPrivateSession;

public interface ScheduledPrivateSessionRepository extends CrudRepository<ScheduledPrivateSession, Integer> {

}
