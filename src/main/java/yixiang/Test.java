package yixiang;

public class Test {

    public static void main(String[] args) {

//        String  a = "https://vdo.lynkage.cn/sv/31a22657-172bc856acc/31a22657-172bc856acc.mp4,9FCA1EFF-4A7F-41FB-A146-96F6DEED6E91";
        String a = "https://vdo.lynkage.cn/sv/305f4e7-172bc760a64/305f4e7-172bc760a64.mp4,艺术1的本质";
//        String a = "https://vdo.lynkage.cn/sv/305f4e7-172bc760a64/305f4e7-172bc760a64.mp4,艺术的本质.mp4";
        String[] aa = a.split(",");
        String before = aa[0];
        String after = aa[1];

        System.out.println(before);
        after = after.contains(".") ?after:after+before.substring(before.lastIndexOf("."));
        System.out.println(after);
    }
}
