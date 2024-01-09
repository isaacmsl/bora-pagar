package ufrn.imd.boraPagar.user;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ufrn.imd.boraPagar.core.AbstractModel;
import ufrn.imd.boraPagar.core.Views;

@Document("User")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends AbstractModel  {
    private String name, username, pictureUri;

    @JsonView(Views.Admin.class)
    private String email, googleId;

    @JsonView(Views.Admin.class)
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private LocalDateTime lastLoginTime = LocalDateTime.now(), registrationTime = LocalDateTime.now();

    private RoleEnum role;
}
