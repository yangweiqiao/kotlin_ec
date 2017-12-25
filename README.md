## 项目介绍
## 模块化
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
## 项目中用到的技术
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
## 项目初始化 包结构设计
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
### Rxkotlin  RxAndroid
    基于RxJava
    使用1版本 导包
    使用 :1.Service 创建接口方法
    2.        userService.register(username,password,code)
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribeOn(Schedulers.io())
                      .subscribe { object:Subscriber<Boolean>( ){
                          override fun onCompleted() {
                          mview.on....()实现回调方法 通过P层传递信息到view层
                          }

                          override fun onError(e: Throwable?) {
                          }

                          override fun onNext(t: Boolean?) {
                          }

                      } }
      3.改造下方法
                open class BaseSubscriber<T>(val baseView: BaseView) : Subscriber<T>() {
                    override fun onNext(t: T) {

                    }

                    override fun onCompleted() {
                        baseView.hideLoading()
                    }

                    override fun onError(e: Throwable?) {
                        baseView.hideLoading()
                        if (e is BaseException) {
                            baseView.onError(e.msg)
                        }
                    }

                }
                这里面就是集中的处理了多余的方法 onError onCompleted
                让他传进来view的目的是实现view中错误的回调
    4.改造  .observeOn(AndroidSchedulers.mainThread())
                               .subscribeOn(Schedulers.io())
                               .subscribe
                               这一块是一样的 我们可以使用扩展方法来简化
               扩展方法
                   fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
                       this.observeOn(AndroidSchedulers.mainThread())
                               .compose(lifecycleProvider.bindToLifecycle())
                               .subscribeOn(Schedulers.io())
                               .subscribe(subscriber)
                   }
    5.


## Retrofit 集成与单例工厂
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
## Dagger2
### 依赖注入
    目标类中需要用到其他类,首先需要创建其他类的对象,依赖注入就是不需要我们手动的创建对象,通过技术手段把其他类已经初始化好的对象实例注入到目标类中
    一般的我们可以称依赖注入为控制反转.控制反转分为依赖注入和依赖查找,依赖注入比较常用
### java注解
   也叫元数据.一种代码级别的说明
### dagger2中的inject component module provides 含义,有什么用 ?
###### Inject
       用注解来标注目标类中所依赖的其他类,
       同样采用注解来标注所依赖的其他类的构造函数,那注解的名字就是inject
###### Component
        Component也是一个注解类,一个类想要是Component,必须用Component注解来标注该类,
        并且是接口或者是抽象类
                @Component 注入器 连接目标类和依赖实例的桥梁
                以@Component标注的类必须是接口或者是抽象类
                @component 依赖关系是通过dependencies属性添加
                APp必须有一个全局的Component来管理全局实例
        工作原理 : Component需要引用到目标类的实例,Component会找目标类中用Inject注解标注的属性,
        查找到相应的属性后会接着找该属性对应的用Inject标注的构造函数,就发生联系了,
        剩下的工作就是初始化该属性的实例并且把实例进行赋值.我们可以叫他注入器
###### module
          使用场景 :项目中使用到第三方库,这个时候Inject就不能用了
                     接口不能实例化 ,只能通过实现类实例化,这个时候Inject也不能用了
                     Module其实就是一个简单的工厂模式,Module里面的方法基本都是创建类实例的方法
       Component(管理者)可以通过modules属性加入多个Module
###### provides
       在module中,使用它标注创建实例的方法

#### 执行流程
            依赖注入
             Module的优先级高于Inject构造函数
            步骤1：查找Module中是否存在创建该类的方法。就是用 @Provides 标注的providesXXX的方法
            步骤2：若存在创建类方法，查看该方法是否存在参数
                步骤2.1：若存在参数，则按从**步骤1**开始依次初始化每个参数
                步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
            步骤3：若不存在创建类方法，则查找Inject注解的构造函数，
                       看构造函数是否存在参数
                步骤3.1：若存在参数，则从**步骤1**开始依次初始化每个参数
                步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束

