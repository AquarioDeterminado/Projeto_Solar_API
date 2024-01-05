package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
    User findByIdAndPassword(int id, String password);
}
