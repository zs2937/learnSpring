package com.jirengu.hotel;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class TestClass {


    @Getter
    @Setter
    @ToString
    private static class Class1 {
        private Integer id;
        private String name;

    }

    public static void main(String[] args) {
        Class1 class1 = new Class1();
        class1.setId(1);
        class1.setName("a");
        System.out.println(class1);
    }
}
