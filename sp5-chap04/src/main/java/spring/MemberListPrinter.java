package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

public class MemberListPrinter {
  @Autowired
  private MemberDao memberDao;
  @Autowired
  @Qualifier("printer")
  private MemberPrinter printer;
  
  public MemberListPrinter() {
  }
  
  public void printAll() {
    Collection<Member> members = memberDao.selectAll();
    members.forEach(m -> printer.print(m));
  }
}
