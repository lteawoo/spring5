package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration
public class AppCtx {
  // 메서드 이름을 빈 객체의 이름으로 사용한다.
  @Bean
  public MemberDao memberDao() {
    return new MemberDao();
  }
  
  @Bean
  public MemberRegisterService memberRegSvc() {
    return new MemberRegisterService(memberDao());
  }
  
  @Bean
  public ChangePasswordService changePwdSvc() {
    ChangePasswordService pwdSvc = new ChangePasswordService();
    pwdSvc.setMemberDao(memberDao());
    return pwdSvc;
  }
}
