package com.jirengu.hotel.template;

public class Teacher extends People {

    @Override
    public void qichuang() {
        System.out.println("起床：闹钟叫醒.");
    }

    @Override
    public void quxuexiao() {
        System.out.println("去学校：坐地铁去学校");
    }

    @Override
    public void shangke() {
        System.out.println("上课：传授知识");
    }
}
