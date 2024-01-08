package ufrn.imd.boraPagar.user;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String name;
    private String username;
    @JsonIgnore
    private String email;
    private String pictureUri;
    @JsonIgnore
    private String googleId;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private LocalDateTime lastLoginTime = LocalDateTime.now(), registrationTime = LocalDateTime.now();
}
