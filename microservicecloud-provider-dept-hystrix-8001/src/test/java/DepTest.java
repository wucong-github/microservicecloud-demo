import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DepTest {

    @Autowired
    private DeptService deptService;

    @Test
    public void test01() {
        Dept dept = deptService.get(1L);
        System.out.println(dept);

    }
}
