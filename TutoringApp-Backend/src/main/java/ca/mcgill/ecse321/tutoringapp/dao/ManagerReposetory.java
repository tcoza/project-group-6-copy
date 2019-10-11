package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Manager;

public interface ManagerReposetory extends CrudRepository<Manager, String> {

}
