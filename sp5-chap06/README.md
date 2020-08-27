# 스프링은 객체 컨테이너
스프링의 핵심 기능은 객체를 생성하고 초기화 하는 것이다.

* BeanFactory 인터페이스: 객체 생성과 검색에 대한 기능을 정의한다.
* ApplicationContext 인터페이스: 메시지, 프로필/환경 변수 등을 처리할 수 있는 기능을 추가로 정의한다.
* AnnotationConfigApplicationContext: ApplicationContext의 구현체, 자바 어노테이션을 이용하여 클래스로부터 객체 설정 정보를 가져온다.
* GenericXmlApplicationContext: ApplicationContext의 구현체,XML로부터 객체 설정 정보를 가져온다.
* GenericGroovyApplicationContext: ApplicationContext의 구현체,그루비 코드를 이용해 설정 정보를 가져온다.

어떤 구현 클래스를 사용하든, 각 구현 클래스는 설정 정보로부터 빈(Bean)이라고 불리는 객체를 생성하고 그 객체를 내부에 보관한다. 그리고 getBean() 메서드를 실행하면 해당하는 빈 객체를 제공한다.

ApplicationContext(또는 BeanFactory)는 빈 객체의 생성, 초기화, 보관, 제거 등을 관리하고 있어서 ApplicationContext를 컨테이너(Contanier)라고도 부른다. 이 책에서도 ApplicationContext나 BeanFactory 등을 스프링 컨테이너라고 표현할 것이다.

# 싱글톤(Singleton) 객체
```java
public class Main2 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
    Greeter g1 = ctx.getBean("greeter", Greeter.class);
    Greeter g2 = ctx.getBean("greeter", Greeter.class);
    System.out.println("(g1 == g2) = " + (g1 == g2));
    ctx.close();
  }
}
```
(g1 == g2)의 결과가 true로 나온다 -> g1과 g2가 같은 객체라는 의미이다.

별도 설정을 하지 않을 경우 스프링은 한 개의 빈 객체만을 생성하며 이 때 빈 객체는 '싱글톤(singleton) 범위를 갖는다'고 표현다. 싱글톤은 단일 객체(single object)를 의미하는 단어다.