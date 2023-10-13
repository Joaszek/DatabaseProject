package com.example.databaseproject.controller;

import com.example.databaseproject.data.student.Blog;
import com.example.databaseproject.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRespository;

    @GetMapping("/blog")
    public List<Blog> index(){
        return blogRespository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Optional<Blog> show(@PathVariable String id){
        Long blogId = Long.parseLong(id);
        return blogRespository.findById(blogId);
    }

    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRespository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String content = body.get("content");
        return blogRespository.save(new Blog(title, content));
    }

    @PutMapping("/blog/{id}")
    public Optional<Blog> update(@PathVariable String id, @RequestBody Map<String, String> body){
        Long blogId = Long.parseLong(id);
        // getting blog
        Optional<Blog> blog = blogRespository.findById(blogId);
        blog.get().setTitle(body.get("title"));
        blog.get().setContent(body.get("content"));
        return Optional.of(blogRespository.save(blog.get()));
    }

    @DeleteMapping("blog/{id}")
    public boolean delete(@PathVariable String id){
        Long blogId = Long.parseLong(id);
        blogRespository.delete(blogRespository.getReferenceById(blogId));
        return true;
    }
}

