package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ufrn.imd.boraPagar.core.AbstractController;

@RestController
@RequestMapping("subjects")
public class SubjectController extends AbstractController<SubjectModel, SubjectService>{

    @Override
    @GetMapping("/findAll")
    public Page<SubjectModel> findAll(@RequestHeader(value = USER_HEADER_TOKEN_NAME, required = false) String credential, Pageable pageable) {
        Page<SubjectModel> listResult = service.findAllByPage(credential, pageable);
        return listResult;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"componentID"})
    public ResponseEntity<SubjectModel> findByComponentID(@RequestParam int componentID) {
        return ResponseEntity.ok().body(service.findByComponentID(componentID));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<List<SubjectModel>> findAllByName(@RequestParam String name) {
        return ResponseEntity.ok().body(service.findAllByName(name));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"code"})
    public ResponseEntity<SubjectModel> findByCode(@RequestParam String code) {
        return ResponseEntity.ok().body(service.findByCode(code));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"modality"})
    public ResponseEntity<List<SubjectModel>> findAllByModality(@RequestParam SubjectModalityType modality) {
        return ResponseEntity.ok().body(service.findAllByModality(modality));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"totalHours"})
    public ResponseEntity<List<SubjectModel>> findAllByTotalHours(@RequestParam int totalHours) {
        return ResponseEntity.ok().body(service.findAllByTotalHours(totalHours));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"department"})
    public ResponseEntity<List<SubjectModel>> findAllByDepartment(@RequestParam String department) {
        return ResponseEntity.ok().body(service.findAllByDepartment(department));
    }
}