###### 如果执行到@inject标注的属性的时候,我们呢就去module中先找provides标注的方法 ,找不到就找在实例类构造前有没有加@inject
                     可见module的优先级是高于inject构造函数的

#### Scope 作用域
        用处就是Component的组织
        更好的管理Component之间的组织方式,不管是依赖方式还是包含方式,都有必要用自定义的Scope注解标注这些Component,这些注解最好不要一样,不一样是为了能更好的体现出
        component之间的组织方式,还有编辑器检查依赖关系或者是包含关系的Component,若发现有Component没有用自定义的scope注解标注,就会报错
        更好的管理component与module之间的匹配关系,编译器会检查Component管理的Modules,若发现Component的自定义的Scope注解与Modules中的标注创建类实例方法的注解不一样,就会报错
#####    @Scope和@Singleton
              @Scope作用域
              主要用于component的组织方式
              管理Component和Module之间的匹配的关系
              提高可读性,见名知意
              @Singleton
              并没有实现单例的能力 是Scope的一种默认实现
              ApplicationComponent单例是由代码控制实现
             自定义Scope
             以Component组织方式自定义Scope
               @Scope
               @Retention(RetentionPolicy.RUNTIME)
               annotation class ActivityScope
            没有作用域的component的不能依赖有作用域的component

##### @Qualifier 限定符
        解决依赖注入迷失(同一个接口有多个实现类,编译报错,分不清楚使用哪一个实现类)
###### @Named
       Qualifier的一种实现方式 ,以名称来区分使用哪种注解实现
###### 自定义Qualifier
       @Qualifier
       @Retention(RetentionPolicy.RUNTIME)
       annotation class ActivityQualifier

## RxLifecycle
    解决Rx内存泄漏
    通过监听Activity,Fragment的生命周期,自动断开Rx连接
## AppManager
    1.提供了app中Activity的管理
    实现自己的栈结构
    用单例实现 私有的构造方法+伴生对象
    class AppManager private constructor() {
         //实例化 stack
        private val activityStack: Stack<Activity> = Stack()

        companion object {

            val instance: AppManager by lazy { AppManager() }

        }
     //入栈的方法
      fun addActivity(activity: Activity) {
             activityStack.add(activity)
         }

         //出栈
         fun finishActivity(activity: Activity) {
             activity.finish()
             activityStack.remove(activity)
         }
         //获取当前栈顶的activity
         fun currentActivity(): Activity {
             //最后一个元素 压栈的形式 所以最后一个就是在栈顶的
             return activityStack.lastElement()
         }

         // 清理所有栈 出栈
         fun finishAllActivity() {
             for (activity in activityStack) { //循环
                 activity.finish()
             }
             activityStack.clear()
         }


     @SuppressLint("MissingPermission")
     //退出app
         fun exitApp(context: Context) {
             finishAllActivity()
             val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
             activityManager.killBackgroundProcesses(context.packageName)
             System.exit(0)
         }
     }

   直接放在baseActivity中
    双击退出App的方法

             //监听返回按键的事件
             override fun onBackPressed() {
             //获取当前的时间 time2
              val time2 = System.currentTimeMillis()
              //当前的时间 - 上次点击的时间
              if (time2 - time(上次点击的时间) > 2000) {

                toast("再按一次退出... ")
                //这是第一次进来 把当前的时间赋值给点击的时间 用作下次点击判断时间差
                time = time2
            } else {
            //如果在2秒钟之内 退出App
                AppManager.instance.exitApp(this)
            }


        }


