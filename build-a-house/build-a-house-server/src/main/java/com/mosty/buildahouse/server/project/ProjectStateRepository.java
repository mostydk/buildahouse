package com.mosty.buildahouse.server.project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStateRepository extends JpaRepository<ProjectStateEntity, Integer> {
	Optional<ProjectStateEntity> findByName(String name);
}
