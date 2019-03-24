
# StargazerProjectCloudSystem
<font color="#28638b">  观星者计划 - 分布式云计算框架</font></br>
（文档正在书写, 最近更新日期 2019/3/25 Version : 35.0.1）

       StargazerProjectCloudSystem是StargazerProject的顶级项目，作为云计算中间事务框架，用于承担海量请求的冲击。不光承担海量的用户请求，还要承担着与云计算系统的海量数据交互，其本身也可以进行定制化的云计算及云事务。

# 文档目录

----------

##  * StargazerProjectCloudSystem 简介
       <font color="#28638b">StargazerProjectCloudSystem并不是用于颠覆Hadoop或者是Spark这类云计算框架，这点我在开发初期很迷茫，我错误的一直认为自己在开发传统的云计算框架，直到近2年前开始第3次大规模重构的时候，我才发现，我究竟需要什么。</br>
       我要一种去中心化的流式计算网络，这个网络的可以使用任何可以使用的计算设备，拥有极强的稳定性和承载能力和扩展能力，能够根据现在的需求自主的进行构架设计和布局，能够脱离人的参与控制，这个集群能够通过使用Hadoop或者是Spark等任何可以使用的外部云计算子集群为自己服务。
</font>

----------

##  * StargazerProjectCloudSystem 能力
####         - <font color="#28638b">高等级容错</font>
       即使出现错误，无论是系统工作出现错误，还是使用者人为的造成错误，都不会影响整体，不会干扰其他的工作，高级的容错能力为整个系统提供了极强的稳定性保证。
####         - <font color="#28638b">支持灾难重组（自愈合）</font>
       容错与灾难重组是不可分开的一部分，任何系统的容错是有极限的如果把整个集群断电，那么，即使再强大的容错都将不起作用，所以，作为容错的最后一道防线，灾难重组就显得非常的重要，灾难重组指的是允许集群内指定数目内的计算节点下线，并且排除故障后允许其重新上线及入队，或者系统遭受毁灭性打击，能够以最快速的速度重新部署上线运行。</br>
       灾难重组是现代系统必须拥有的的能力，在大规模分布式集群内，尤其是去中心化的集群，子节点的加入和退出都将变得非常频繁，子节点的失去响应与错误行为都将持续出现，所以，必须保障即使系统被攻击，也要能在最短的时间内重新愈合上线。
####         - <font color="#28638b">高并发</font>
       作为分布式集群，其最重要的目的就是高并发。传统依赖几台服务器完成所有工作的时代已经过去了，为了给更多的人提供更快服务，必须依赖上千上万甚至是上亿的服务器或者任何可以提供计算的设备提供算力。而过去的单机或者小集群的并发手段已经不再适合现代的大规模集群，在算力充足的时代。如何利用好这些算力，提供高并发能力，成为架构设计的新挑战。
####         - <font color="#28638b">分布式事务</font>
      无论是CAP理论还是BASE理论，分布式事务在当今的架构设计中，依然是最前沿的研究对象，如何取舍，如何权衡，是每个架构师需要更深入研究的问题。</br>
      把所有的算法分布在不同的节点上，或者建立某一个算法的小集群今天看来并不是一个最优秀的解决方案，在大部分情况下，将浪费大量的时间在网络上和排队等待上，尽量把相关联的算法放置到同一个计算节点上，尽量减少分布式算法的流转和等待时间，使得一个事务尽量在一个节点完成，并且根据负载状况进行算法的分布及管理，为分布式事务持续提供优化。</br>
####         - <font color="#28638b">实时计算能力</font>
      支持流式计算，在限定时间内完成事物或者报告超时错误，在事物分发阶段，可以根据分布式系统的负载情况给出运算模拟情况，如果模拟情况没有满足要求，会警告上游使用者从而采取模拟超时策略。
####         - <font color="#28638b">模拟分析能力</font>
      在事务运行前，就可以根据系统状况（由去中心化分布式模拟与调节引擎提供）模拟分析出事务的运行流程，事务的运行时间及等待时间等。
####         - <font color="#28638b">动态调节能力</font>
      去中心化分布式实时模拟与调节引擎（Decentralized distributed real-time simulation and tuning engine 简称：DD-RSTE）能够实时的根据负载和未来需求指引调节系统整体或者局部架构、负载分布、计算节点分布、节点内部算法分布等。DD-RSTE不是系统的中心，而是系统中一个重要的调节子系统，其决议需要部分节点达到一定的通过率才可以进行。
