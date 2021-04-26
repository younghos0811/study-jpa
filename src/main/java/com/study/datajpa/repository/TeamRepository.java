package com.study.datajpa.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.study.datajpa.entity.Team;
import com.study.datajpa.entity.Team;

@Repository
public class TeamRepository {

	@PersistenceContext //영속성 컨텍스트 주입해주는 어노테이션
	private EntityManager em;

	public Team save(Team team) {
		em.persist(team);
		return team;
	}

	public void delete(Team team) {
		em.remove(team);
	}

	public List<Team> findAll() {
		return em.createQuery("select t from Team t", Team.class)
			.getResultList();
	}

	public Optional<Team> findById(Long id) {
		Team team = em.find(Team.class, id);
		return Optional.ofNullable(team);
	}

	public long count() {
		return em.createQuery("select count(t) from Team t", Long.class)
			.getSingleResult();
	}

}
