package ufrn.imd.boraPagar.subject;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ufrn.imd.boraPagar.core.AbstractModel;
import ufrn.imd.boraPagar.user.UserModel; 

@Document("Subject")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectModel extends AbstractModel {
    private int componentID;
    private String componentType, code, level, name, department;
    private int totalHours;

    @Enumerated(EnumType.STRING)
    private SubjectModalityType modality;

    private String equivalencesTxt, requirementsTxt, coRequirementsTxt, coursesTxt;

    @Column(nullable = true)
    @OneToMany
    @JoinColumn(name="subject_id")
    List<SubjectModel> equivalences, requirements, coRequirements;

    @Column(nullable = true)
    @OneToMany
    @JoinColumn(name="subject_id")
    List<String> courses;

    @Builder.Default
    @DBRef(lazy = true)
    private List<UserModel> interestedUsers = new ArrayList<>();
}