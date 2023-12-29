package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.stereotype.Repository;

import ufrn.imd.boraPagar.core.AbstractRepository;

@Repository
public interface SubjectRepository extends AbstractRepository<SubjectModel> {
    SubjectModel findByComponentID(int componentID);
    SubjectModel findByName(String name);
    SubjectModel findByCode(String code);
    List<SubjectModel> findAllByTotalHours(int totalHours);
    List<SubjectModel> findAllByDepartment(String department);
    List<SubjectModel> findAllByEquivalence(SubjectModel equivalence);
    List<SubjectModel> findAllByRequirement(SubjectModel requirement);
    List<SubjectModel> findAllByCoRequirement(SubjectModel coRequirement);
}