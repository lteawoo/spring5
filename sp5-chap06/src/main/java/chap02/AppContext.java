package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
  
  /*
   * Bean 어노테이션을 붙인 메서드의 이름은 빈 객체를 구분할 때 사용한다.
   */
  @Bean
  public Greeter greeter() {
    Greeter g = new Greeter();
    g.setFormat("%s, 안녕하세요!");
    return g;
  }
}
