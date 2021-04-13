1.编写HelloWorldService.thrift

2.生成java文件
thrift -r --gen java HelloWorldService.thrift

3.服务端实现Service逻辑
```
public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        System.out.println("request from " + username);
        return "Hello " + username + " !";
    }
}
```
4.编写client和server代码

