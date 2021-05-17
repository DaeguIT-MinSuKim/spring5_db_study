package spring5_db_study.spring;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	/* 결과가 1개 이상인 경우 */
    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query("select * from member where email = ?", new MemberRowMapper(), email);
        return results.isEmpty()?null:results.get(0);
    }

    public void insert(Member member) {}
    public void update(Member member) {}

    public List<Member> selectAll() {
        return jdbcTemplate.query("select * from member", new MemberRowMapper());
    }

    /* 결과과 1행인 경우 */
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from member", Integer.class);
    }

}