####         - <font color="#28638b">去中心化的集群</font>
      当今时代，集群的中心化和去中心化依然是争论的焦点，去中心化真的是任何瓶颈的解决办法吗？不一定，比特币系统的成功，其背后还是存在着很多的取舍，为了地域双花攻击，需要巨大算力的保障，POW导致的整体非常缓慢的事务，这些都是当今去中心化需要面对的问题，但是随着研究的进步，未来，去中心化一定会成为新的发展方向。
####         - <font color="#28638b">集群的横向扩展及纵向扩展</font>
      根据现有系统的负载及产能报告的情况进行系统的的水平扩张或水平衰退。
####         - <font color="#28638b">空中编译及分布式运算模块的动态注入</font>
      支持代码的空中编译及运行时动态加载及动态注入，可以把编写好的模块代码注入集群，支持热部署及红黑部署。
####         - <font color="#28638b">运算模块的自行动态均衡</font>

####         - <font color="#28638b">代码检测</font>

####         - <font color="#28638b">核心模块化</font>

####         - <font color="#28638b">基于区块链的分布式存储</font>

####         - <font color="#28638b">基于人工智能的系统审计集及系统内动态构架</font>


----------
##  * StargazerProjectCloudSystem 模块介绍

####         - <font color="#28638b"> Cache 缓存模块介绍</font>
####         - <font color="#28638b"> Queue 队列介绍</font>
    框架采用非阻塞的高速Disruptor队列，可以灵活的配置消费者线程及等待寻轮策略，并可以显著控制高速队列的背景功率（Power Background，意指高速队列在空闲时期其反复空寻轮对系统资源的占用率）。
####         - <font color="#28638b"> Log 日志介绍</font>
####         - <font color="#28638b"> Bus 总线介绍</font>
####         - <font color="#28638b"> Server 服务介绍</font>
####         - <font color="#28638b"> Sequence 序列介绍</font>
####         - <font color="#28638b"> UserInterface 界面介绍</font>
      StargazerCloud使用的是定制化的StargazerCellsUI，以下为UI的启动界面及使用界面，UI已经开发完毕，因为其依赖了StargazerCloud的一些内部模块，所以就不单独发布了。
