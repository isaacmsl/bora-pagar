package ufrn.imd.boraPagar.user;

import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserTests {

    @Autowired
    UserRepository repository;
    UserModel user;

    @Before
    public void setUp() throws Exception {
        user = new UserModel();
        user.setEmail("isaac.lourenco.704@ufrn.edu.br");
        user.setGoogleId("123");
        user.setPictureUri("https://bonitao.com");
        user.setUsername("V1d4isaacL0uk4");

        user = repository.save(user);
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shoudFindByGoogleId() {
        Assert.assertNotNull(repository.findByGoogleId(user.getGoogleId()));
    }

    @Test
    public void shoudFindByEmail() {
        Assert.assertNotNull(repository.findByEmail(user.getEmail()));
    }

    @Test
    public void shoudFindByUsername() {
        Assert.assertNotNull(repository.findByUsername(user.getUsername()));
    }

    @Test
    public void shouldFindById() {
        Assert.assertNotNull(repository.findById(user.getId()));
    }

    @Test
    public void shouldFindAllByUsername() {
        Assert.assertFalse(repository.findAllByUsername("IsAaC").isEmpty());
    }

    @Test
    public void shouldNotFindAllByUsername() {
        Assert.assertTrue(repository.findAllByUsername("joao").isEmpty());
    }

    @Test
    public void shouldPageZeroHasLengthOne() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<UserModel> userPage = repository.findAllActiveByPage(pageable);

        Assert.assertEquals(1, userPage.getContent().size());
    }

    @Test
    public void shouldPageOneHasLengthZero() {
        Pageable pageable = PageRequest.of(1, 2);
        Page<UserModel> userPage = repository.findAllActiveByPage(pageable);

        Assert.assertEquals(0, userPage.getContent().size());
    }
}
