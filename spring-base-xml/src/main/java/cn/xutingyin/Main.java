package cn.xutingyin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: Spring Bean主类测试,Spring为我们提供了丰富的应用上下文工具类：
 *               a、AnnotationConfigApplicationContext：从一个或多个基于Java配置类中加载Spring应用上下文。
 *               b、AnnotationConfigWebApplicationContext：从一个或多个基于java的配置类中加载Spring Web应用上下文。
 *               c、ClassPathXmlApplicationContext：从类路径下的一个或多个xml配置文件中加载上下文定义，把应用上下文定义文件作为类资源。
 *               d、FileSystemXMLApplicationContext：从文件系统下的一个或多个xml配置文件中加载上下文定义。
 *               e、XmlWebApplicationContext：从Web应用的一个或多个xml配置文件中加载上下文定义。
 *               其中AnnotationConfigWebApplicationContext、XmlWebApplicationContext都需要引入spring-web 这个jar包，才支持 BeanFactory
 * 
 *               注：BeanFactory在程序启动的时候，不会创建Bean的实例；只有在getBean()的时候才创建；而ApplicationContext在程序启动的时候，就进行了Bean实例的创建。
 * @author: xuty
 * @date: 2020/8/18 16:07
 */

public class Main {

    public static void main(String[] args) {
        // 基于XML 配置、使用ApplicationContext获取Bean
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // Human human = context.getBean("human", Human.class);
        // System.out.println(human);
        //
        // A a = context.getBean("a", A.class);
        // System.out.println(a);
        // 使用BeanFactory[这里使用具体实现类：DefaultListableBeanFactory]方式获取Bean
        // ClassPathResource resource = new ClassPathResource("spring.xml");
        // // BeanFactory factory = new XmlBeanFactory(resource); // XmlBeanFactory已过时，官方不推荐使用-- 替代的是如下的写法
        // BeanFactory factory1 = new DefaultListableBeanFactory();
        // BeanDefinitionReader bdr = new XmlBeanDefinitionReader((BeanDefinitionRegistry)factory1);
        // bdr.loadBeanDefinitions(resource);
        // // 获取所有加载的Bean
        // String[] beanDefinitionNames = ((BeanDefinitionRegistry)factory1).getBeanDefinitionNames();
        // List<String> beanNames = Arrays.asList(beanDefinitionNames);
        // beanNames.forEach(beanName -> {
        // System.out.println(beanName);
        // });
        // Human human = (Human)factory1.getBean(Human.class);
        // System.out.println(human);
        // Human human = (Human)factory.getBean("human");
        // System.out.println(human);
        // 基于配置类获取Bean
        /*     ApplicationContext context1 = new AnnotationConfigApplicationContext(MyConfig.class);
        Human human2 = context1.getBean("human", Human.class);
        System.out.println(human2.getAudi());
        System.out.println(human2.getBmw());
        System.out.println(human2.getBenz());
        
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(A.class);
        A a = applicationContext.getBean("a", A.class);
        System.out.println(a);*/
    }
}
