package org.edupoll.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "avatars")
public class Avatar {
	// 필드 설정
	// 어노테이션 설정
	@Id
	String id;
	String description;
	String url;

	@OneToMany(mappedBy = "avatar", fetch = FetchType.LAZY)
	List<UserDetail> details;

	public List<UserDetail> getDetails() {
		return details;
	}

	public void setDetails(List<UserDetail> details) {
		this.details = details;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Avatar [id=" + id + ", description=" + description + ", url=" + url + "]";
	}

	// setter,getter, constructor, toString

}
