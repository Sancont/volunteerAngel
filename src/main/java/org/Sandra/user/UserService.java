package org.Sandra.user;

import org.Sandra.forms.UserForm;
import org.Sandra.models.User;

/**
 * Created by Chris Bay
 */
public interface UserService {

    public User save(UserForm userForm) throws EmailExistsException;
    public User findByEmail(String email);

}
