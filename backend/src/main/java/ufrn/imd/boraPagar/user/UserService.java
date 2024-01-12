package ufrn.imd.boraPagar.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        user.setLastLoginTime(null);
        user.setRegistrationTime(null);
        return user;
    }

    public String welcome(String credential) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        if (user != null) {
            return "Welcome, " + user.getName();
        }

        return "You're not welcome.";
    }

    public UserModel findByGoogleId(String credential, String googleId) {
        return userRepository.findByGoogleId(googleId);
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

    public Page<UserModel> findAllByName(Pageable pageable, String partialName) {
        Page<UserModel> users = userRepository.findAllByName(pageable, partialName);
        
        for (UserModel user : users) {
            user = getUserWithoutSensitiveInfo(user);
        }
        
        return users;
    }

    public Page<UserModel> findAllByUsername(Pageable pageable, String partialUsername) {
        Page<UserModel> users = userRepository.findAllByUsername(pageable, partialUsername);
        
        for (UserModel user : users) {
            user = getUserWithoutSensitiveInfo(user);
        }
        
        return users;
    }
    
    public Page<UserModel> findAllByNameOrderByNameAsc(Pageable pageable) {
        Page<UserModel> users = userRepository.findAllByNameOrderByNameAsc(pageable);
        
        for (UserModel user : users) {
            user = getUserWithoutSensitiveInfo(user);
        }
        
        return users;
    }

    public Page<UserModel> findAllByUsernameOrderByUsernameAsc(Pageable pageable) {
        Page<UserModel> users = userRepository.findAllByUsernameOrderByUsernameAsc(pageable);
        
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
}
