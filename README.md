#Java葫芦娃小游戏
孔岳
171860700
957201434@qq.com
##一、环境依赖
JDK 1.8.0_131  
eclipse  
JavaFX   


##二、项目模块
###1.代码结构
####application包
Main.java  
MainScene.java
####Controller包
Controller.java
####Creature包
Bad.java
CalabashBrother.java  
CalabashBrothers.java  
CBinformation.java  
Creature.java  
Good.java  
Grandpa.java  
Nobody.java  
Scorpion.java  
Snake.java
####Formation包
Array.java  
CraneWing.java  
FangYuan.java  
Formation.java  
HengE.java  
LongSnake.java  
####World包
Map.java  
World.java
###2.模块说明
####class Main
程序入口，完成了页面布局和调用其他类对象进行运行的任务。  
页面布局：一个HBox类对象包含了三个按钮Button类对象作用分别是新对局、历史对局、开始游戏，位于一个BorderPane类对象最上面。BorderPane类对象还包含了一个自定义的画布的派生类的对象MainScene。再将BorderPane类对象赋予一个Scene类对象，Scene类对象则是Stage类对象的场景。
####class MainScene
该类是Canvas类的派生类，作用是作为世界的画布，完成了战斗过程中世界地图的变化以及背景、初始**界面的展现**，包括总控战斗的**开始、保存、回放**等。
####class Controller
该控制器类是真正操控整个战斗过程的**中枢**，实际作用是设置、清空地图上的生物，真正地将生物类、世界类联系到一起，
####class Creature
游戏中的每个生物（葫芦娃、老爷爷、蛇精、蝎子精、小喽啰）都属于该类的派生类，有攻击、防御、生命、最大生命、是否存活等属性；且每个生物都有自己单独的线程，故有一个Runnable接口，Runnable接口完成的任务是当生物存活时攻击周围敌人或者向最近的敌人靠近移动一格。
####class Good 和 class Bad
这两个类分别表示好人阵营类和坏人阵营类，都是Creature类的派生类，作用是将葫芦娃阵营和妖精阵营划分从而方便统计数量判断战斗结束。
####class CalabashBrother
该类是Good类的派生类，指葫芦娃类，封装了一些获得信息以及比较信息的操作函数。
####class CalabashBrothers
该类不是派生类，是葫芦兄弟们总体的类，聚集了7个葫芦娃类对象，封装了以index为下标查找某一葫芦娃信息的函数以及将7个葫芦娃随机排序的算法（沿用前面作业）。
####enum CBinformation
该枚举类包含了7个葫芦娃的颜色以及对应的排行、姓名等信息。
####class Grandpa 、 class Scorpion 、 class Snake 和 class Nobody
这几个类分别是Good类和Bad类的派生类，设置了对应的攻击、防御和最大血量。
####class Map
Map类是地图上一个格子代表的类，类型是泛型，用了泛型通配符extends Creature，故其泛型类只能是Creature的及其子类。也就是说每个地图格子可以存储一个生物体。
####class World
World类表示整个游戏世界地图，用来聚集整个地图的长和宽以及每个地图格子maps，成员函数有初始化地图、计算地图上好人和坏人数量、获得地图坐标(x,y)处生物体信息、判断地图(x,y)处是否为空等。
####abstract class Formation
阵型类是抽象类，有一个抽象函数用来在地图上布阵。
####class Array、class CraneWing、class FangYuan、class HengE、class LongSnake
这几个类是阵型类的派生类，具体化了每个阵型的布阵函数。





##三、游戏功能介绍及展示
###欢迎界面
![](pic1.png) 
###新对局
点击按钮“新对局”可以开始一局新的对战，葫芦娃按**长蛇阵**随机站成一列在地图左边，小喽啰则是**随机**按长蛇阵、鹤翼阵、锋矢阵、方圆阵、衡轭阵中某一阵型站位在地图右边，故再次点击“新对局”可以随机更换小喽啰阵型。
![](pic2.png) 
###历史对局
点击按钮“历史对局”可以打开文件选择器，选择一个之前保存的战斗场景(.txt文件)进行回放。
![](pic5.png) 
###开始游戏
点击按钮“开始游戏”则可以开始当前对局，血量可以通过头顶的血条近似反映。战斗过程的文件会自动保存为当前时间+“.txt”文件。 
![](pic3.png)
![](pic4.png)   
##四、涉及的Java知识
###面向对象
####封装：
如将对世界类的操作、阵型的布置封装成一个个整体，只提供对外的接口即可访问。
####继承：
所有具体的生物体要么继承自Good类，要么继承自Bad类，而Good类和Bad类继承自Creature类。
####多态：
CalabashBrother继承自Good类，并将其中某些函数方法进行重写，则不同时候父类引用（如指向子类对象）表现出的具体对象不同。
###设计原则
####单一职责原则：
一个类（如Controller类）只允许有一个职责（将生物体与地图联系到一起），无其他职责。
####里氏替换原则：
所有引用Creature类的地方都可以替换成其子类Good类或Bad类。
####CARP合成/聚合服用原则
将一个个地图块Map类对象聚合到World类；将7个CalabashBrother类对象聚合到CalabashBrothers类。
####开放封闭原则
阵型类Formation中已有阵型的布阵函数不可修改，但后期可以自由增加新的阵型，重写新的布阵函数。
###异常处理
打开文件时通过try{}catch{}判断是否能够成功打开文件，即是否会发生FILENOTFOUND的异常。
###泛型
地图类Map用到泛型<T extends Creature>，即Map格子只能存储一个Creature及其子类。
###输入输出
保存对局是将战斗过程中的地图保存成txt文件输出，历史对局则是将之前保存的txt文件输入并展示成战斗场景。
###线程
MainScene是一个的线程，作用是将战斗过程保存或者回放战斗过程；每个生物也有一个单独的线程，通过Runnable()赋予线程任务run()；且在MainScene上创建了一个线程池，开始游戏就是将地图上的生物线程加入线程池从而动起来。且通过synchronized加锁使得公共资源不能同时访问（如World.class）。