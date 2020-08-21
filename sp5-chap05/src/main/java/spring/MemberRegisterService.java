package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MemberRegisterService {
  @Autowired
  private MemberDao memberDao;
  
  public MemberRegisterService() {
  }
  
  public Long regist(RegisterRequest req) {
    // 주입 받은 의존 객체의 메서드를 사용
    Member member = memberDao.selectByEmail(req.getEmail());
    
    if (member != null) {
      throw new DuplicateMemberException("dup email " + req.getEmail());
    }
    Member newMember = new Member(
        req.getEmail(),
        req.getPassword(),
        req.getName(),
        LocalDateTime.now());
    memberDao.insert(newMember);
    return newMember.getId();
  }
}
