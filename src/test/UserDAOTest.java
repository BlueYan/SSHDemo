import com.mark.dao.IUserDAO;
import com.mark.domain.User;
import com.mark.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mark_Yan on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDAOTest {

	@Autowired
	private IUserService userService;

	@Test
	public void testSave() {
		User user = new User();
		user.setUname("Mark");
		user.setAge(18);
		userService.save(user);
	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testGet() {
		User user = userService.get(1L);
		System.out.println(user);
	}


}
