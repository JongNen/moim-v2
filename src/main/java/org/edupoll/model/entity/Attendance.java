package org.edupoll.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendances")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moimId")
	Moim moim;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Moim getMoim() {
		return moim;
	}

	public void setMoim(Moim moim) {
		this.moim = moim;
	}

	public Attendance() {
		super();
	}

	public Attendance(User user, Moim moim) {
		super();
		this.user = user;
		this.moim = moim;
	}

}
