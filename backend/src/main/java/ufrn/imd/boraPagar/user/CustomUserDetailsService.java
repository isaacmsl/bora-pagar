package ufrn.imd.boraPagar.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void processUserDetails(OidcUser oidcUser) {
        String userId = oidcUser.getName();
        String username = oidcUser.getGivenName().toLowerCase() + "_" + oidcUser.getFamilyName().toLowerCase();
        String email = oidcUser.getEmail();
        String pictureUri = oidcUser.getPicture();
        UserModel existingModel = userRepository.findByGoogleId(userId);

        if (existingModel == null) {
            UserModel newUser = new UserModel();
            newUser.setGoogleId(userId);
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPictureUri(pictureUri);

            userRepository.save(newUser);
        } else {
            existingModel.setLastLoginTime(LocalDateTime.now());
            userRepository.save(existingModel);
        }
    }
}