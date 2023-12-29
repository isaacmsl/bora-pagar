package ufrn.imd.boraPagar.core;

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
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*", methods = { 
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.DELETE
})
public class AbstractController <M extends AbstractModel, S extends AbstractService> {
    @Autowired
    protected S service;

    @GetMapping("/findAll")
    public ResponseEntity<List<M>> findAll() {
        List<M> listResult = (List<M>) service.findAll();

        return ResponseEntity.ok().body(listResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<M> findById(@PathVariable String id) {
        Optional<M> opModel = service.findById(id);
        if (!opModel.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(opModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<M> deleteById(@PathVariable String id) {
        Optional<M> opModel = service.findById(id);
        if (!opModel.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        M model = opModel.get();
        service.delete(model);
        return ResponseEntity.ok().body(model);
    }

    @PostMapping
    public ResponseEntity<M> post(@RequestBody M saveModel) {
        M result = null;
        try {
            result = (M) service.save(saveModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}