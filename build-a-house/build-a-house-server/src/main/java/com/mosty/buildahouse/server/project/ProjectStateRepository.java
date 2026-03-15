package com.mosty.buildahouse.server.project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStateRepository extends JpaRepository<ProjectStateEntity, Integer> {
	Optional<ProjectStateEntity> findByEnumName(String enumName);
}
