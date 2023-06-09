package org.edupoll.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	String id;

	String pass;
	String nick;
	Date joinDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userDetailIdx") // User Entity 의 필드
	UserDetail userDetail; // 이 컬럼을 찾는 객체는 UserDetail (id를 기준으로 찾음)

	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
	List<Moim> moims;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="attendances",
		joinColumns = @JoinColumn(name="userId"), 
		inverseJoinColumns = @JoinColumn(name="moimId"))
	List<Moim> attendMoims;
	
	@OneToMany(mappedBy = "owner")
	List<Follow> followTo;
	
	@OneToMany(mappedBy = "target")
	List<Follow> followFrom;
	
	
	
	
	
	public List<Follow> getFollowTo() {
		return followTo;
	}

	public void setFollowTo(List<Follow> followTo) {
		this.followTo = followTo;
	}

	public List<Follow> getFollowFrom() {
		return followFrom;
	}

	public void setFollowFrom(List<Follow> followFrom) {
		this.followFrom = followFrom;
	}

	public List<Moim> getAttendMoims() {
		return attendMoims;
	}

	public void setAttendMoims(List<Moim> attendMoims) {
		this.attendMoims = attendMoims;
	}

	public List<Moim> getMoims() {
		return moims;
	}

	public void setMoims(List<Moim> moims) {
		this.moims = moims;
	}

	// setter / getter 만 추가
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	// 객체 insert 전 할 작업
	@PrePersist
	public void doPrePersist() {
		System.out.println("doPrePersist..");
		joinDate = new Date();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", nick=" + nick + ", joinDate=" + joinDate + ", userDetail="
				+ userDetail + "]";
	}
	// toString
}

/*
 * 
 * id varchar2(90) primary key, pass varchar2(90) not null, nick varchar2(90)
 * not null, join_date date default sysdate, user_detail_id varchar2(900)
 */
