package ufrn.imd.boraPagar.user;

import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import ufrn.imd.boraPagar.core.AbstractRepository;

public interface UserRepository extends AbstractRepository<UserModel> {
    UserModel findByGoogleId(String googleId);
    UserModel findByEmail(String email);
    UserModel findByName(String name);
    UserModel findByUsername(String username);
    
    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    List<UserModel> findAllByName(String partialName);

    @Query("{ 'username' : { $regex: ?0, $options: 'i' } }")
    List<UserModel> findAllByUsername(String partialUsername);

    List<UserModel> findAllByRole(RoleEnum role);
}
