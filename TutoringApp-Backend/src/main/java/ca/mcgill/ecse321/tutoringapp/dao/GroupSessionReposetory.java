package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.GroupSession;

public interface GroupSessionReposetory extends CrudRepository<GroupSession, Integer> {

}
