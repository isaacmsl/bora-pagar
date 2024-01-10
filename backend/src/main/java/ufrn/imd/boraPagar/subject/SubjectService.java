package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import ufrn.imd.boraPagar.core.AbstractService;
import ufrn.imd.boraPagar.user.UserModel;
import ufrn.imd.boraPagar.user.UserService;

@Service
@NoRepositoryBean
public class SubjectService extends AbstractService<SubjectModel, SubjectRepository> {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    UserService userService;
    
    public SubjectModel addInterestedUserByCode(String credential, String code) {
        SubjectModel subject = subjectRepository.findByCode(code);
        UserModel user = userService.getExistingOrNewUserFromCredential(credential);
        if (subject != null &&  user != null) {
            List<UserModel> interestedUsers = subject.getInterestedUsers();
            if (!interestedUsers.contains(user)) {
                interestedUsers.add(user);
                return subjectRepository.save(subject);
            }
        }

        return null;
    }

    public SubjectModel removeInterestedUserByCode(String credential, String code) {
        SubjectModel subject = subjectRepository.findByCode(code);
        UserModel user = userService.getExistingOrNewUserFromCredential(credential);
        if (subject != null &&  user != null) {
            List<UserModel> interestedUsers = subject.getInterestedUsers();
            if (interestedUsers.contains(user)) {
                interestedUsers.remove(user);
                return subjectRepository.save(subject);
            }
        }

        return null;
    }

    @Override
    public Page<SubjectModel> findAllByPage(String credential, Pageable pageable) {
        return subjectRepository.findAllActiveByPage(pageable);
    }

    public SubjectModel findByComponentID(int id) {
        return subjectRepository.findByComponentID(id);
    }

    public SubjectModel findByCode(String code) {
        return subjectRepository.findByCode(code);
    }
 
    public List<SubjectModel> findAllByName(String name) {
        return subjectRepository.findAllByName(name);
    }
    
    public List<SubjectModel> findAllByModality(SubjectModalityType modality) {
        return subjectRepository.findAllByModality(modality);
    }

    public List<SubjectModel> findAllByTotalHours(int totalHours) {
        return subjectRepository.findAllByTotalHours(totalHours);
    }

    public List<SubjectModel> findAllByDepartment(String department) {
        return subjectRepository.findAllByDepartment(department);
    }

    public Page<SubjectModel> findAllByNameAndDepartment(Pageable pageable, String name, String department) {
        return subjectRepository.findAllByNameContainingIgnoreCaseAndDepartment(pageable, name, department);
    }

}
