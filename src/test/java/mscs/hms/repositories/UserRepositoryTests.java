package mscs.hms.repositories;

import mscs.hms.models.Role;
import mscs.hms.models.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        UserInfo user = new UserInfo();
        user.setUserName("test-user2");
        user.setFirstName("Unit-2");
        user.setLastName("Test-2");
        user.setEmail("test2@test.com");
        user.setPhone("145789652");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("123456"));

        Role role = userRepository.getAllRoleByName("Admin");
        assert (role != null);

        user.getRoles().add(role);

        UserInfo savedUser = userRepository.save(user);

        UserInfo existUser = entityManager.find(UserInfo.class, savedUser.getId());

        assert(user.getEmail()).equals(existUser.getEmail());
    }
}
