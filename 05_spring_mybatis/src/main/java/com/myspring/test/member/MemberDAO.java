package com.myspring.test.member;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void memberJoin(Member member) {
		sqlSession.insert("mapper.member.insertMember", member);
	}
	
	public ArrayList<Member> getMemberList(){
		ArrayList<Member> memberList = new ArrayList<Member>();
		List<Member> list = sqlSession.selectList("mapper.member.selectAllMemberList");
		for(Member m : list) {
			memberList.add(m);
		}	
		return memberList;
	}
	
	public Member checkMember(Member member) {

		Member mem  = sqlSession.selectOne("mapper.member.checkMember", member);
		return mem;
	}
	
	public Member getOneMember(String id) {
		Member member = sqlSession.selectOne("mapper.member.getMemberInfo", id);
			
		return member;
	}
	
	public void updateMember(Member member) {
		sqlSession.update("mapper.member.updateMember", member);
	}
	
}
