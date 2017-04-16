package runTest;

import Njava.util.business.CloneUtil;
import Njava.util.business.ContainerUtil;
import org.junit.Test;
import testObject.Member;

import java.util.*;

/**
 * Created by Doohyun on 2017. 4. 16..
 */
public class ContainerUtilTest {

    /**
     * As Test
     */
    @Test
    public void runByAsType() {

        // asList
        {
            List<Integer> list = ContainerUtil.AsList(1, 2, 3);
            printList(list);
        }

        // asSet
        {
            Set<Integer> set = ContainerUtil.AsSet(2, 6, 5, 7, 3, 9, 0);
            printList(set);
        }

        // as sorted sets
        {
            Set<Integer> set = ContainerUtil.AsSortedSet(2, 6, 5, 7, 3, 9);
            printList(set);
        }

        {
            Member member = new Member();
            member.setAge(10);

            Member member2 = CloneUtil.CopyProperties(member);
            member2.setAge(5);

            Member member3 = CloneUtil.CopyProperties(member);
            member3.setAge(6);

            Set<Member> set = ContainerUtil.AsSortedSet(member, member2, member3);
            printList(set);

            Set<Member> set2 = ContainerUtil.AsSortedSet(new Comparator<Member>() {
                @Override
                public int compare(Member o1, Member o2) {
                    return o2.compareTo(o1);
                }
            }, member, member2, member3);
            printList(set2);
        }

        // as sorted set
        {
            Set<Integer> set = ContainerUtil.AsSortedSet(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            }, 2, 6, 5, 7, 3, 9);
            printList(set);
        }

        // as linkedhashset
        {
            Set<Integer> set = ContainerUtil.AsLinkedHashSet(2, 6, 5, 7, 3, 9);
            printList(set);
        }
    }

    /**
     * Print Collection
     *
     * @param collection
     * @param <T>
     */
    private <T>void printList(Collection<T> collection) {
        System.out.println(collection);
    }
}
