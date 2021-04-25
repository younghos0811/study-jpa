package com.study.datajpa.entity;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})   //team은 만약에 적으면 연관관계타고 다 출력되므로 team은 안됨
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;
	private String username;
	private int age;

	@ManyToOne(fetch = FetchType.LAZY)  //Member와 Team이 다대일 관계이기에 , fetch를 lazy로 바꾸는걸 추 - JPA에서는 lazy로하는게 성능이 좋음
	@JoinColumn(name = "team_id")  //fk명
	private Team team;

	public Member(String username) {
		this.username = username;
	}

	public Member(String userName, int age, Team team) {
		this.username = userName;
		this.age = age;
		if(team != null)
			changeTeam(team);
	}

	//team을 변경할 수 있게 위하여
	public void changeTeam(Team team) {
		this.team = team;
		team.getMebers().add(this);
	}
}


