package ufrn.imd.boraPagar.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import ufrn.imd.boraPagar.core.AbstractService;
import ufrn.imd.boraPagar.core.ApplicationConstants;
import ufrn.imd.boraPagar.exceptions.ResourceNotFoundException;

@Service
@NoRepositoryBean
public class UserService extends AbstractService<UserModel, UserRepository> {

    @Autowired
    UserRepository userRepository;

    public String welcome(String credential) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        if (user != null) {
            return "Welcome, " + user.getName();
        }

        return "You're not welcome.";
    }

    public Optional<UserModel> findByGoogleId(String credential, String googleId) {
        return Optional.ofNullable(userRepository.findByGoogleId(googleId).orElseThrow(
            () -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE)));
    }

    public Optional<UserModel> findByName(String credential, String name) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            return Optional.ofNullable(userRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE)));
        }

        return Optional.empty();
    }

    public Optional<UserModel> findByUsername(String credential, String username) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE)));
        }

        return Optional.empty();
    }

    public Optional<UserModel> findByEmail(String credential, String email) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            return Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE)));
        }

        return Optional.empty();
    }

    public Page<UserModel> findAllByName(Pageable pageable, String partialName) {
        Page<UserModel> users = userRepository.findAllByName(pageable, partialName);
        
        return users;
    }

    public Page<UserModel> findAllByUsername(Pageable pageable, String partialUsername) {
        Page<UserModel> users = userRepository.findAllByUsername(pageable, partialUsername);
        
        return users;
    }
    
    public Page<UserModel> findAllByNameOrderByNameAsc(Pageable pageable) {
        Page<UserModel> users = userRepository.findAllByNameOrderByNameAsc(pageable);
        return users;
    }

    public Page<UserModel> findAllByUsernameOrderByUsernameAsc(Pageable pageable) {
        Page<UserModel> users = userRepository.findAllByUsernameOrderByUsernameAsc(pageable);
        return users;
    }

    public List<UserModel> findAllByRole(String credential, RoleEnum role) {
        UserModel user = getExistingOrNewUserFromCredential(credential);

        if(user != null && user.getRole() == RoleEnum.ROLE_ADMIN) {
            List<UserModel> users = userRepository.findAllByRole(role);
            
            return users;
        }

        return null;
    }
}
