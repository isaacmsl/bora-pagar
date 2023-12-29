package ufrn.imd.boraPagar.core;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public abstract class AbstractService <M extends AbstractModel, R extends AbstractRepository<M>> {
    @Autowired
    private R repository;

    // @Autowired
    // private LogRepository log;

    public List<M> findAll() {
        return repository.findAll();
    }
    
    public Optional<M> findById(String id) {
        return repository.findById(id);
    }

    public synchronized M save(M model) throws Exception {
        M savedModel = repository.save(model);
        // log.save(new LogModel("System", "has saved/updated a " + model.getClass().getSimpleName(), model.getId(), null, LocalDateTime.now()));
        return savedModel;
    }

    public void delete(M model) {
        repository.delete(model);
        // log.save(new LogModel("System", "has deleted a " + model.getClass().getSimpleName(), model.getId(), null, LocalDateTime.now()));
    }

}