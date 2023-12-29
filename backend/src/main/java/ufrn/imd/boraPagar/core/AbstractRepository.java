package ufrn.imd.boraPagar.core;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository <M extends AbstractModel> extends MongoRepository<M, String> {
}