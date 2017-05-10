package runTest;

import Njava.function.exceptionLambda.IExRunnable;
import org.junit.Test;
import testObject.MessageException;

/**
 * Simple Test
 *
 * Created by Doohyun on 2017. 5. 10..
 */
public class SimpleTest {

    @Test
    public void run() {



        try {
            Test();
        } catch (MessageException e) {
            System.out.println("메시지 익셉션");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("익셉션");
            e.printStackTrace();
        }
    }

    public void Test() throws Exception{
        IExRunnable runnable = new IExRunnable() {
            @Override
            public void run() throws MessageException {
                throw new MessageException("asd");
            }
        };

        runnable.run();
    }
}
