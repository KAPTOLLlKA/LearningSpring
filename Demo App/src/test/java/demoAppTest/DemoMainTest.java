package demoAppTest;

import demoShop.DemoMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoMain.class)
public class DemoMainTest {
    @Test
    public void contextLoads() {

    }
}
