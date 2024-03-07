package com.example.ccnu_station;


import java.util.ArrayList;
import java.util.List;

public class Achievement {

    private String title;
//    private String detail;


    public Achievement(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

//    public String getDetail() {
//        return detail;
//    }

    //这里我的内容是固定的所以我用arraylist来构造一些数据
    public static List<Achievement> getAchivement() {
        List<Achievement> AchivementList = new ArrayList<>();

        AchivementList.add(new Achievement("  1.参加华师故事文艺展演"));
        AchivementList.add(new Achievement("  2.在华师的校门打卡留念"));
        AchivementList.add(new Achievement("  3.去图书馆读自己喜欢的书"));
        AchivementList.add(new Achievement("  4.在武汉的雪天堆一个自己的小雪人"));
        AchivementList.add(new Achievement("  5.学习疲惫后看一场超美的晚霞"));
        AchivementList.add(new Achievement("  6.在利群书社看书，抬头即是青葱"));
        AchivementList.add(new Achievement("  7.路上遇到了喷水彩虹"));
        AchivementList.add(new Achievement("  8.在天气很好的体育课上，\n  投篮的时候看到篮板上的蓝天白云"));
        AchivementList.add(new Achievement("  9.感受初冬的阳光洒在脸上"));
        AchivementList.add(new Achievement("  10.中秋之夜路过桂花树，\n  抬头正是花好月正圆"));
        AchivementList.add(new Achievement("  11.傍晚散步，发现透过竹叶的阳光和\n  灰白色的石板路如此契合"));
        AchivementList.add(new Achievement("  12.桂花飘香时走属于华师人自己的花路"));
        AchivementList.add(new Achievement("  13.雨后漫游"));
        AchivementList.add(new Achievement("  14.吃桂香园的油泼面"));
        AchivementList.add(new Achievement("  15.看绝美的喷泉"));
        AchivementList.add(new Achievement("  16.送老师一朵花，暖他整个冬天"));
        AchivementList.add(new Achievement("  17.盛大的校运会"));
        AchivementList.add(new Achievement("  18.去上自己喜欢的老师课"));
        AchivementList.add(new Achievement("  19.看一次演出"));
        AchivementList.add(new Achievement("  20.在民族游园会中感受民族魅力"));
        AchivementList.add(new Achievement("  21.参加一次志愿活动"));
        AchivementList.add(new Achievement("  22.参加桂子山国际文化节，感受到文化的交流与碰撞"));
        AchivementList.add(new Achievement("  23.在菊展拍照"));
        AchivementList.add(new Achievement("  24.在佑铭体育场挥洒汗水"));
        AchivementList.add(new Achievement("  25.寻找华师专属的蜜雪冰城的堡垒"));
        AchivementList.add(new Achievement("  26.吃南湖食堂的鱼粉"));
        AchivementList.add(new Achievement("  27.捡一片银杏做书签"));
        AchivementList.add(new Achievement("  28.拍华师的神仙小猫"));
        AchivementList.add(new Achievement("  29.遇到一只绝美蝴蝶"));
        AchivementList.add(new Achievement("  30.探索博雅广场上的特别一角"));
        AchivementList.add(new Achievement("  31.和华师的爱心树一起拍照留念"));
        AchivementList.add(new Achievement("  32.参加毕业晚会，母校对所有的孩子说“祝大家永远快乐”"));
        AchivementList.add(new Achievement("  33.初春时和好友一起去梅园"));
        AchivementList.add(new Achievement("  34.发现南门可爱的泡泡学长"));
        AchivementList.add(new Achievement("  35.观察小蜜蜂采蜜"));
        AchivementList.add(new Achievement("  36.在秋天去银杏树林拍照"));
        AchivementList.add(new Achievement("  37.参加晚上的草坪音乐会"));
        AchivementList.add(new Achievement("  38.通过友谊门去武理逛一逛"));
        AchivementList.add(new Achievement("  39.去林荫道呼吸新鲜空气"));
        AchivementList.add(new Achievement("  40.给华师写一首小诗"));
        AchivementList.add(new Achievement("  41.勇敢地站到讲台上表现自己"));
        AchivementList.add(new Achievement("  42.拍下美丽的异木棉"));
        AchivementList.add(new Achievement("  43.在落叶满地时漫步"));
        AchivementList.add(new Achievement("  44.逛逛百团大战"));
        AchivementList.add(new Achievement("  45.参观光未然英雄的雕像"));
        AchivementList.add(new Achievement("  46.欣赏紫荆花"));
        AchivementList.add(new Achievement("  47.参观古朴典雅的文学院"));
        AchivementList.add(new Achievement("  48.华师的大树会拥抱每个抬头的人"));
        AchivementList.add(new Achievement("  49.参观校训石"));
        AchivementList.add(new Achievement("  50.游览校医院门口的竹林"));
        AchivementList.add(new Achievement("  51.溜进美术学院参观画室"));
        AchivementList.add(new Achievement("  52.在桂中路乘凉"));
        AchivementList.add(new Achievement("  53.参观校史馆"));
        AchivementList.add(new Achievement("  54.参观博物馆"));
        AchivementList.add(new Achievement("  55.看一场露天电影"));
        AchivementList.add(new Achievement("  56.参加一次升旗仪式"));
        AchivementList.add(new Achievement("  57.入喜欢的社团进行团建"));
        AchivementList.add(new Achievement("  58.早起看一次日出"));
        AchivementList.add(new Achievement("  59.走过华师大天桥"));
        AchivementList.add(new Achievement("  60.吃学子食堂的烤鸡腿"));
        AchivementList.add(new Achievement("  61.参观沁园春食堂"));
        AchivementList.add(new Achievement("  62.参加华师的校庆"));
        AchivementList.add(new Achievement("  63.上一堂心理课来一场心灵之旅"));
        AchivementList.add(new Achievement("  64.和亚运冠军合影"));
        AchivementList.add(new Achievement("  65.在佑铭体育场看一场排球比赛"));
        AchivementList.add(new Achievement("  66.坐在长椅上听歌"));



        // ... 添加其他任务

        return AchivementList;
    }
}