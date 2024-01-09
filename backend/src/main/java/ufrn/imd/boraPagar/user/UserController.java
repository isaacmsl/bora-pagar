package ufrn.imd.boraPagar.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ufrn.imd.boraPagar.core.AbstractController;
import ufrn.imd.boraPagar.core.Views;

@RestController
@RequestMapping("users")
public class UserController extends AbstractController<UserModel, UserService>{
    @JsonView(Views.Admin.class)
    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<UserModel> findByName(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestParam String name) {
        return ResponseEntity.ok().body(service.findByName(credential, name));
    }

    @JsonView(Views.Admin.class)
    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    public ResponseEntity<UserModel> findByUsername(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestParam String username) {
        return ResponseEntity.ok().body(service.findByUsername(credential, username));
    }

    @JsonView(Views.Admin.class)
    @RequestMapping(method = RequestMethod.GET, params = {"email"})
    public ResponseEntity<UserModel> findByEmail(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestParam String email) {
        return ResponseEntity.ok().body(service.findByEmail(credential, email));
    }

    @JsonView(Views.Admin.class)
    @RequestMapping(method = RequestMethod.GET, params = {"googleId"})
    public ResponseEntity<UserModel> findByGoogleId(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestParam String googleId) {
        return ResponseEntity.ok().body(service.findByGoogleId(credential, googleId));
    }

    @JsonView(Views.Public.class)
    @RequestMapping(method = RequestMethod.GET, params = {"partialName"})
    public Page<UserModel> findAllByName(@RequestParam String partialName, Pageable pageable) {
        return service.findAllByName(pageable, partialName);
    }

    @JsonView(Views.Public.class)
    @RequestMapping(method = RequestMethod.GET, params = {"partialUsername"})
    public Page<UserModel> findAllByUsername(@RequestParam String partialUsername, Pageable pageable) {
        return service.findAllByUsername(pageable, partialUsername);
    }

    @JsonView(Views.Admin.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserModel>> findAllByRole(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestHeader("role") RoleEnum role) {
        return ResponseEntity.ok().body(service.findAllByRole(credential, role));
    }
}