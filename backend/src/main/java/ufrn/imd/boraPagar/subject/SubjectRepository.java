package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.stereotype.Repository;

import ufrn.imd.boraPagar.core.AbstractRepository;

@Repository
public interface SubjectRepository extends AbstractRepository<SubjectModel> {
    SubjectModel findByComponentID(int componentID);
    SubjectModel findByCode(String code);
    List<SubjectModel> findAllByName(String name);
    List<SubjectModel> findAllByModality(SubjectModalityType modality);
    List<SubjectModel> findAllByTotalHours(int totalHours);
    List<SubjectModel> findAllByDepartment(String department);
    List<SubjectModel> findAllByEquivalences(SubjectModel equivalence);
    List<SubjectModel> findAllByRequirements(SubjectModel requirement);
    List<SubjectModel> findAllByCoRequirements(SubjectModel coRequirement);
}