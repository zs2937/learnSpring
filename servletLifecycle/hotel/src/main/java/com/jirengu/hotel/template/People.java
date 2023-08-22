package com.jirengu.hotel.template;

public abstract class People {

    public void oneDay() {
        // 人的一天包括：起床、洗漱、去学校、上课
        qichuang();
        xishu();
        quxuexiao();
        shangke();
    }

    public void qichuang() {
        // 空实现
    }

    public void xishu() {
        // 默认实现
        System.out.println("洗漱：刷牙洗脸");
    }

    public void quxuexiao() {
        // 默认实现
        System.out.println("去学校：坐校车去学校");
    }

    public void shangke() {
        // 空实现
    }

}
