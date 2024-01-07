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

    private UserModel getUserWithoutSensitiveInfo(UserModel user) {
        if (user == null) {
            return null;
        }

        user.setId(null);
        user.setGoogleId(null);
        user.setLastLoginTime(null);
        user.setRegistrationTime(null);
        return user;
    }

    public UserModel findByGoogleId(String credential, String googleId) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            return userRepository.findByGoogleId(googleId);
        }

        return null;
    }

    public UserModel findByUsername(String credential, String username) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            return userRepository.findByUsername(username);
        }

        return null;
    }

    public UserModel findByEmail(String credential, String email) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            return userRepository.findByEmail(email);
        }

        return null;
    }

    public List<UserModel> findAllByUsername(String partialUsername) {
        List<UserModel> users = userRepository.findAllByUsername(partialUsername);
        
        for (UserModel user : users) {
            user = getUserWithoutSensitiveInfo(user);
        }
        
        return users;
    }
}
