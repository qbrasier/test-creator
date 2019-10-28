package quentin.testcreator.models.data;


import quentin.testcreator.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface QuestionDao extends CrudRepository<Question, Integer> {
}
