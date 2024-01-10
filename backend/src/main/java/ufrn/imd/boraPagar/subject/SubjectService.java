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
    
    public SubjectModel addInterestedUserByComponentID(String credential, String componentID) {
        SubjectModel subject = subjectRepository.findByComponentID(componentID);
        System.out.println(subject);
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

    public SubjectModel removeInterestedUserByComponentID(String credential, String componentID) {
        SubjectModel subject = subjectRepository.findByComponentID(componentID);
        System.out.println(subject);
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

    public Page<SubjectModel> findAllByInterestedUserWithGoogleId(String userGoogleId, Pageable pageable) {
        UserModel user = userService.findByGoogleId("", userGoogleId);
        return subjectRepository.findAllByInterestedUsers(pageable, user);
    }

    @Override
    public Page<SubjectModel> findAllByPage(String credential, Pageable pageable) {
        return subjectRepository.findAllActiveByPage(pageable);
    }

    public SubjectModel findByComponentID(String id) {
        return subjectRepository.findByComponentID(id);
    }

    public SubjectModel findByCode(String code) {
        return subjectRepository.findByCode(code);
    }
    
    public List<SubjectModel> findAllByModality(SubjectModalityType modality) {
        return subjectRepository.findAllByModality(modality);
    }

    public List<SubjectModel> findAllByTotalHours(int totalHours) {
        return subjectRepository.findAllByTotalHours(totalHours);
    }

    public Page<SubjectModel> findAllByNameAndDepartment(Pageable pageable, String name, String department) {
        return subjectRepository.findAllByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(pageable, name, department);
    }

}
