package spring5_db_study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	@Autowired
	private MemberDao memberDao;

//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
}
