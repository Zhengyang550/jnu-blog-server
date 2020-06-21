##Spring Aop底层原理 https://blog.csdn.net/baomw/article/details/84262006
#### 什么时aop？
* aop是面向切面的，通过jdk代理或者cglib实现。可以对指定对象的某个方法进行增强；
* springIoc可以对我们应用程序中的java对象做一个集中化的管理，从而使我们从繁琐的new Object()；中解脱出来。
  其核心思想呢就是先创造出一个bean工厂，也就是我们的beanFactory，通过beanFactory来生产出我们应用程序中所需要的java对象，也就是我们的java bean。

## 后置处理器
##### * BeanFactoryPostProcessor:可以插手beanFactory的生命周期

##### * BeanPostProcessor：可以插手bean的生命周期，该接口定义了两个方法，分别在bean实例化之后放到我们的容器之前和之后去执行，方法的返回值为一个object，这个object呢就是我们存在于容器的对象了（所以这个位置我们是不是可以对我们的bean做一个动态的修改，替换等等操作，所以这也是我们spring的扩展点之一，后面结合我么自己手写aop来详细讲解这个扩展点的应用）；

##### * ImportSelector：借助@Import注解，可以动态实现将一个类是否交由spring管理，常用作开关操作
在spring处理我们的java类的时候，会分成四种情况去处理
1. 普通类：就是@Component，@Service，@Repository注解的类
2. 处理我们的import进来的类：这里呢，又分为三种情况：
  *   a）import一个普通类：@Import（A.class）
  *   b）import一个Registrar：比如我们的aop @Import(AspectJAutoProxyRegistrar.class)
  *   c）import一个ImportSelector：具体妙用见下文

至于spring在什么时候处理的呢，我大致叙述一下，有兴趣的可以自己去研究下spring源码：
对于普通类，spring在doScan的时候，就将扫描出来的java类转换成我们的BeanDefinition，然后放入一个BeanDefinitionMap中去
@import(ImportSelector.class)了
ImportSelector 接口有一个实现方法，返回一个字符串类型的数组，里面可以放类名，在@import(ImportSelector.class)的时候，spring会把我们返回方法里面的类全部注册到BeanDefinitionMap中，继而将对象注册到Spring容器中


### 实现aop
1. 我们知道开启和关闭aop需要注解@EnableAspectAutoProxy，如何实现，结合上下文，我们可以使用@import(importSelector.class)
2. 如何确定代理关系、即哪些是我们需要代理的目标对象和其中的目标方法、以及哪些方法是要增强到目标对象的目标方法上去的；
3. 如何实现目标对象的替换、就是我们再getBean的时候、如何根据目标对象来获取到我们增强后的代理对象；

##### 针对问题2，由于BeanFactoryPostProcessor的所有实现 会在beanFactory完成bean的扫描后，bean的实例化之前执行，所以我们可以新建一个类，实现这个接口，然后实现方法里面主要完成对有BeanDefinition的扫描，找出我们所有的通知类，然后循环里面的方法，找到所有的通知方法，然后根据注解判断切入类型（也就是前置，后置还是环绕），最后解析注解的内容，扫描出所有的目标类，放入我们定义好的容器中。

##### 针对问题3，我们可以利用BeanPostProcessor，在bean实例化之后，在放入容器之前，进行一个条件过滤，如果当前对象是我们的目标对象（即在我们定义好的Map中），则对对象进行代理，将目标对象替换成代理对象返回即可.





