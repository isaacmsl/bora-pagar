package ufrn.imd.boraPagar.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

@Component
public class GoogleUserWrapper {

    @Autowired
    private UserRepository userRepository;

    public UserModel persistNewUserOrUpdateExisting(Payload payload) {
        String userId = payload.getSubject();
        String name = setAllToLowerCaseExceptFirstLetter(payload.get("given_name").toString()) + " " + setAllToLowerCaseExceptFirstLetter(payload.get("family_name").toString());
        String username = payload.get("given_name").toString().toLowerCase() + "_" + payload.get("family_name").toString().toLowerCase();
        String email = payload.getEmail();
        String pictureUri = payload.get("picture").toString();
        Optional<UserModel> existingModel = userRepository.findByGoogleId(userId);

        if (!existingModel.isPresent()) {
            UserModel newUser = new UserModel();
            newUser.setName(name);
            newUser.setGoogleId(userId);
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPictureUri(pictureUri);

            return userRepository.save(newUser);
        } else {
            existingModel.get().setLastLoginTime(LocalDateTime.now());
            return userRepository.save(existingModel.get());
        }
    }

    private String setAllToLowerCaseExceptFirstLetter(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return inputString;
        }

        char firstLetter = inputString.charAt(0);

        return firstLetter + inputString.substring(1).toLowerCase();
    }
}
