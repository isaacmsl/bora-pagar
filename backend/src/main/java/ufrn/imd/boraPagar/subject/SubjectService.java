package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import ufrn.imd.boraPagar.core.AbstractService;

@Service
@NoRepositoryBean
public class SubjectService extends AbstractService<SubjectModel, SubjectRepository> {

    @Autowired
    SubjectRepository subjectRepository;
    

    @Cacheable("subjects")
    public List<SubjectModel> findAll() {
        return subjectRepository.findAllActive();
    }

    @Override
    public List<SubjectModel> findAll(String credential) {
        return subjectRepository.findAllActive();
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
