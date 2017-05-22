package com.collaboration.project.dao;

import java.util.List;

import com.collaboration.project.model.Blog;

public interface BlogDao {
  boolean saveBlog(Blog blog);
  boolean updateBlog(Blog blog);
  Blog getBlog(Integer id);
  List<Blog> getAll();
}
