package com.example.operatelogspringbootstarter;

import com.example.operatelogspringbootstarter.v1.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class OperatelogSpringBootStarterApplicationTests {

    @Test
    void contextLoads() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.purchaseName");
        Order order = new Order();
        order.setPurchaseName("张三");
        System.out.println(expression.getValue(order));
    }


    @Test
    public void gg(){
        String str = "my name is 007";

        List<String> stringList = Stream.of(str.split(" "))
                .filter(s -> {
                            System.out.println(s.length());
                            System.out.println(s.length()>2);
                            return s.length()>2;
                        }
                    ).collect(Collectors.toList());
        System.out.println("-----stringList："+stringList);

    }


}
