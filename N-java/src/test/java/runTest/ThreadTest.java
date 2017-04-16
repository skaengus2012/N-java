package runTest;

import Njava.util.business.CloneUtil;
import org.junit.Test;

/**
 * Created by Doohyun on 2017. 4. 16..
 */
public class ThreadTest {

    private class ManagerHolder {
        public Integer a;
        public Integer b;


    }

    @Test
    public void runTest() {
        ManagerHolder managerHolder = new ManagerHolder();
        managerHolder.a = 5;
        managerHolder.b = 7;

        ManagerHolder managerHolder1 = CloneUtil.CopyProperties(managerHolder);

        managerHolder.a = 7;

        ManagerHolder managerHolder2 = CloneUtil.NewInstance(ManagerHolder.class);
        managerHolder2.a = 3;

        ManagerHolder managerHolder3 = CloneUtil.NewInstance(managerHolder2.getClass());

        System.out.println(managerHolder.a  + " " + managerHolder1.a + " " + managerHolder2.a + managerHolder3.a);
    }
}
