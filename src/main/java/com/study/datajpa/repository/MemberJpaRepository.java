package com.study.datajpa.repository;

import java.util.List;
import java.util.Optional;

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

	public void delete(Member member) {
		em.remove(member);
	}

	public List<Member> findAll() {
		//JPQL 사용 // 전체조회가 없기떄문에
		return em.createQuery("select m from Member m", Member.class).
			getResultList(); //테이블 대상이 아닌 객체 대상으로함 !! 그래서 대문자 ..
	}

	public long count() {
		return em.createQuery("select count(m) from Member m", Long.class)
				.getSingleResult();
	}

	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
