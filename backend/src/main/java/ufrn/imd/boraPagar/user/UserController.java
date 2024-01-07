package ufrn.imd.boraPagar.user;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufrn.imd.boraPagar.core.AbstractController;

@RestController
@RequestMapping("users")
public class UserController extends AbstractController<UserModel, UserService>{
    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    public ResponseEntity<UserModel> findByUsername(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestParam String username) {
        return ResponseEntity.ok().body(service.findByUsername(credential, username));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"email"})
    public ResponseEntity<UserModel> findByEmail(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestParam String email) {
        return ResponseEntity.ok().body(service.findByEmail(credential, email));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"googleId"})
    public ResponseEntity<UserModel> findByGoogleId(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestParam String googleId) {
        return ResponseEntity.ok().body(service.findByGoogleId(credential, googleId));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"partialUsername"})
    public ResponseEntity<List<UserModel>> findAllByUsername(@RequestParam String partialUsername) {
        return ResponseEntity.ok().body(service.findAllByUsername(partialUsername));
    }
}