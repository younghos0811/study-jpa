package com.study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
