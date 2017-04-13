package testObject;

/**
 * Created by Doohyun on 2017. 4. 13..
 */
public class ObserverSample {

    public void notifyData() {
        try {
          //  Thread.sleep(3000);

            System.out.println("옵저버 노티파이 완료");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
