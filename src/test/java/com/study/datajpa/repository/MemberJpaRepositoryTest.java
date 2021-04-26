package com.study.datajpa.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

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

	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		memberJpaRepository.save(member1);
		memberJpaRepository.save(member2);

		Optional<Member> findMember1 = memberJpaRepository.findById(member1.getId());
		Optional<Member> findMember2 = memberJpaRepository.findById(member2.getId());
		assertThat(findMember1.get()).isEqualTo(member1);
		assertThat(findMember2.get()).isEqualTo(member2);

		//리스트 조회 검증
		List<Member> members = memberJpaRepository.findAll();
		assertThat(members.size()).isEqualTo(2);

		//count 조회 검증
		long count = memberJpaRepository.count();
		assertThat(count).isEqualTo(2);

		findMember1.get().setUsername("change name");

		//delete
		// memberJpaRepository.delete(member1);
		// memberJpaRepository.delete(member2);
		//
		// //삭제 검
		// long count1 = memberJpaRepository.count();
		// assertThat(count1).isEqualTo(0);

	}
}
