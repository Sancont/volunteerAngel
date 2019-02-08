package org.Sandra.models.data;

import org.Sandra.models.Ministry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MinistryDao extends CrudRepository<Ministry, Integer> {
}
