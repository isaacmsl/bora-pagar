package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufrn.imd.boraPagar.core.AbstractController;

@RestController
@RequestMapping("subjects")
public class SubjectController extends AbstractController<SubjectModel, SubjectService>{
    @RequestMapping(method = RequestMethod.GET, params = {"componentID"})
    public ResponseEntity<SubjectModel> findByComponentID(@RequestParam int componentID) {
        return ResponseEntity.ok().body(service.findByComponentID(componentID));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<SubjectModel> findByName(@RequestParam String name) {
        return ResponseEntity.ok().body(service.findByName(name));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"code"})
    public ResponseEntity<SubjectModel> findByCode(@RequestParam String code) {
        return ResponseEntity.ok().body(service.findByCode(code));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"totalHours"})
    public ResponseEntity<List<SubjectModel>> findAllByTotalHours(@RequestParam int totalHours) {
        return ResponseEntity.ok().body(service.findAllByTotalHours(totalHours));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"department"})
    public ResponseEntity<List<SubjectModel>> findAllByTotalHours(@RequestParam String department) {
        return ResponseEntity.ok().body(service.findAllByDepartment(department));
    }
}