package org.edupoll.model.dto;

import java.text.SimpleDateFormat;

import org.edupoll.model.entity.User;

public class UserResponseData {
	String id;
	String pass;
	String nick;
	String joinDay;
	String joinTime;
	String avatarUrl;
	String description;
	boolean followed;

	public UserResponseData(User user) {
		this.id = user.getId();
		SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");
		this.joinDay = dayFmt.format(user.getJoinDate());
		long diff = System.currentTimeMillis() - user.getJoinDate().getTime();
		this.joinTime = diff / (1000L * 60 * 60 * 24) + "일 전";
		this.nick = user.getNick();
		if (user.getUserDetail() != null) {
			description = user.getUserDetail().getDescription();
			if (user.getUserDetail().getAvatar() != null) {
				avatarUrl = user.getUserDetail().getAvatar().getUrl();
			}
		}
	}
	
	

	public boolean isFollowed() {
		return followed;
	}



	public void setFollowed(boolean followed) {
		this.followed = followed;
	}



	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public String getNick() {
		return nick;
	}

	public String getJoinDay() {
		return joinDay;
	}

	public String getJoinTime() {
		return joinTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setJoinDay(String joinDay) {
		this.joinDay = joinDay;
	}

	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}

}
