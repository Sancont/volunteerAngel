package org.Sandra.models.data;

import org.Sandra.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Chris Bay
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
}
