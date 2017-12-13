## 项目介绍
### 模块化
#### 什么是模块化?
    相对独立业务拆分成块,单独开发调试
    拼接业务模块,组装app
#### 为什么需要模块化?
    业务分离 解耦
    单独开发,模块之间互不影响
#### 如何实现模块化?
    公用模块抽取
    业务模块抽取
    主工程组装模块
#### 模块之间通讯?
    跨模块跳转
    跨模块接口调用
    路由框架 Arouter
### 项目采用的是多模块开发的
### MVP 架构
#### 项目中用到的技术
    kotlin-android-extensions kotlin提供的插件,不需要引入第三方库
    RxKotlin RxAndroid
    RxLifecycle 生命周期管理的
    Retrofit
    OkHttp
    Dagger2
    Gson
    Arouter 模块路由
    Glide
    takephoto
    七牛 数据云存储
    MultiStateView 多状态视图
    bga-refreshlayout 上下拉刷新
### 项目初始化 包结构设计
    BaseLibrary
    Provider
    UserCenter
    App
    kotlin的引入
    apply plugin: 'kotlin-android'

    apply plugin: 'kotlin-android-extensions'

    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"

#### BaseLibrary
     抽取公共的部分在base模块里面
#### 初始化用户模块
    Application 和 Library
    Application作为应用程序启动 : apply plugin:'com.android.application'
    Library作为库引用 : apply plugin:'com.android.library'
#### Retrofit 集成与单例工厂
    配置 : okhttp Retrofit Gson interceptor adapter-rxjava
    1.定义一个接口  接口里面定义了请求的方法 返回值的类型

    2.构建Retrofit
     可以传入初始化的参数 baseurl

    3.调用
     creat(接口.class).调用接口里面的方法
     post请求接口里面的参数是@Body User user 就是自己创建的一个请求体
    单例工厂
     1.私有化构造
     2.伴生对象 懒加载 这也是一个线程安全的
     3.init{ 初始化Retrofit对象 设置base地址 拦截器 gson转换 client}
     4.