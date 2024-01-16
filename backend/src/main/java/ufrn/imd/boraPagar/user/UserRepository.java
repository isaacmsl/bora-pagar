package ufrn.imd.boraPagar.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import ufrn.imd.boraPagar.core.AbstractRepository;

public interface UserRepository extends AbstractRepository<UserModel> {
    Optional<UserModel> findByGoogleId(String googleId);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByName(String name);
    Optional<UserModel> findByUsername(String username);
    
    @Query("{ 'name' : { $regex: ?0, $options: 'i' } }")
    Page<UserModel> findAllByName(Pageable pageable, String partialName);

    @Query("{ 'username' : { $regex: ?0, $options: 'i' } }")
    Page<UserModel> findAllByUsername(Pageable pageable, String partialUsername);

    List<UserModel> findAllByRole(RoleEnum role);

    @Query("{ 'name' : { $exists: true }}")
    Page<UserModel> findAllByNameOrderByNameAsc(Pageable pageable);

    @Query("{ 'username' : { $exists: true }}")
    Page<UserModel> findAllByUsernameOrderByUsernameAsc(Pageable pageable);
}
