package ufrn.imd.boraPagar.subject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import ufrn.imd.boraPagar.core.AbstractService;
import ufrn.imd.boraPagar.core.ApplicationConstants;
import ufrn.imd.boraPagar.exceptions.ResourceNotFoundException;
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
        SubjectModel subject = subjectRepository.findByComponentID(componentID).orElseThrow(() -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE));
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
        SubjectModel subject = subjectRepository.findByComponentID(componentID).orElseThrow(() -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE));
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
        UserModel user = userService.findByGoogleId("", userGoogleId).get();
        return subjectRepository.findAllByInterestedUsers(pageable, user);
    }

    @Override
    public Page<SubjectModel> findAllByPage(String credential, Pageable pageable) {
        return subjectRepository.findAllActiveByPage(pageable);
    }

    public Optional<SubjectModel> findByComponentID(String id) {
        return Optional.ofNullable(subjectRepository.findByComponentID(id).orElseThrow(
            () -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE)));
    }

    public Optional<SubjectModel> findByCode(String code) {
        return Optional.ofNullable(subjectRepository.findByCode(code).orElseThrow(
            () -> new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_MESSAGE)));
    }
    
    public List<SubjectModel> findAllByModality(SubjectModalityType modality) {
        return subjectRepository.findAllByModality(modality);
    }

    public List<SubjectModel> findAllByTotalHours(int totalHours) {
        return subjectRepository.findAllByTotalHours(totalHours);
    }

    public Page<SubjectModel> findAllByNameAndDepartmentAndCode(Pageable pageable, String name, String department, String code) {
        return subjectRepository.findAllByNameAndDepartmentAndCode(pageable, name, department, code);
    }

}
