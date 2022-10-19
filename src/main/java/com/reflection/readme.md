# java reflection
- reflection(反射)是被视为动态语言的关键，反射机制允许程序在 **执行期** 借助于reflection API 获得**任何类的内部信息，并能直接操作任意对象的内部属性及方法**。
- 加载完类后，在堆内存的方法区中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类的结构信息。我们可以通过这个对象看到类的结构。这个对象就像是一面镜子，透过这个镜子看到类的结构，所以，我们形象地称之为：反射。
    1. 正常方式： 引入需要的包类名称 --> 通过new方式实例化 --> 获取实例化对象
    2. 反射方式：实例化对象 --> getClass（）方法 --> 得到完整的 ‘包类’名称
    
- Java反射机制提供的功能
    1. **在运行时**判断任意一个对象所属的类
    2. **在运行时**构造任意一个类的对象
    3. **在运行时**判断任意一个类所具有的成员变量和方法
    4. **在运行时**获取泛型信息
    5. **在运行时**调用任意一个对象的成员变量和方法
    6. **在运行时**处理注解
    7. 生成动态代理
    
- 反射相关的主要API
    1. java.lang.Class : 代表一个类
    2. java.lang.reflect.Method : 代表类的方法
    3. java.lang.reflect.Field : 代表类的成员变量
    4. java.lang.reflect.Constructor : 代表类的构造器
    