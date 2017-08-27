import com.mark.project.mybatis_demo.domain.User;
import com.mark.project.mybatis_demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mark on 17/8/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyBatisDemoTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testSave() {
        User user = new User();
        user.setName("Mark");
        user.setPassword("123456");
        userService.save(user);
    }


}
