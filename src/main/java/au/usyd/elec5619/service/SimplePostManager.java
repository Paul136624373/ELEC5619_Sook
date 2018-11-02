package au.usyd.elec5619.service;

import java.util.ArrayList;
import java.util.List;

import au.usyd.elec5619.domain.Post;

public class SimplePostManager implements PostManager {

	private List<Post> allPosts;
	
	public List<Post> getAllPosts(){
		return allPosts;
	}
	
	public void setPosts(List<Post> posts){
		allPosts = posts;
	}
	
	public List<Post> getPostsByAuthor(String author){
		
		List<Post> posts = new ArrayList<Post>();
		
		for(Post onePost : allPosts) 
		{
			if(onePost.getAuthor().equals(author))
			{
				posts.add(onePost);
			}
		}
		
		return posts;
	}
	
	public Post getPostByID(long postID) {
		
		Post thePost = new Post();
		
		for(Post onePost : allPosts) 
		{
			if(onePost.getPostID() == postID)
			{
				thePost = onePost;
			}
		}
		
		return thePost;
	}
	
	public void deletePostByID(long postID) {
		
		for(Post onePost : allPosts) 
		{
			if(onePost.getPostID() == postID)
			{
				allPosts.remove(onePost);
			}
		}
	}

	@Override
	public void addPost(Post onePost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePost(Post onePost) {
		// TODO Auto-generated method stub
		
	}
	
}
