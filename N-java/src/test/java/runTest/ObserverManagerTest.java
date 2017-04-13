package runTest;


import Njava.OOP.ObserverManager;
import Njava.function.exceptionLambda.IExConsumer;
import io.reactivex.annotations.NonNull;
import testObject.ObserverSample;

/**
 * Created by Doohyun on 2017. 3. 20..
 */
public class ObserverManagerTest {

    @org.junit.Test
    public void rxSimple() {


        final ObserverManager<ObserverSample> observerManager = ObserverManager.Create();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("시작");
                for (int i = 0; i < 10; ++i) {
                    observerManager.addObserver(new ObserverSample());
                }

                try {

                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }

                observerManager.clear();

                System.out.println("끄");
            }
        }).start();

        for (int i = 0; i < 100000; ++i) {
            observerManager.notify(new IExConsumer<ObserverSample>() {
                @Override
                public void accept(@NonNull ObserverSample observerSample) throws Exception {
                    observerSample.notifyData();
                }
            });
        }
    }
}
