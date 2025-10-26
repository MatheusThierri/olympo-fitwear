package com.olympofitwear.olympo.olympo_api.domain.repository;

import com.olympofitwear.olympo.olympo_api.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
