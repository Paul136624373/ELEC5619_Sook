package au.usyd.elec5619.service;

import java.io.Serializable;
import java.util.List;

import au.usyd.elec5619.domain.Post;

public interface PostManager extends Serializable {
	
	public List<Post> getAllPosts();
	
	public void addPost(Post onePost);
	
	public Post getPostByID(long postID);
	
	public void updatePost(Post onePost);
	
	public void deletePostByID(long postID);

	public void setPosts(List<Post> allPosts);
}
