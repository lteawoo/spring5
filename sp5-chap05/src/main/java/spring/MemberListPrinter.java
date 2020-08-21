package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("listPrinter")
public class MemberListPrinter {
  @Autowired
  private MemberDao memberDao;
  @Autowired
  private MemberPrinter printer;
  
  public MemberListPrinter() {
  }
  
  public void printAll() {
    Collection<Member> members = memberDao.selectAll();
    members.forEach(m -> printer.print(m));
  }
}
