package com.version.tracking.repository;

import com.version.tracking.entity.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionRepository extends JpaRepository<VersionEntity,Long> {
    Optional<Integer> findMaxVersionNumberByDocumentId(Long documentId);
    Optional<Integer> findLatestVersionNumberByDocumentId(Long documentId);

}
