package testObject;

/**
 * Created by Doohyun on 2017. 4. 16..
 */
public class Member implements Comparable<Member>{
    private Integer memberSubjectSn;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return String.format("sn : [%d], name : [%s], age : [%d]", memberSubjectSn, name, age);
    }

    public Integer getMemberSubjectSn() {
        return memberSubjectSn;
    }

    public void setMemberSubjectSn(Integer memberSubjectSn) {
        this.memberSubjectSn = memberSubjectSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Member o) {
        return getAge().compareTo(o.getAge());
    }
}
