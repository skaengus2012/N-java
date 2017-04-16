package runTest;

import Njava.util.business.CloneUtil;
import org.junit.Test;
import testObject.Member;

/**
 * Created by Doohyun on 2017. 4. 16..
 */
public class CloneUtilTest {

    @Test
    public void run() {
        Member member = new Member();
        member.setName("Doohyun Name");
        member.setMemberSubjectSn(1);

        // deep copy
        {
            Member copy = CloneUtil.CopyProperties(member);
            printInfo(copy);
        }

        // deep copy by void
        {
            Member newInstance = new Member();
            CloneUtil.CopyProperties(member, newInstance);
            printInfo(newInstance);
        }

        // Create new Instance.
        {
            Member newInstance = CloneUtil.NewInstance(Member.class);
            newInstance.setMemberSubjectSn(5);
            newInstance.setName("Test");

            printInfo(newInstance);
        }
    }

    /**
     * print member info.
     *
     * @param member
     */
    private void printInfo(Member member) {
        System.out.println(String.format("sn : %d, name : %s", member.getMemberSubjectSn(), member.getName()));
    }
}
