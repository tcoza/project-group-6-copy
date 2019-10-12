package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringapp.model.TutoringApplicationModel;

public interface TutoringApplicationModelRepository extends CrudRepository<TutoringApplicationModel, String> {

}
