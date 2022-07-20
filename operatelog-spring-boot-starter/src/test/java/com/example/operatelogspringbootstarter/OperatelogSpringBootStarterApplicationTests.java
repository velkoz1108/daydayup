package com.example.operatelogspringbootstarter;

import com.example.operatelogspringbootstarter.v1.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

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


    @Test
    public void bb(){
        ExpressionParser parser = new SpelExpressionParser();


        System.out.println(parser.parseExpression("'你好'").getValue());
        //算术运算符
        System.out.println(parser.parseExpression("'Welcome SPEL'+'!'").getValue());
        System.out.println(parser.parseExpression("10 * 10/2").getValue());
        System.out.println(parser.parseExpression("'Today is: '+ new java.util.Date()").getValue());
        //逻辑运算符
        System.out.println(parser.parseExpression("true and false").getValue());
        //关系运算符
        System.out.println(parser.parseExpression("'sonoo'.length()==5").getValue());


        System.out.println("-------------------------------------------------------");


        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("param1","你好");
        evaluationContext.setVariable("param2","'Welcome SPEL'+'!'");


        Expression param1Expression = parser.parseExpression("#param1");
        System.out.println(param1Expression.getValue(evaluationContext,String.class));

        Expression param2Expression = parser.parseExpression("#param2");
        System.out.println(param2Expression.getValue(evaluationContext,String.class));


    }


}
