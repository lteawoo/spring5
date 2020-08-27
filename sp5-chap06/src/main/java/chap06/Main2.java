package chap06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main2 {
  public static void main(String[] args) {
    AbstractApplicationContext ctx =
            new AnnotationConfigApplicationContext(AppCtx.class);

    Client2 client = ctx.getBean(Client2.class);
    client.send();

    ctx.close();
  }
}
