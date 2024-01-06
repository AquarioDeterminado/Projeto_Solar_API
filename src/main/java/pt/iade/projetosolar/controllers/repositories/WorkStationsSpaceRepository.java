package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.workstations.WorkStationsSpace;

public interface WorkStationsSpaceRepository extends CrudRepository<WorkStationsSpace, Integer> { }
