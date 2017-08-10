import com.mark.project.pss.domain.Department;
import com.mark.project.pss.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Mark_Yan on 2017/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {

	@Autowired
	private IDepartmentService departmentService;

	@Test
	public void save() throws Exception {
		Department department = new Department();
		department.setSn("1001");
		department.setName("开发部");
		departmentService.save(department);
	}

	@Test
	public void update() throws Exception {

	}

	@Test
	public void delete() throws Exception {

	}

	@Test
	public void get() throws Exception {

	}

	@Test
	public void list() throws Exception {

	}

}