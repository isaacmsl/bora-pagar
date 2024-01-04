package ufrn.imd.boraPagar.user;

import ufrn.imd.boraPagar.core.AbstractRepository;

public interface UserRepository extends AbstractRepository<UserModel> {
    UserModel findByGoogleId(String googleId);
    UserModel findByEmail(String email);
    UserModel findByUsername(String username);
}
