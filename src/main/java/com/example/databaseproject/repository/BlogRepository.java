package com.example.databaseproject.repository;

import com.example.databaseproject.data.student.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);
}
