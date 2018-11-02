package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Comment")
public class Comment implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="CommentID")
	private long commentID;
	
	@Column(name="PostID")
	private long postID;
	
	@Column(name="Writer")
	private String writer;
	
	@Column(name="CreationTime")
	private String creationTime;
	
	@Column(name="Content")
	private String content;
	
	public Comment()
	{
		// Constructor
	}
	
	public Comment(long postID, String writer, String creationTime, String content)
	{
		this.postID = postID;
		this.writer = writer;
		this.creationTime = creationTime;
		this.content = content;
	}
	
	public long getCommentID() {
		return commentID;
	}
	public void setCommentID(long commentID) {
		this.commentID = commentID;
	}
	public long getPostID() {
		return postID;
	}
	public void setPostID(long postID) {
		this.postID = postID;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Comment [commentID=" + commentID + ", postID=" + postID + ", writer=" + writer + ", creationTime="
				+ creationTime + ", content=" + content + "]";
	}
}
