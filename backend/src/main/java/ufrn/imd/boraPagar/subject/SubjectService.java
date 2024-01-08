package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    // O(n²) but amortized with cache
    @Cacheable("subjects")
    @Override
    public Page<SubjectModel> findAllByPage(String credential, Pageable pageable) {
        Page<SubjectModel> page = subjectRepository.findAllActiveByPage(pageable);
        for (SubjectModel subject : page.getContent()) {
            for (UserModel user : subject.getInterestedUsers()) {
                user = UserService.getUserWithoutSensitiveInfo(user);
            }
        }
        return page;
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

}