启动界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/1.jpeg)
Translucent Surface运行界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/2.jpeg)
启动界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/7.jpeg)
Black Lotus运行界面截图
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/6.jpeg)
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/4.jpeg)
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/3.jpeg)
![image](https://github.com/pisual/StargazerProjectDistributedSystemVessel/blob/master/UIShow/5.jpeg)
####         - <font color="#28638b"> Negotiate 协商介绍</font>
      协商服务模块负责分布式系统整体架构的构建，StargazerCloud系统并没有要求强行指使用定某一种架构模式，架构是演变与进化的，今天的架构并不一定适合明日的架构，这个项目的架构模式也不适用另外项目的，保持架构的灵活性是为了延长StargazerCloud生存周期，使得StargazerCloud能够不断的分支演化，StargazerCloud在架构哲学上遵守着最基本的一点就是协商架构，在StargazerCloud系统之内，节点的分化及架构成型，是由模型来进行指定，每一次系统新生都不会形成完全相同的架构。</br>
      现阶段，Stargazer分布式系统的拓扑结构是扁平化布局，使得节点的加入和退出或者局部扩张和消亡对整体构架造成的影响降到最低</br></br>
      
                                                                局部结构图：</br>
                                                           Group - Group - Group * *</br>
                                                           (根据需求自行构建Group) </br>
                                                         Cell - Cell - Cell - Cell - Cell *</br></br>
      Group是一个内部包含节点的拓扑结构，根据BackupLevel（集群事务备份级别）来确定包含的数目以此实现备份级别。</br></br>
例如：</br>
                                                             BackupLevel（节点备份级别）: 3</br>
                                                             Group A (Cell 1 : Cell 2 : Cell 3)</br></br>
      组内节点互相跟踪，节点分为跟踪节点（Follow，用于同步跟踪节点）和 领导节点（Leader，负责实际的运算），为了提高负载能力系统将根据BackupLevel来进行固定分组</br></br>
      
                                                                        运算结构图：</br>
                                                            Group A (Cell 1 : Cell 2 : Cell 3)</br>
                                           CellsGroup1 (Cell 1(Leader) : Cell 2(Follow) : Cell 3(Follow))</br>
                                           CellsGroup1 (Cell 1(Follow) : Cell 2(Leader) : Cell 3(Follow))</br>
                                           CellsGroup1 (Cell 1(Follow) : Cell 2(Follow) : Cell 3(Leader))</br></br>

      组成员将会定期通信来记录已经处理完成的指令，Leader将定期发送指令来指示Follow推进备份指令队列的位置</br>
### <font color="#733049">        扩展说明-分布式区域协议：</font>
      Cell节点需要自行组建Group结构，依靠共识算法（例如比特币的POW工作量证明）来获取组建的权利，分布式区域协议的建组是动态模式，任何时候都可以进行自由建组。</br>
      计算节点按照其容灾策略自行成组（Group），并进一步自行结合成区（Zone），在节点下线的情况下提供再均衡及重组的能力。</br>


####         - <font color="#28638b"> Cell 细胞</font>
####         - <font color="#28638b"> MessageQueue 消息队列</font>
####         - <font color="#28638b"> Transaction 事物及事件</font>
####         - <font color="#28638b"> Check 检测</font>
####         - <font color="#28638b"> Resources 资源</font>
####         - <font color="#28638b"> @SHELL壳式编程</font>
       @SHELL是一种对于未来编程的描绘与设计，很多我开发的项目，最后都死在了无法维护、无法更新、无法更迭上，随着项目的不断扩张，原来能稳定运行的项目不断被腐蚀，最后，巨大的无法维护的代码堆就摆在一代代后来人的眼前，无法重构，没人能了解这个代码堆的全局架构，最后，往往是成为一个被死去的孩子。</br>
       我们能开发出一个万能的架构吗？对不起不能。这是一个虚幻的梦，但是我们能做的，我们最容易做的就是保持这个项目架构的可成长性，使得这个项目不断成长的代价降低，使得这个项目在搭建初期就能为如今或者未来描绘好成长的轨迹，即使有一日，她再也不能承担起重任需要逝去，她，也是以闪亮的姿态谢幕，而不是一堆烂代码。</br>
       @SHELL壳式编程不是终点，未来可能会有更多天才的解决方法，但是如今她为超大型项目的构建提供了未来解决方案，在StargazerProjectCloudSystem中，经过漫展与痛苦第一次SHELL大型重构，重构后的代码已经全部采用了@SHELL的编程方案，初见就展现了其独特的魅力。在Spring的辅助下，@SHELL更是极大的缩小了代码量，下面，放空心态，与她一起走近@SHELL的世界。</br>

# FelixErio 作者后记：
       每个人都有一个伴随了自己大半个人生的故事，总有一天会拿出来讲一讲，每个人都会有自己最大的隐藏的能力，默默的在深夜里磨练，希望有他成熟的一天，会闪耀在夜空中，那么，就让我讲一讲我的故事吧。</br>
       从第一次接触编程，我就认为，编程是未来世界的改编者之一，从Pisual项目的幼稚到StargazerProject项目的成熟，未来总会有一天，在某某项目里继续成长，正是不断的书写，不断的完善，不断的重构，给了我从幼稚到成长的故事。</br>
       StargazerProject顶层项目CloudSystem的最初的设想很简单，首先设计出一个工作引擎，这个工作引擎可以完成大量的工作，系统会自动的设计指令，自动的调度运行，自动的运算结果，自动的分布布局，自动的容灾重组，并在指定时间内返回结果，这就是我最初希望的结果，抛掉Hadoop，抛掉Spark，最为纯正的需要，最为纯正的设想。</br>
       现在看来，这是多么的奢想，这是多么的梦幻，没想到这简简单单的几句话就写了5年代码。</br>
       至今为止，正如前面说的，StargazerProjectCloudSystem项目已经书写了近5年，重构了8次，其子成果已经用在其它很多的项目之上，因为我本身是一名UI设计师、前端技术设计师，动画师、摄影师、平日里还要在银行工作，留给我的时间并不多，但即使如此，正如小镇在慢慢的向前前进者，我也在尽力的书写者，这便是爱吧。虽然我在银行的工作并不涉及到编程，但是这个平凡的工作确实给了我很多，给了我很多的时间与精力去完成我自己想做的事情，有时候，看到开发中心的正在痛苦编程的人们，不由得苦笑一下，她们，可能永远都不知道身后站着一位与编程恋爱的人吧。直到现在，我还保持者一个习惯，经常打开我在的银行的所使用的系统，对里面的代码如数家珍，心里幻想一下，好像是我的孩子一样。</br>
       最后，说一些最后的话语，StargazerProject给我最大的启示，那就是代码不是重要的东西，代码并不是你的成果，谁都可以写出来的，最重要的是你，是你书写代码的那种感觉，是你设计架构的那种泰然自信，是你那种真正爱他的这种灵魂，所以，我将我的书写过的全部代码开源，如果能在茫茫人海中与你相见，给看到这里的你一点启示，那便是我最大的快乐了。</br>
       最后的最后，StargazerProject顶级项目CloudSystem预计2018年上半年将开发出第一个测试版本，并用于承担StargazerProject项目组内所有的图像分析及计算任务。</br>
  
  
                           StargazerProject首席设计师 FelixErio & 一直在背后努力的喵小姐
