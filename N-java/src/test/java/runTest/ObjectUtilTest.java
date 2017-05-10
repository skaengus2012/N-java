package runTest;

import Njava.function.IConsumer;
import Njava.util.business.ObjectUtil;
import org.junit.Test;
import testObject.Member;

/**
 * Created by Doohyun on 2017. 4. 16..
 */
public class ObjectUtilTest {

    @Test
    public void run() {
        Member member = new Member();
        member.setName("Doohyun Name");
        member.setMemberSubjectSn(1);

        // Test Function.
        IConsumer<Member> printInfo = new IConsumer<Member>() {
            @Override
            public void accept(Member member) {
                System.out.println(String.format("sn : %d, name : %s", member.getMemberSubjectSn(), member.getName()));
            }
        };

        // deep copy
        {
            Member copy = ObjectUtil.CopyProperties(member);
            printInfo.accept(copy);
        }

        // deep copy by void
        {
            Member newInstance = new Member();
            ObjectUtil.CopyProperties(member, newInstance);
            printInfo.accept(newInstance);
        }

        // Create new Instance.
        {
            Member newInstance = ObjectUtil.NewInstance(Member.class);
            newInstance.setMemberSubjectSn(5);
            newInstance.setName("Test");

            printInfo.accept(newInstance);
        }
    }
}
