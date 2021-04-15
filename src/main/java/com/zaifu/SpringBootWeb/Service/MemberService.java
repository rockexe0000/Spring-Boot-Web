package com.zaifu.SpringBootWeb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zaifu.SpringBootWeb.Dao.MemberDao;
import com.zaifu.SpringBootWeb.Model.MemberAccount;

@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;
	public void addMember(MemberAccount memberAccount){
		memberDao.addMember(memberAccount);
	}
}