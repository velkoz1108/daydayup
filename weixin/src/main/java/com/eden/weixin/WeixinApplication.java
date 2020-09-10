package com.eden.weixin;

import com.mxixm.fastboot.weixin.annotation.WxApplication;
import com.mxixm.fastboot.weixin.annotation.WxAsyncMessage;
import com.mxixm.fastboot.weixin.annotation.WxButton;
import com.mxixm.fastboot.weixin.annotation.WxController;
import com.mxixm.fastboot.weixin.annotation.WxEventMapping;
import com.mxixm.fastboot.weixin.annotation.WxMessageMapping;
import com.mxixm.fastboot.weixin.module.event.WxEvent;
import com.mxixm.fastboot.weixin.module.message.WxMessage;
import com.mxixm.fastboot.weixin.module.message.WxMessageBody;
import com.mxixm.fastboot.weixin.module.user.WxUser;
import com.mxixm.fastboot.weixin.module.web.WxRequest;
import com.mxixm.fastboot.weixin.module.web.WxRequestBody;
import com.mxixm.fastboot.weixin.module.web.session.WxSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@WxApplication
@WxController
@SpringBootApplication
public class WeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinApplication.class, args);
    }

    public static final Map<String, Integer> money = new ConcurrentHashMap<>();

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.LEFT, main = true, name = "我的")
    public void left() {
        System.out.println("left");
    }

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.MIDDLE, main = true, name = "财富")
    public WxMessage middle(WxUser wxUser) {
        int money = WeixinApplication.money.getOrDefault(wxUser.getNickName(), 0);
        System.out.println("right");
        return WxMessage.textBuilder()
                .content("账户余额" + money + "亿元")
                .build();
    }

    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.RIGHT, main = true, name = "生活")
    public WxMessage right(WxUser wxUser) {
        Integer orDefault = WeixinApplication.money.getOrDefault(wxUser.getNickName(), 0) + 1;
        money.put(wxUser.getNickName(), orDefault);
        return WxMessage.textBuilder()
                .content("微信钱包到账1亿元")
                .build();

    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.LOCATION_SELECT,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.FIRST,
            name = "地理位置")
    public String leftFirst(WxRequest wxRequest, WxUser wxUser) {
        return "你好，" + wxUser.getNickName();
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.THIRD,
            name = "财富榜")
    public WxMessage leftThird(WxRequest wxRequest, WxUser wxUser) {

        Set<Map.Entry<String, Integer>> entries = money.entrySet();
        LinkedList<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(entries);
        list.sort(Comparator.comparingInt(obj -> -obj.getValue()));

        StringBuilder sb = new StringBuilder("财务排行榜：").append(" \n ");
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Integer> obj = list.get(i);
            sb.append(" ").append(i + 1).append(". ").append(obj.getKey()).append("  ").append(obj.getValue()).append("亿");
        }

        return WxMessage.textBuilder()
                .content(sb.toString())
                .build();
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.VIEW,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.SECOND,
            url = "http://baidu.com",
            name = "前往查看")
    @WxAsyncMessage
    public WxMessage link() {
        return WxMessage.newsBuilder().addItem("测试图文消息", "测试",
                "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png",
                "http://baidu.com").build();
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.FORTH,
            name = "财富自由之路")
    @WxAsyncMessage
    public WxMessage getLink() {
        return WxMessage.newsBuilder().addItem("财富自由之路", "财富自由之路财富自由之路",
                "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png",
                "http://baidu.com").build();
    }

    /**
     * 接受微信事件
     *
     * @param wxRequest
     * @param wxUser
     */
    @WxEventMapping(type = WxEvent.Type.UNSUBSCRIBE)
    public void unsubscribe(WxRequest wxRequest, WxUser wxUser) {
        System.out.println(wxUser.getNickName() + "退订了公众号");
    }

    /**
     * 接受用户文本消息，异步返回文本消息
     *
     * @param content
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT)
    @WxAsyncMessage
    public String text(WxRequest wxRequest, String content) {
        WxSession wxSession = wxRequest.getWxSession();
        if (wxSession != null && wxSession.getAttribute("last") != null) {
            return "上次收到消息内容为" + wxSession.getAttribute("last");
        }
        return "收到消息内容为" + content;
    }

    /**
     * 接受用户文本消息，同步返回图文消息
     *
     * @param content
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "1*")
    public WxMessage message(WxSession wxSession, String content) {
        wxSession.setAttribute("last", content);
        return WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title(content).description("随便一点")
                        .picUrl("http://k2.jsqq.net/uploads/allimg/1702/7_170225142233_1.png")
                        .url("http://baidu.com").build())
                .addItem(WxMessageBody.News.Item.builder().title("第二条").description("随便二点")
                        .picUrl("http://k2.jsqq.net/uploads/allimg/1702/7_170225142233_1.png")
                        .url("http://baidu.com").build())
                .build();
    }

    /**
     * 接受用户文本消息，异步返回文本消息
     *
     * @param content
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "2*")
    @WxAsyncMessage
    public String text2(WxRequestBody.Text text, String content) {
        boolean match = text.getContent().equals(content);
        return "收到消息内容为" + content + "!结果匹配！" + match;
    }
}
