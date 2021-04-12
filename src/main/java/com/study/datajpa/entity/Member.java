package com.study.datajpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

	@Id
	@GeneratedValue
	private Long id;
	private String username;

	protected Member() {
	}

	public Member(String username) {
		this.username = username;
	}
}


