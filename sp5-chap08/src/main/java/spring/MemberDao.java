package spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)" +
                            "values (?, ?, ?, ?)",
                    new String[]{"ID"});
            preparedStatement.setString(1, member.getEmail());
            preparedStatement.setString(2, member.getPassword());
            preparedStatement.setString(3, member.getName());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

            return preparedStatement;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }

    public void update(Member member) {
        jdbcTemplate.update(
                "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
                member.getName(), member.getPassword(), member.getEmail());
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
