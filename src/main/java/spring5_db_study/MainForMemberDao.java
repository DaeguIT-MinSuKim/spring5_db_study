package spring5_db_study;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring5_db_study.config.AppCtx;
import spring5_db_study.spring.Member;
import spring5_db_study.spring.MemberDao;

public class MainForMemberDao {
	private static MemberDao memberDao;

	public static void main(String[] args) {
        try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);){
            DataSource ds = ctx.getBean(DataSource.class);
            System.out.println(ds);
            
            memberDao = ctx.getBean(MemberDao.class);
            selectAll();
        }
	}
	
	private static void selectAll() {
        System.out.println("----- selectAll");
        int total = memberDao.count();
        System.out.println("전체 데이터 : " + total);
        
        List<Member> members = memberDao.selectAll();
        for(Member member : members) {
            System.out.printf("%d : %s : %s%n", member.getId(), member.getEmail(), member.getName());
        }
    }


}
