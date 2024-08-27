// infrastructure.out.jpa.repository.IArticleRepository
package com.example.emazon.infrastructure.out.jpa.repository;

import com.example.emazon.infrastructure.out.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
