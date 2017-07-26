package runTest;

import Njava.util.business.StringUtil;
import org.junit.Test;
import testObject.DateIncludeVo;

import java.util.Date;

/**
 * String Util Test 를 위한 클래스.
 *
 * Created by Doohyun on 2017. 7. 26..
 */
public class StringUtilTest {

    /**
     * StringUtil ToJson Test.
     */
    @Test
    public void StringUtil_ToJson_Test() {

        DateIncludeVo dateIncludeVo = new DateIncludeVo();
        dateIncludeVo.setDate(new Date());

        System.out.println(StringUtil.ToJson(dateIncludeVo));

        System.out.println(new Date(1501057623761L));

    }

}
