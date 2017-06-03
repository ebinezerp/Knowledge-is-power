package com.collaboration.project.dao;

import java.util.List;

import com.collaboration.project.model.BlogComments;

public interface BlogCommentsDao {
	
	boolean postComment(BlogComments blogComment);
	List<BlogComments> allComments(Integer blogId);

}
