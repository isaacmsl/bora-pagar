package ufrn.imd.boraPagar.subject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import ufrn.imd.boraPagar.core.AbstractRepository;
import ufrn.imd.boraPagar.user.UserModel;

@Repository
public interface SubjectRepository extends AbstractRepository<SubjectModel> {
    Optional<SubjectModel> findByComponentID(String componentID);
    Optional<SubjectModel> findByCode(String code);
    List<SubjectModel> findAllByModality(SubjectModalityType modality);
    List<SubjectModel> findAllByTotalHours(int totalHours);
    List<SubjectModel> findAllByEquivalences(SubjectModel equivalence);
    List<SubjectModel> findAllByRequirements(SubjectModel requirement);
    List<SubjectModel> findAllByCoRequirements(SubjectModel coRequirement);
    Page<SubjectModel> findAllByNameContainingIgnoreCaseAndDepartmentContainingIgnoreCaseAndCodeContainingIgnoreCase(Pageable pageable, String name, String department, String code);
    Page<SubjectModel> findAllByInterestedUsers(Pageable pageable, UserModel user);
}