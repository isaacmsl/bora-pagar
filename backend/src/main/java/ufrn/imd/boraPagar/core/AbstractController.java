package ufrn.imd.boraPagar.core;

// import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@CrossOrigin(origins = "*", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE
})
public class AbstractController <M extends AbstractModel, S extends AbstractService<M, ?>> {
    @Autowired
    protected S service;
    
    protected final String USER_HEADER_TOKEN_NAME = "credential";

    // @GetMapping("/findAll")
    // public ResponseEntity<List<M>> findAll(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential) {
    //     List<M> listResult = (List<M>) service.findAll(credential);
    //     return ResponseEntity.ok().body(listResult);
    // }

    @GetMapping("/findAll")
    public Page<M> findAll(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, Pageable pageable) {
        Page<M> listResult = service.findAllByPage(credential, pageable);
        return listResult;
    }

    @GetMapping("/{id}")
    public ResponseEntity<M> findById(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @PathVariable String id) {
        Optional<M> opModel = service.findById(credential, id);
        if (!opModel.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(opModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<M> deleteById(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @PathVariable String id) {
        Optional<M> opModel = service.findById(credential, id);
        if (!opModel.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        M model = opModel.get();
        service.delete(credential, model);
        return ResponseEntity.ok().body(model);
    }

    @PostMapping
    public ResponseEntity<M> post(@RequestHeader(USER_HEADER_TOKEN_NAME) String credential, @RequestBody M saveModel) {
        M result = null;

        try {
            result = (M) service.save(credential, saveModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}