package ufrn.imd.boraPagar.user;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ufrn.imd.boraPagar.core.AbstractModel;

@Document("User")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends AbstractModel  {
    private String username;
    private String email;
    private String pictureUri;
    private String googleId;

    @Builder.Default
    private LocalDateTime lastLoginTime = LocalDateTime.now(), registrationTime = LocalDateTime.now();
}