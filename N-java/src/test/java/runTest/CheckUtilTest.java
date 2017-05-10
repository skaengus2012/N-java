package runTest;

import Njava.util.business.CheckUtil;
import io.reactivex.Maybe;
import org.junit.Test;
import testObject.MessageException;

import java.util.Collection;
import java.util.Collections;

/**
 * Check Util Test
 *
 * Created by Doohyun on 2017. 5. 10..
 */
public class CheckUtilTest {

    /**
     * null check 예외 테스트
     *
     * @throws Exception
     */
    @Test
    public void nullCheckException() throws Exception{
        // Validation Check 에 대한 익셉션 종류 선택 가능
        // 경우에 따라서, 클라이언트에게 주는 메시지인지 서버 500 으로 갈지 간략화할 수 있지!!!

        // 이 것은 메시지 익셉션으로 핸들링
        CheckUtil.NullCheck(null,"동적 메세지 익셉션!!!", MessageException.class);

        // 이 것은 런타임 익센션으로 핸들링
        CheckUtil.NullCheck(null,"동적 런타임 익셉션!!!", RuntimeException.class);
    }

    /**
     * String 테스트
     *
     * @throws MessageException
     */
    @Test
    public void runStringCheckTest() throws MessageException{
        String message = "";

        CheckUtil.EmptyToStringCheck(message, "메시지가 비었습니다", MessageException.class);
    }

    /**
     * 메이비 테스트
     *
     * @throws MessageException
     */
    @Test
    public void runMaybeCheckTest() throws MessageException{
        Maybe<String> maybe = Maybe.empty();

        CheckUtil.EmptyMaybeCheck(maybe, "메이비가 비었습니다", MessageException.class);
    }

    /**
     * 메이비 테스트
     *
     * @throws MessageException
     */
    @Test
    public void runContainerCheckTest() throws MessageException {
        Collection<String> strings = Collections.emptyList();

        CheckUtil.EmptyContainerCheck(strings, "컬렉션이 비었습니다", MessageException.class);
    }
}
