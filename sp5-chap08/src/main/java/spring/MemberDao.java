package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;

public class MemberDao {
  private JdbcTemplate jdbcTemplate;

  public MemberDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public Member selectByEmail(String email) {
    List<Member> results = jdbcTemplate.query(
            "select * from MEMBER where EMAIL = ?",
            new RowMapper<Member>() {
              @Override
              public Member mapRow(ResultSet resultSet, int i) throws SQLException {
                Member member = new Member(
                        resultSet.getString("email"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("NAME"),
                        resultSet.getTimestamp("REGDATE").toLocalDateTime());
                member.setId(resultSet.getLong("ID"));

                return member;
              }
            },
            email);

    return results.isEmpty() ? null : results.get(0);
  }
  
  public void insert(Member member) {
  }
  
  public void update(Member member) {
  }

  public int count() {
    Integer count = jdbcTemplate.queryForObject(
            "select count(*) from MEMBER", Integer.class);
    return count;
  }
  
  public List<Member> selectAll() {
    List<Member> results = jdbcTemplate.query(
            "select * from MEMBER",
            new RowMapper<Member>() {
              @Override
              public Member mapRow(ResultSet resultSet, int i) throws SQLException {
                Member member = new Member(
                        resultSet.getString("email"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("NAME"),
                        resultSet.getTimestamp("REGDATE").toLocalDateTime());
                member.setId(resultSet.getLong("ID"));

                return member;
              }
            });

    return results;
  }
}
