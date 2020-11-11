package main;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.WrongIdPasswordException;

public class MainForCPS {
    private static final Logger logger = LoggerFactory.getLogger(MainForCPS.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);
        logger.error("hello");
        try {
            cps.changePassword("lteawoo@naver.com", "1234", "1111");
            System.out.println("암호 변경완료");
        } catch (MemberNotFoundException e) {
            System.out.println("회원이 존재 X");
        } catch (WrongIdPasswordException e) {
            System.out.println("암호가 올바르지 않음");
        }

        ctx.close();
    }
}
