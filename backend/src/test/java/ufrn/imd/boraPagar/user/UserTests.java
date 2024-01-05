package ufrn.imd.boraPagar.user;

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
        user.setUsername("isàaçV1d4L0uk4");

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
}