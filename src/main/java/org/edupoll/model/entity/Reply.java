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
@Table(name = "replys")
public class Reply {
	// 필드 설정 // 어노테이션 설정
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;

	String text;
	String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moimId")
	Moim moim;

	// 기타 setter, getter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Moim getMoim() {
		return moim;
	}

	public void setMoim(Moim moim) {
		this.moim = moim;
	}

}
