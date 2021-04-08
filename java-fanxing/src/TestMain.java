import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        List<BasketBall> basketBalls = new ArrayList<>();
        basketBalls.add(new BasketBall());

        List<Ball> balls = new ArrayList<>();
        balls.add(new BasketBall());

        List multi = new ArrayList();

        multi.addAll(balls);
        multi.addAll(basketBalls);

        List<String> strs = new ArrayList<>();
        strs.add("aaa");
        multi.addAll(strs);

        List<? extends Ball> l1 = multi;

        List<? super Ball> l2 = new ArrayList<>();

//        l1.add(new Object());//报错
//        l1.add(new Ball());//报错
//        l1.add(new BasketBall());//报错

        l2.add(new Ball());
        l2.add(new BasketBall());
//        l2.add(new Object()); //报错

        System.out.println("l1 = " + l1.size());
        System.out.println("l2 = " + l2.size());


        for (Ball ball : l1) {
            System.out.println("ball = " + ball);
        }
    }

    public static void test(List<? extends Ball> balls) {
        for (Ball ball : balls) {

        }
    }

    public static void test2(List<? super Ball> balls) {
        for (Object ball : balls) {

        }
    }

    //both methods have same erasure

//    public void test3(List<String> ls) {
//        System.out.println("Sting");
//    }

    public void test3(List<Integer> li) {
        System.out.println("Integer");
    }
}

