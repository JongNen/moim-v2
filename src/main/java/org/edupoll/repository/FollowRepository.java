package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	
	boolean existsByOwnerIdIsAndTargetIdIs(String ownerId, String targetId);
	
	void deleteByOwnerIdIsAndTargetIdIs(String ownerId, String targetId);
	
	List<Follow> findByOwnerId(String ownerId);

}
