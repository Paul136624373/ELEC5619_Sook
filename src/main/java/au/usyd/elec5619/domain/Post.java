package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Post")
public class Post implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="PostID")
	private long postID;
	
	@Column(name="Author")
	private String author;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Type")
	private String type;
	
	@Column(name="CreationTime")
	private String creationTime;
	
	@Column(name="Content")
	private String content;
	
	@Column(name="ImgPath")
	private String imgPath;
	
	public Post()
	{
		// Constructor
	}
	
	public Post(String author, String title, String type, String creationTime, String content, String imgPath)
	{
		this.author = author;
		this.title = title;
		this.type = type;
		this.creationTime = creationTime;
		this.content = content;
		this.imgPath = imgPath;
	}
	
	public long getPostID() {
		return postID;
	}
	public void setPostID(long postID) {
		this.postID = postID;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "Post [postID=" + postID + ", author=" + author + ", title=" + title + ", type=" + type
				+ ", creationTime=" + creationTime + ", content=" + content + ", imgPath=" + imgPath + "]";
	}
}
