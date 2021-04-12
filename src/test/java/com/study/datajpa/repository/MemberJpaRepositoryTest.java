package com.study.datajpa.repository;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.study.datajpa.entity.Member;

@SpringBootTest //스프링 컨테이너를 받기 위해 (junit4이하는 runwith(spring....)을 넣어줘야 컨테이너를 얻음)
@Transactional //JPA는 트랜잭셔널 상태에서 나기떄문에 없으면 에러남, org springframework내에서 실행
@Rollback(false) //default는 true
class MemberJpaRepositoryTest {

	@Autowired
	MemberJpaRepository memberJpaRepository;

	//junit은 jupiter를 사용하므로 이거 확실히 필요
	@Test
	public void testMember() {
		Member member = new Member("memberA");
		Member savedMember = memberJpaRepository.save(member); //cmd + opt + v

		Member findMember = memberJpaRepository.find(savedMember.getId());

		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member); //JPA특성상 같은 트랜잭션안에서는 영속성컨텍스트 안에서는 동일성을 보장

	}
}
