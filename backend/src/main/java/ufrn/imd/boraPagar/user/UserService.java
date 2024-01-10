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
        
        if (user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            return userRepository.findByGoogleId(googleId);
        }

        return null;
    }

    public UserModel findByName(String credential, String name) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            return userRepository.findByName(name);
        }

        return null;
    }

    public UserModel findByUsername(String credential, String username) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            return userRepository.findByUsername(username);
        }

        return null;
    }

    public UserModel findByEmail(String credential, String email) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            return userRepository.findByEmail(email);
        }

        return null;
    }

    public List<UserModel> findAllByName(String partialName) {
        List<UserModel> users = userRepository.findAllByName(partialName);
        
        for (UserModel user : users) {
            user = getUserWithoutSensitiveInfo(user);
        }
        
        return users;
    }

    public List<UserModel> findAllByUsername(String partialUsername) {
        List<UserModel> users = userRepository.findAllByUsername(partialUsername);
        
        for (UserModel user : users) {
            user = getUserWithoutSensitiveInfo(user);
        }
        
        return users;
    }

    public List<UserModel> findAllByRole(String credential, RoleEnum role) {
        UserModel user = getExistingOrNewUserFromCredential(credential);

        if(user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            List<UserModel> users = userRepository.findAllByRole(role);

            for (UserModel obj : users) {
                obj = getUserWithoutSensitiveInfo(obj);
            }
            
            return users;
        }

        return null;
    }

    public UserModel addFriend(String credential, String googleId) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        UserModel newFriend = userRepository.findByGoogleId(googleId);

        if(user != null && newFriend != null && !user.getFriends().contains(newFriend)) {
            user.getFriends().add(newFriend);
            
            return userRepository.save(user);
        }

        return null;
    }
    
    public UserModel removeFriend(String credential, String googleId) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        UserModel friend = userRepository.findByGoogleId(googleId);

        if(user != null && friend != null && user.getFriends().contains(friend)) {
            user.getFriends().remove(friend);
            
            return userRepository.save(user);
        }

        return null;
    }
}
