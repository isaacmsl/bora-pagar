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
    UserModel user2;

    private int pageSize = 5;

    @Before
    public void setUp() throws Exception {
        user = new UserModel();
        user.setName("Isaac Lourenço");
        user.setEmail("isaac.lourenco.704@ufrn.edu.br");
        user.setGoogleId("123");
        user.setPictureUri("https://bonitao.com");
        user.setUsername("V1d4isaacL0uk4");

        user = repository.save(user);

        user2 = new UserModel();
        user2.setName("Ramon jales");
        user2.setEmail("ramon.jales@ufrn.edu.br");
        user2.setGoogleId("1234");
        user2.setPictureUri("https://bonitao.com");
        user2.setUsername("Ramonzin");

        user2 = repository.save(user2);
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
    public void shouldFindByName() {
        Assert.assertNotNull(repository.findByName(user.getName()));
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
    public void shouldFindAllByName() {
        String partialName = "IsAaC";
        
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<UserModel> userPage = repository.findAllByName(pageable, partialName);
        
        Assert.assertTrue(userPage.getContent().contains(user));
    }

    @Test
    public void shouldNotFindAllByName() {
        String partialName = "joao";
        
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<UserModel> userPage = repository.findAllByName(pageable, partialName);
        
        Assert.assertTrue(userPage.getContent().isEmpty());
    }

    @Test
    public void shouldFindAllByUsername() {
        String partialName = "IsAaC";
        
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<UserModel> userPage = repository.findAllByUsername(pageable, partialName);

        Assert.assertTrue(userPage.getContent().contains(user));
    }

    @Test
    public void shouldNotFindAllByUsername() {
        String partialName = "joao";
        
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<UserModel> userPage = repository.findAllByUsername(pageable, partialName);

        Assert.assertTrue(userPage.getContent().isEmpty());
    }

    @Test
    public void shouldRoleBeUser() {
        Assert.assertEquals(RoleEnum.ROLE_USER, user.getRole());
    }

    @Test
    public void shouldPageZeroHasLengthTwo() {
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<UserModel> userPage = repository.findAllActiveByPage(pageable);

        Assert.assertEquals(2, userPage.getContent().size());
        Assert.assertTrue(userPage.getContent().contains(user));
        Assert.assertTrue(userPage.getContent().contains(user2));
    }

    @Test
    public void shouldPageOneHasLengthZero() {
        Pageable pageable = PageRequest.of(1, pageSize);
        Page<UserModel> userPage = repository.findAllActiveByPage(pageable);

        Assert.assertEquals(0, userPage.getContent().size());
    }

    @Test
    public void shouldPageZeroHasUserAsFirstObjectUsingOrderByName() {
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<UserModel> userPage = repository.findAllByNameOrderByNameAsc(pageable);

        Assert.assertEquals(user, userPage.getContent().get(0));
        Assert.assertEquals(user2, userPage.getContent().get(1));
    }

    @Test
    public void shouldPageZeroHasUserAsFirstObjectUsingOrderByUsername() {
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<UserModel> userPage = repository.findAllByUsernameOrderByUsernameAsc(pageable);

        Assert.assertEquals(user, userPage.getContent().get(0));
        Assert.assertEquals(user2, userPage.getContent().get(1));
    }
}
