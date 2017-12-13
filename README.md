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
######   配置 : okhttp Retrofit Gson interceptor adapter-rxjava
    1.定义一个接口  接口里面定义了请求的方法 返回值的类型

    2.构建Retrofit
     可以传入初始化的参数 baseurl

    3.初始化创建
     creat(接口.class).调用接口里面的方法
     post请求接口里面的参数是@Body User user 就是自己创建的一个请求体
    单例工厂
     1.私有化构造
     2.伴生对象 懒加载 这也是一个线程安全的
     3.init{ 初始化Retrofit对象 设置base地址 拦截器 gson转换 client}

    4.调用
####   Dagger2 注入神器
    依赖注入

    步骤1：查找Module中是否存在创建该类的方法。
    步骤2：若存在创建类方法，查看该方法是否存在参数
        步骤2.1：若存在参数，则按从**步骤1**开始依次初始化每个参数
        步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
    步骤3：若不存在创建类方法，则查找Inject注解的构造函数，
               看构造函数是否存在参数
        步骤3.1：若存在参数，则从**步骤1**开始依次初始化每个参数
        步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束

#####         @Inject和@Component
        @Inject 标注构造函数类 标注实例属性
        在ClassB里面标注构造函数
            class ClassB @Inject constructor(){
            fun asyHello(){
            print("hello")}
            }
        在ClassA里面标注ClassB的属性
            class ClassA{
            @Inject
            lateinit var mClassB : ClassB
            fun doSomeThings(){
            MClass.sayHello()
            }
            }
        怎么联系起来  Component  连接实例属性和类声明
        可以说这个实例是经过类的构造方法创建的
        @Component 注入器 连接目标类和依赖实例的桥梁
        以@Component标注的类必须是接口或者是抽象类
        @component 依赖关系是通过dependencies属性添加
        APp必须有一个全局的Component来管理全局实例
#####    @@Module和@Provides
      @Module
       使用场景 : 第三方库无法修改,不能在其构造上添加@Inject
                 接口不能实例化 ,只能通过实现类实例化
       Module是一个简单工厂,创建类实例的方法
       component通过modules属性加入多个module
     @Provides
         在module中,使用它标注创建实例的方法

#####    @Scope和@Singleton
      @Scope作用域
      主要用于component的组织方式
      管理Component和Module的关系
      @Singleton 并没有实现单例的=能力 是Scope的一种默认实现

     自定义Scope

#####    @Qualifier和@Named 限定符

#### Dagger2的两种提供注入实例的方式。
    1.   在ClassB里面标注构造函数
                  class ClassB @Inject constructor(){
                  fun asyHello(){
                  print("hello")}
                  }
              在ClassA里面标注ClassB的属性
                  class ClassA{
                  @Inject
                  lateinit var mClassB : ClassB
                  fun doSomeThings(){
                  mClassB.sayHello()
                  }
                  }
                   怎么联系起来  Component  连接实例属性和类声明
                 在Component中提供   fun getClassB :ClassB
