package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.coworks.CoWork;

public interface CoWorkRepository extends CrudRepository<CoWork, Integer> { }
