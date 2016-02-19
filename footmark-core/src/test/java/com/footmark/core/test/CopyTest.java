package com.footmark.core.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.cglib.beans.BeanCopier;

public class CopyTest {

    @Test
    public void testCopyBean() {
        BeanCopier copier = BeanCopier.create(A.class, B.class, false);
        A a = new A();
        a.setName("aaa");
        List<String> children = new ArrayList<String>();
        children.add("child1");
        a.setChildren(children);
        B b = new B();
        copier.copy(a, b, null); // 1.复制属性值
    }
}

class A {
    private String name;
    private List<String> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

}

class B {
    private Long name;
    private List<String> children;

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

}