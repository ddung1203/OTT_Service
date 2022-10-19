package com.restapi.rcmn.domain.db.repository;

import com.restapi.rcmn.domain.db.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTag(String tag);
}
