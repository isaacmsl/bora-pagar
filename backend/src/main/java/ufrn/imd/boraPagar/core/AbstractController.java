package ufrn.imd.boraPagar.core;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import ufrn.imd.boraPagar.user.GoogleUserWrapper;
import ufrn.imd.boraPagar.user.UserModel;

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE
})
public class AbstractController <M extends AbstractModel, S extends AbstractService<M, ?>> {
    @Autowired
    protected S service;

    @Autowired
    private GoogleUserWrapper googleUserWrapper;

    private UserModel getExistingOrNewUserFromCredential(String credential) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(Collections
                        .singletonList("384114633752-8jn7olobqn2e44sj7mdlahib0r70s5cv.apps.googleusercontent.com"))
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

    @GetMapping("/findAll")
    public ResponseEntity<List<M>> findAll(@RequestHeader("credential") String credential) {
        UserModel user = getExistingOrNewUserFromCredential(credential);
        
        if (user != null) {
            List<M> listResult = (List<M>) service.findAll();

            return ResponseEntity.ok().body(listResult);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<M> findById(@RequestHeader("credential") String credential, @PathVariable String id) {
        UserModel user = getExistingOrNewUserFromCredential(credential);

        if (user != null) {
            Optional<M> opModel = service.findById(id);
            if (!opModel.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(opModel.get());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<M> deleteById(@RequestHeader("credential") String credential, @PathVariable String id) {
        UserModel user = getExistingOrNewUserFromCredential(credential);

        if (user != null) {
            Optional<M> opModel = service.findById(id);
            if (!opModel.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            M model = opModel.get();
            service.delete(model);
            return ResponseEntity.ok().body(model);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping
    public ResponseEntity<M> post(@RequestHeader("credential") String credential, @RequestBody M saveModel) {
        M result = null;

        UserModel user = getExistingOrNewUserFromCredential(credential);

        if (user != null) {
            try {
                result = (M) service.save(saveModel);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}