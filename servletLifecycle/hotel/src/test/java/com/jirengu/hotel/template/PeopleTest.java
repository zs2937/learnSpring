package com.jirengu.hotel.template;

import org.junit.Test;

import static org.junit.Assert.*;

public class PeopleTest {

    @Test
    public void testStudentAndTeacher() {
        Student student = new Student();
        Teacher teacher = new Teacher();
        System.out.println("学生的一天.");
        student.oneDay();
        System.out.println("老师的一天.");
        teacher.oneDay();
    }

}