## HeaderBar的封装
    通过自定义控件封装顶部导航栏
    1.layout文件
    <?xml version="1.0" encoding="utf-8"?>
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    android:background="@color/colorPrimary"
                    android:layout_width="match_parent"
                    android:layout_height="@dimen/common_header_bar_height">
      //左边 布局 一般是返回按钮
        <ImageView android:id="@+id/mLeftIv"
                   android:paddingLeft="@dimen/common_padding"
                   android:paddingRight="@dimen/common_padding"
                   style="@style/WrapMatch"
                   android:src="@drawable/icon_back"/>
       //标题
        <TextView
                android:id="@+id/mTitleTv"
                android:textSize="@dimen/text_large_size"
                android:textColor="#333333"
                style="@style/WrapWrap"
                android:layout_centerInParent="true"/>
        //右布局 一般是消息 之类的
        <TextView
                android:id="@+id/mRightTv"
                style="@style/WrapMatch"
                android:textColor="@color/common_black"
                android:gravity="center"
                android:paddingLeft="@dimen/common_padding"
                android:paddingRight="@dimen/common_padding"
                android:visibility="gone"
                android:layout_alignParentRight="true"
                android:layout_centerVertical="true"/>
    </RelativeLayout>
    2.自定义布局
    class HeaderBar @JvmOverloads constructor(
              context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
      ) : FrameLayout(context, attrs, defStyleAttr) {
      //重写构造方法  在java里面 我们一般是重写三个构造方法
      //在kotlin中 我们可以使用@JvmOverloads constructor(
                          //           context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
                        //     )   后面的两个参数  我们不传的可以使用的是默认值
          //定义一些变量
          private var isShowback = true
          private var titleText: String? = null
          private var rightText: String? = null

          //初始化 在init方法中 初始化布局  设置style
          init {
          //自定义属性
              val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
              //取出在布局中定义的属性
              isShowback = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
              titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
              rightText = typedArray.getString(R.styleable.HeaderBar_rightText)
              initView()
              typedArray.recycle()

          }
      //初始化控件的方法
          private fun initView() {
          //填充布局
              View.inflate(context, R.layout.layout_header_bar, this)
              mLeftIv.visibility = if (isShowback) View.VISIBLE else View.INVISIBLE
              titleText?.let {
                  mTitleTv.text = it
              }
              rightText?.let {
                  mRightTv.text = it
                  mRightTv.visibility = View.VISIBLE
              }

              mLeftIv.onClick {

                  if (context is Activity)
                      (context as Activity).finish()
              }

          }


          fun getRightView(): TextView {
              return mRightTv
          }

      }
        自定义属性
            <declare-styleable name="HeaderBar">
                <attr name="isShowBack" format="boolean" />
                <attr name="titleText" format="string" />
                <attr name="rightText" format="string" />
            </declare-styleable>


## ProgressLoading的封装
## 用户注册
## 用户登录
## 忘记密码 重置密码


## 闲的没事 配置linux 下的java 开发环境
     1.安装java jdk
     2.下载jdk
     3.推送到服务器中
     4.登录Linux，切换到root用户 su root 获取root用户权限，当前工作目录不变(需要root密码)
     5.在usr目录下建立java安装目录
     　        　cd /usr
     　          　mkdir java
     6.将jdk-8u60-linux-x64.tar.gz拷贝到java目录下
   　            　cp /mnt/hgfs/linux/jdk-8u151-linux-x64.tar.gz /usr/java/
     7.解压jdk到当前目录,得到文件夹 jdk1.8.0_*　　(注意：下载不同版本的JDK目录名不同！)
   　　          tar -zxvf jdk-8u151-linux-x64.tar.gz
     8.安装完毕为他建立一个链接以节省目录长度
   　        　ln -s /usr/java/jdk1.8.0_151/ /usr/jdk
    9.编辑配置文件，配置环境变量
   　　vim /etc/profile
   　　在文本的末尾添加如下内容：
           JAVA_HOME=/usr/jdk
           CLASSPATH=$JAVA_HOME/lib/
           PATH=$PATH:$JAVA_HOME/bin
           export PATH JAVA_HOME CLASSPATH
     10.重启 udo shutdown -r now

     9、查看 java -version

       　　java version "1.8.0_151"
       　　Java(TM) SE Runtime Environment (build 1.8.0_60-b27)
       　　Java HotSpot(TM) Client VM (build 25.60-b23, mixed mode)

       