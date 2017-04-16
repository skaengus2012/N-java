package runTest;

import Njava.concurrent.NxExecuteIngredient;
import Njava.concurrent.NxExecutorManager;
import Njava.function.exceptionLambda.IExConsumer;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by Doohyun on 2017. 4. 16..
 */
public class ThreadTest {


    @Test
    public void runTest() {
        NxExecutorManager executeManager = NxExecutorManager.Create(NxExecuteIngredient.GetDefault());

      /*  {
            executeManager.submit(new IExRunnable() {
                @Override
                public void run() {
                    System.out.println("1 실행");
                }
            }, 5000);
        }

        {
            executeManager.submit(new IExRunnable() {
                @Override
                public void run() {
                    System.out.println("2 실행");
                }
            }, 2000);
        }

        {
            executeManager.submit(new IExRunnable() {
                @Override
                public void run() {
                    System.out.println("3 실행");
                }
            }, 2000);
        }

        {
            executeManager.submit(new IExRunnable() {
                @Override
                public void run() {
                    System.out.println("4 실행");
                }
            }, 5000);
        }*/


      {
            Future<Maybe<Integer>> result2 = executeManager.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println(Thread.currentThread().toString());
                    System.out.println("5 추출 에러");
                    throw new Exception("에러발생");
                }
            }, 1000);
        }

        {
            final Future<Maybe<Integer>> result1 = executeManager.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {

                    System.out.println("5 추출");
                    return null;
                }
            }, 5000);

            Observable.fromFuture(result1).subscribeOn(Schedulers.io()).forEach(new IExConsumer<Maybe<Integer>>() {
                @Override
                public void accept(@NonNull Maybe<Integer> integer) throws Exception {
                    System.out.println(Thread.currentThread().toString());
                    System.out.println("future 출력 : " + integer.blockingGet(0));
                }
            });
        }

        {
            Future<Maybe<Integer>> result2 = executeManager.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {

                    System.out.println("5 추출 에러");
                    throw new Exception("에러발생");
                }
            }, 5000);

        }

        {
            Future<Maybe<Integer>> result2 = executeManager.submitEmergency(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {

                    System.out.println("6 추출 에러");
                    throw new Exception("에러발생");
                }
            }, 1000);

        }

        System.out.println("메인 루프 진행.");

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
