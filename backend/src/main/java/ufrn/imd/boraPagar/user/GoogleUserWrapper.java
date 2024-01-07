package ufrn.imd.boraPagar.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

@Component
public class GoogleUserWrapper {

    @Autowired
    private UserRepository userRepository;

    public UserModel persistNewUserOrUpdateExisting(Payload payload) {
        String userId = payload.getSubject();
        String username = payload.get("given_name").toString().toLowerCase() + "_" + payload.get("family_name").toString().toLowerCase();
        String email = payload.getEmail();
        String pictureUri = payload.get("picture").toString();
        UserModel existingModel = userRepository.findByGoogleId(userId);

        if (existingModel == null) {
            UserModel newUser = new UserModel();
            newUser.setGoogleId(userId);
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPictureUri(pictureUri);

            return userRepository.save(newUser);
        } else {
            existingModel.setLastLoginTime(LocalDateTime.now());
            return userRepository.save(existingModel);
        }
    }
}