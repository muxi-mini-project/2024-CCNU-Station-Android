package com.example.ccnu_station.Achievements;


import java.util.ArrayList;
import java.util.List;

public class Achievement {
    private int achievementId;
    private String title;
    private boolean isfinished;
//    private String detail;


    public Achievement(int achievementId, String title, boolean isfinished) {
        this.achievementId = achievementId;
        this.title = title;
        this.isfinished = isfinished;
    }

    public int getAchievementId() {
        return achievementId+1;
    }
    public String getTitle() {
        return title;
    }

    public boolean isIsfinished() {
        return isfinished;
    }


    public void setIsfinished(boolean isfinished) {
        this.isfinished = isfinished;
    }

    //这里我的内容是固定的所以我用arraylist来构造一些数据
    public static List<Achievement> getAchivement() {
        List<Achievement> AchivementList = new ArrayList<>();

        AchivementList.add(new Achievement(0,"  1.参加华师故事文艺展演",false));
        AchivementList.add(new Achievement(1,"  2.在华师的校门打卡留念",false));
        AchivementList.add(new Achievement(2,"  3.去图书馆读自己喜欢的书",false));
        AchivementList.add(new Achievement(3,"  4.在武汉的雪天堆一个自己的小雪人",false));
        AchivementList.add(new Achievement(4,"  5.学习疲惫后看一场超美的晚霞",false));
        AchivementList.add(new Achievement(5,"  6.在利群书社看书，抬头即是青葱",false));
        AchivementList.add(new Achievement(6,"  7.路上遇到了喷水彩虹",false));
        AchivementList.add(new Achievement(7,"  8.在天气很好的体育课上，\n  投篮的时候看到篮板上的蓝天白云",false));
        AchivementList.add(new Achievement(8,"  9.感受初冬的阳光洒在脸上",false));
        AchivementList.add(new Achievement(9,"  10.中秋之夜路过桂花树，\n  抬头正是花好月正圆",false));
        AchivementList.add(new Achievement(10,"  11.傍晚散步，发现透过竹叶的阳光和\n  灰白色的石板路如此契合",false));
        AchivementList.add(new Achievement(11,"  12.桂花飘香时走属于华师人自己的花路",false));
        AchivementList.add(new Achievement(12,"  13.雨后漫游",false));
        AchivementList.add(new Achievement(13,"  14.吃桂香园的油泼面",false));
        AchivementList.add(new Achievement(14,"  15.看绝美的喷泉",false));
        AchivementList.add(new Achievement(15,"  16.送老师一朵花，暖他整个冬天",false));
        AchivementList.add(new Achievement(16,"  17.盛大的校运会",false));
        AchivementList.add(new Achievement(17,"  18.去上自己喜欢的老师课",false));
        AchivementList.add(new Achievement(18,"  19.看一次演出",false));
        AchivementList.add(new Achievement(19,"  20.在民族游园会中感受民族魅力",false));
        AchivementList.add(new Achievement(20,"  21.参加一次志愿活动",false));
        AchivementList.add(new Achievement(21,"  22.参加桂子山国际文化节，\n  感受到文化的交流与碰撞",false));
        AchivementList.add(new Achievement(22,"  23.在菊展拍照",false));
        AchivementList.add(new Achievement(23,"  24.在佑铭体育场挥洒汗水",false));
        AchivementList.add(new Achievement(24,"  25.寻找华师专属的蜜雪冰城的堡垒",false));
        AchivementList.add(new Achievement(25,"  26.吃南湖食堂的鱼粉",false));
        AchivementList.add(new Achievement(26,"  27.捡一片银杏做书签",false));
        AchivementList.add(new Achievement(27,"  28.拍华师的神仙小猫",false));
        AchivementList.add(new Achievement(28,"  29.遇到一只绝美蝴蝶",false));
        AchivementList.add(new Achievement(29,"  30.探索博雅广场上的特别一角",false));
        AchivementList.add(new Achievement(30,"  31.和华师的爱心树一起拍照留念",false));
        AchivementList.add(new Achievement(31,"  32.参加毕业晚会，母校对所有的孩子说\n  “祝大家永远快乐”",false));
        AchivementList.add(new Achievement(32,"  33.初春时和好友一起去梅园",false));
        AchivementList.add(new Achievement(33,"  34.发现南门可爱的泡泡学长",false));
        AchivementList.add(new Achievement(34,"  35.观察小蜜蜂采蜜",false));
        AchivementList.add(new Achievement(35,"  36.在秋天去银杏树林拍照",false));
        AchivementList.add(new Achievement(36,"  37.参加晚上的草坪音乐会",false));
        AchivementList.add(new Achievement(37,"  38.通过友谊门去武理逛一逛",false));
        AchivementList.add(new Achievement(38,"  39.去林荫道呼吸新鲜空气",false));
        AchivementList.add(new Achievement(39,"  40.给华师写一首小诗",false));
        AchivementList.add(new Achievement(40,"  41.勇敢地站到讲台上表现自己",false));
        AchivementList.add(new Achievement(41,"  42.拍下美丽的异木棉",false));
        AchivementList.add(new Achievement(42,"  43.在落叶满地时漫步",false));
        AchivementList.add(new Achievement(43,"  44.逛逛百团大战",false));
        AchivementList.add(new Achievement(44,"  45.参观光未然英雄的雕像",false));
        AchivementList.add(new Achievement(45,"  46.欣赏紫荆花",false));
        AchivementList.add(new Achievement(46,"  47.参观古朴典雅的文学院",false));
        AchivementList.add(new Achievement(47,"  48.华师的大树会拥抱每个抬头的人",false));
        AchivementList.add(new Achievement(48,"  49.参观校训石",false));
        AchivementList.add(new Achievement(49,"  50.游览校医院门口的竹林",false));
        AchivementList.add(new Achievement(50,"  51.溜进美术学院参观画室",false));
        AchivementList.add(new Achievement(51,"  52.在桂中路乘凉",false));
        AchivementList.add(new Achievement(52,"  53.参观校史馆",false));
        AchivementList.add(new Achievement(53,"  54.参观博物馆",false));
        AchivementList.add(new Achievement(54,"  55.看一场露天电影",false));
        AchivementList.add(new Achievement(55,"  56.参加一次升旗仪式",false));
        AchivementList.add(new Achievement(56,"  57.入喜欢的社团进行团建",false));
        AchivementList.add(new Achievement(57,"  58.入喜欢的部门进行团建",false));
        AchivementList.add(new Achievement(58,"  59.走过华师大天桥",false));
        AchivementList.add(new Achievement(59,"  60.吃学子食堂的烤鸡腿",false));
        AchivementList.add(new Achievement(60,"  61.参观沁园春食堂",false));
        AchivementList.add(new Achievement(61,"  62.参加华师的校庆",false));
        AchivementList.add(new Achievement(62,"  63.上一堂心理课来一场心灵之旅",false));
        AchivementList.add(new Achievement(63,"  64.和亚运冠军合影",false));
        AchivementList.add(new Achievement(64,"  65.在佑铭体育场看一场排球比赛",false));
        AchivementList.add(new Achievement(65,"  66.坐在长椅上听歌",false));
        AchivementList.add(new Achievement(66,"  67.参加华师的校庆",false));
        AchivementList.add(new Achievement(67,"  68.上一堂心理课来一场心灵之旅",false));
        AchivementList.add(new Achievement(68,"  69.和亚运冠军合影",false));
        AchivementList.add(new Achievement(69,"  70.在佑铭体育场看一场排球比赛",false));
        AchivementList.add(new Achievement(70,"  71.在南湖的菜鸟驿站旁边闻闻桂花香",false));
        AchivementList.add(new Achievement(71,"  72.坐在长椅上听歌",false));
        AchivementList.add(new Achievement(72,"  73.走上舞台参加一次文艺展演",false));
        AchivementList.add(new Achievement(73,"  74.星期五晚上去佑铭唱歌或者跳广场舞",false));
        AchivementList.add(new Achievement(74,"  75.喝壮壮哥家的熊猫奶盖",false));
        AchivementList.add(new Achievement(75,"  76.吃一碗热干面",false));
        AchivementList.add(new Achievement(76,"  77.冬至吃桂香园二楼的东北饺子，喝饺子汤",false));
        AchivementList.add(new Achievement(77,"  78.和喜欢的ta在树林里散步",false));
        AchivementList.add(new Achievement(78,"  79.看一次笛箫社的表演",false));
        AchivementList.add(new Achievement(79,"  80.一口气跑上绝望坡",false));
        AchivementList.add(new Achievement(80,"  81.好好学习拿一次奖学金",false));
        AchivementList.add(new Achievement(81,"  82.吃南湖民族餐厅的烧烤",false));
        AchivementList.add(new Achievement(82,"  83.选一次历史学院的通核课程",false));
        AchivementList.add(new Achievement(83,"  84.和路边的小鸟打招呼",false));
        AchivementList.add(new Achievement(84,"  85.把自己喜欢的风景画下来",false));
        AchivementList.add(new Achievement(85,"  86.参加一次校园竞赛",false));
        AchivementList.add(new Achievement(86,"  87.拥有一个学校的周边",false));
        AchivementList.add(new Achievement(87,"  88.给毕业后的自己写一封信",false));
        AchivementList.add(new Achievement(88,"  89.感受一次辩论赛的乐趣",false));
        AchivementList.add(new Achievement(89,"  90.整蛊一次室友",false));
        AchivementList.add(new Achievement(90,"  91.写一次日记，记录今天的趣事",false));
        AchivementList.add(new Achievement(91,"  92.和朋友品尝武汉的鸭架子",false));
        AchivementList.add(new Achievement(92,"  93.离开学校时认真和重要的人告别",false));
        AchivementList.add(new Achievement(93,"  94.在图书馆学到闭馆",false));
        AchivementList.add(new Achievement(94,"  95.第一个到达早八的教室",false));
        AchivementList.add(new Achievement(95,"  96.体验华师匣子",false));
        AchivementList.add(new Achievement(96,"  97.运动计步超过两万，不做脆皮大学生",false));
        AchivementList.add(new Achievement(97,"  98.和室友一起在宿舍看恐怖片",false));
        AchivementList.add(new Achievement(98,"  99.拍下朋友的照片做成表情包",false));
        AchivementList.add(new Achievement(99,"  100.相信并且爱自己",false));
        // ... 添加其他任务
        return AchivementList;
    }
}