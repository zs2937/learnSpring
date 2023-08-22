package com.jirengu.hotel.template;

public class Student extends People {

    @Override
    public void qichuang() {
        System.out.println("起床：爸爸妈妈叫醒");
    }

    @Override
    public void quxuexiao() {
        System.out.println("去学校：爸爸妈妈开车送去学校");
    }

    @Override
    public void shangke() {
        System.out.println("上课：学习知识");
    }

}
