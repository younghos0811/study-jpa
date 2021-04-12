package com.study.datajpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.study.datajpa.entity.Member;

@Repository
public class MemberJpaRepository {

	//스프링 컨테이너가 영속성 컨텍스트를 가져와줌 (엔티티 매니저 , 알아서 jpa가 db에 쿼리들을 날림)
	@PersistenceContext
	private EntityManager em;

	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
