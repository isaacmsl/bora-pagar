package ufrn.imd.boraPagar.subject;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import ufrn.imd.boraPagar.user.UserModel;
import ufrn.imd.boraPagar.user.UserRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SubjectTests {

    @Autowired
    SubjectRepository repository;
    SubjectModel subjectA, subjectB;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() throws Exception {
        subjectA = repository.save(
            new SubjectModel(
                "1",
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
                new ArrayList<>(),
                new ArrayList<>()
            )
        );

        subjectB = repository.save(
            new SubjectModel(
                "2",
                "Disciplina",
                "DIM2135",
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
                new ArrayList<>(),
                new ArrayList<>()
            )
        );


        subjectA.getEquivalences().add(subjectB);
        subjectA.getCoRequirements().add(subjectB);
        subjectA.getRequirements().add(subjectB);
        repository.save(subjectA);
    }

    @AfterEach
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldFindUser() {
        UserModel user = new UserModel();
        user.setEmail("isaac.lourenco.704@ufrn.edu.br");
        user.setName("Isaac Lourenço");
        user.setGoogleId("123");
        user.setPictureUri("https://bonitao.com");
        user.setUsername("V1d4isaacL0uk4");

        user = userRepository.save(user);
        subjectA.getInterestedUsers().add(user);
        subjectA = repository.save(subjectA);
        Assert.assertTrue(subjectA.getInterestedUsers().contains(user));
        userRepository.delete(user);
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

    @Test
    public void shouldFindByCode() {
        Assert.assertNotNull(repository.findByCode(subjectA.getCode()));
    }

    @Test
    public void shouldFindAllByHour() {
        Assert.assertEquals(1, repository.findAllByTotalHours(subjectA.getTotalHours()).size());
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
    public void shouldPageZeroHasLengthTwo() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<SubjectModel> subPage = repository.findAllActiveByPage(pageable);

        Assert.assertEquals(2, subPage.getContent().size());
    }

    @Test
    public void shouldPageOneHasLengthZero() {
        Pageable pageable = PageRequest.of(1, 2);
        Page<SubjectModel> subPage = repository.findAllActiveByPage(pageable);

        Assert.assertEquals(0, subPage.getContent().size());
    }

    @Test
    public void shouldPageReturnTheCorrectElements() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<SubjectModel> subPage = repository.findAllActiveByPage(pageable);

        Assert.assertEquals(subjectA, subPage.getContent().get(0));
        Assert.assertEquals(subjectB, subPage.getContent().get(1));
    }

    @Test
    public void findAllByNameAndDepartmentAndCodeReturnTwoElementsInPageZero() {
        String partialName = "fmc";
        String partialDepartment = "DIM";
        String partialCode = "Di";

        Pageable pageable = PageRequest.of(0, 5);
        Page<SubjectModel> subPage = repository.findAllByNameAndDepartmentAndCode(pageable, partialName, partialDepartment, partialCode);

        Assert.assertEquals(2, subPage.getContent().size());
    }

    @Test
    public void findAllByNameAndDepartmentAndCodeReturnCorrectOneElementInPageOne() {
        String partialName = "fmc";
        String partialDepartment = "DIMA";
        String partialCode = "21";
        
        Pageable pageable = PageRequest.of(0, 5);
        Page<SubjectModel> subPage = repository.findAllByNameAndDepartmentAndCode(pageable, partialName, partialDepartment, partialCode);

        Assert.assertTrue(subPage.getContent().contains(subjectB));
    }

    @Test
    public void findAllByNameAndDepartmentAndCodeReturnZeroElementsInPageOne() {
        String partialName = "fmc";
        String partialDepartment = "DIMA";
        String partialCode = "";

        Pageable pageable = PageRequest.of(1, 5);
        Page<SubjectModel> subPage = repository.findAllByNameAndDepartmentAndCode(pageable, partialName, partialDepartment, partialCode);

        Assert.assertEquals(0, subPage.getContent().size());
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