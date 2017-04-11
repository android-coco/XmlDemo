package org.yh.xmldemo;

public class Beauty
{

    //美女姓名
    private String name;
    //美女年龄
    private String age;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "美女资料 [年龄=" + age + ", 姓名=" + name + "]";
    }
}
