package ufrn.imd.boraPagar.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import ufrn.imd.boraPagar.core.AbstractService;

@Service
@NoRepositoryBean
public class UserService extends AbstractService<UserModel, UserRepository> {

    @Autowired
    UserRepository userRepository;

    public UserModel findByGoogleId(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }

    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserModel> findAllByUsername(String partialUsername) {
        return userRepository.findAllByUsername(partialUsername);
    }
}
