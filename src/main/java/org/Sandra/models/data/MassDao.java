package org.Sandra.models.data;

import org.Sandra.models.Mass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MassDao extends CrudRepository<Mass,Integer> {
}
