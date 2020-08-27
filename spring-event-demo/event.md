# SpringEventListener是同步执行的
默认这种事件机制是同步的，好处是如果有事务，发送事件的方法和事件处理的方法在同一个事务里，缺点就是，可能并没有实现我们想象中的异步处理，有一种方案是在处理事件的时候使用一个线程池，通过线程池来异步处理，虽然是解决了异步的问题，


# 比较EventBus与Spring Event

|项目|事件|发布者|发布方法|是否异步|监听者|注册方式|事件区分|是否支持事件簇|是否支持自定义event|是否支持过滤|是否支持事件过滤|复杂程度|
|---|---|---|---|---|---|---|---|---|---|---|---|---|
|EventBus|任意对象|EventBus|EventBus#post|是|注解Subscribe方法|手动注册EventBus#register|Class|是|是|否|是|简单|
|SpringEvent|任意对象|ApplicationEventPublisher|ApplicationEventPublisher#publishEvent|支持同步异步|注解EventLister方法|系统注册|Class|是|是|是|否|复杂|

