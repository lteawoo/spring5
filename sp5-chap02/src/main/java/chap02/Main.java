package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    // AnnotationConfigApplicationContext: 자바 설정에서 정보를 읽어와 빈(Bean) 객체를 생성하고 관리한다.
    AnnotationConfigApplicationContext ctx = 
        new AnnotationConfigApplicationContext(AppContext.class); // 작성한 AppContext 클래스를 생성자 파라미터로 전달한다. AnnotationConfigApplicationContext는 AppContext에 정의한 @Bean 설정 정보를 읽어와 Greater 객체를 생성하고 초기화한다.
    /*
     * GetBean 메서드는 AnnotationConfigApplicationContext가 생성한 빈 객체를 검색할 때 사용된다.
     * 첫 번째 파라미터는 @Bean 어노테이션의 메서드 이름인 빈 객체의 이름이며
     * 두 번째 파라미터는 검색할 빈 객체의 타입이다.
     */
    Greeter g = ctx.getBean("greeter", Greeter.class); 
    String msg = g.greet("스프링");
    System.out.println(msg);
    ctx.close();
  }
}
