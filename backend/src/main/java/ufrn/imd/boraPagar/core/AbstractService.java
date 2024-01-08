package ufrn.imd.boraPagar.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import ufrn.imd.boraPagar.user.GoogleUserWrapper;
import ufrn.imd.boraPagar.user.UserModel;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public abstract class AbstractService <M extends AbstractModel, R extends AbstractRepository<M>> {
    @Autowired
    private R repository;

    // @Autowired
    // private LogRepository log;

    @Autowired
    private GoogleUserWrapper googleUserWrapper;

    private final String GOOGLE_CLIENT_ID = "384114633752-8jn7olobqn2e44sj7mdlahib0r70s5cv.apps.googleusercontent.com";

    protected UserModel getExistingOrNewUserFromCredential(String credential) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(Collections
                        .singletonList(GOOGLE_CLIENT_ID))
                .build();

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(credential);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                return googleUserWrapper.persistNewUserOrUpdateExisting(payload);
            } else {
                return null;
            }
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
        
    }

    public List<M> findAll(String credential) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            return repository.findAllActive();
        }

        return null;
    }

    public Page<M> findAllByPage(String credential, Pageable pageable) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            return repository.findAllActiveByPage(pageable);
        }

        return null;
    }
    
    public Optional<M> findById(String credential, String id) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            return repository.findById(id);
        }

        return Optional.empty();
    }

    public synchronized M save(String credential, M model) throws Exception {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            M savedModel = repository.save(model);
            // log.save(new LogModel("System", "has saved/updated a " + model.getClass().getSimpleName(), model.getId(), null, LocalDateTime.now()));
            return savedModel;
        }

        return null;
    }

    public void delete(String credential, M model) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            model.setIsActive(false);
            repository.save(model);
            // log.save(new LogModel("System", "has deleted a " + model.getClass().getSimpleName(), model.getId(), null, LocalDateTime.now()));
        }
    }

}