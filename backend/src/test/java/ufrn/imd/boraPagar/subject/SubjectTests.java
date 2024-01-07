package ufrn.imd.boraPagar.subject;

import java.util.ArrayList;

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
    SubjectModel subjectA, subjectB;

    @Before
    public void setUp() throws Exception {
        subjectA = repository.save(
            new SubjectModel(
                1,
                "Disciplina",
                "DIM1234",
                "Gradução",
                "FMC 1",
                "DIMAp",
                60,
                SubjectModalityType.IN_PERSON,
                "( DIM0001 )",
                "( DIM0001 ) OU ( DIM0002 )",
                "( DIM0001 ) OU ( DIM0002 )",
                "( DIM0001 ) OU ( DIM0002 ) OU ( DIM0003 )",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
            )
        );

        subjectB = repository.save(
            new SubjectModel(
                2,
                "Disciplina",
                "DIM1235",
                "Gradução",
                "FMC 2",
                "DIMAp",
                90,
                SubjectModalityType.IN_PERSON,
                "( DIM0001 )",
                "( DIM0001 ) OU ( DIM0002 )",
                "( DIM0001 ) OU ( DIM0002 )",
                "( DIM0001 ) OU ( DIM0002 ) OU ( DIM0003 )",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
            )
        );


        subjectA.getEquivalences().add(subjectB);
        subjectA.getCoRequirements().add(subjectB);
        subjectA.getRequirements().add(subjectB);
        repository.save(subjectA);
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldFindAllByModality() {
        Assert.assertEquals(2, repository.findAllByModality(subjectA.getModality()).size());
    }

    @Test
    public void shouldFindById() {
        Assert.assertEquals(true, repository.findById(subjectA.getId()).isPresent());
    }

    @Test
    public void shouldFindByComponentID() {
        Assert.assertNotNull(repository.findByComponentID(subjectA.getComponentID()));
    }

    @Test void shouldFindAllByName() {
        Assert.assertEquals(1, repository.findAllByName(subjectA.getName()).size());
    }

    @Test
    public void shouldFindByCode() {
        Assert.assertNotNull(repository.findByCode(subjectA.getCode()));
    }

    @Test
    public void shouldFindAllByHour() {
        Assert.assertEquals(1, repository.findAllByTotalHours(subjectA.getTotalHours()).size());
    }
    
    @Test
    public void shouldFindAllByDepartment() {
        Assert.assertEquals(2, repository.findAllByDepartment(subjectA.getDepartment()).size());
    }

    @Test
    public void shouldFindAllByRequirements() {
        Assert.assertEquals(true, repository.findAllByRequirements(subjectB).contains(subjectA));
    }

    @Test
    public void shouldFindAllByCorequeriments() {
        Assert.assertEquals(true, repository.findAllByCoRequirements(subjectB).contains(subjectA));
    }

    @Test
    public void shouldFindAllByEquivalence() {
        Assert.assertEquals(true, repository.findAllByEquivalences(subjectB).contains(subjectA));
    }

    @Test
    public void deleteById() {
        repository.deleteById(subjectA.getId());
        Assert.assertFalse(repository.findById(subjectA.getId()).isPresent());
    }

    @Test
    public void shouldBeEmpty() {
        repository.deleteAll();
        Assert.assertEquals(0, repository.count());
    }

}