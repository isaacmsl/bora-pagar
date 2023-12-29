package ufrn.imd.boraPagar.subject;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SubjectTests {

    @Autowired
    SubjectRepository repository;
    SubjectModel subject;
    SubjectModel subject2;
    SubjectModel subject3;

    @BeforeClass
    public void setUp() throws Exception {        
        subject = repository.save(
            new SubjectModel(
                1,
                "tipo do componente",
                "DIM1234",
                "Gradução",
                "FMC 1",          //
                "Dimap",         //
                60,             //
                null,          //equivalence
                null,         //requirements
                null,        //corequirements
                null
            )
        );

        subject2 = repository.save(
            new SubjectModel(
                2,
                "tipo do componente",
                "DIM1235",
                "Gradução",
                "FMC 2",
                "Dimap",
                60,
                null,
                null,
                null,
                null
            )
        );

    }

    @AfterClass
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, repository.findById(subject.getId()).isPresent());
    }

    @Test
    public void shouldFindByName() {
        Assert.assertNotNull(repository.findByName(subject.getName()));
    }

    @Test
    public void shouldFindByCode() {
        Assert.assertNotNull(repository.findByCode(subject.getCode()));
    }

    //does not make sense find one by hour or by department
    @Test
    public void shouldFindAllByHour() {
        Assert.assertNotNull(repository.findAllByHour(subject.getHour()));
    }
    
    @Test
    public void shouldFindAllByDepartment() {
        Assert.assertNotNull(repository.findAllByDepartment(subject.getDepartment()));
    }

    @After
    public void insertSubjects() {
        subject.getRequirements().add(subject2);
        subject.getCorequirements().add(subject2);
        subject.getEquivalence().add(subject2);
    }

    @Test
    public void shouldFindAllByRequeriments() {
        Assert.assertNotNull(repository.findAllByRequeriments(subject2.getName()));
    }

    @Test
    public void shouldFindAllByCorequeriments() {
        Assert.assertNotNull(repository.findAllByCorequeriments(subject2.getName()));
    }

    @Test
    public void shouldFindAllByEquivalence() {
        Assert.assertNotNull(repository.findAllByEquivalence(subject2.getName()));
    }

    @After
    public void deleteOneSubject() {
        repository.deleteById(1);
    }

    @Test
    public void shouldBeEmptyFindById() {
        Assert.assertNull(repository.findById(subject.getId()));
    }

    @Test
    public void shouldBeEmptyFindByName() {
        Assert.assertNull(repository.findByName(subject.getName()));
    }

    @Test
    public void shouldBeEmptyFindByCode() {
        Assert.assertNull(repository.findByCode(subject.getCode()));
    }
    
    @Test
    public void shouldBeEmpty() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }

}