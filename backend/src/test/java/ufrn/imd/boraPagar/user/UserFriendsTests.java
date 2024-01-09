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
public class UserFriendsTests {
    
    @Autowired
    UserRepository repository;
    UserModel userA, userB;

    @Before
    public void setUp() throws Exception {
        userA = new UserModel();
        userA.setName("Isaac Lourenço");
        userA.setEmail("isaac.lourenco.704@ufrn.edu.br");
        userA.setGoogleId("123");
        userA.setPictureUri("https://bonitao.com");
        userA.setUsername("V1d4isaacL0uk4");
        
        userB = new UserModel();
        userB.setName("Emanuel Kywal");
        userB.setEmail("kywal.cabral.707@ufrn.edu.br");
        userB.setGoogleId("456");
        userB.setPictureUri("https://charmosao.com");
        userB.setUsername("Lawyk");

        userA = repository.save(userA);
        userB = repository.save(userB);

        userA.getFriends().add(userB);
        userA = repository.save(userA);
    }

    @After
    public void deleteAll() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void shouldContainsUserB() {
        Assert.assertTrue(userA.getFriends().contains(userB));
    }

    @Test
    public void shouldNotContainsUserA() {
        Assert.assertFalse(userB.getFriends().contains(userA));
    } 
}
