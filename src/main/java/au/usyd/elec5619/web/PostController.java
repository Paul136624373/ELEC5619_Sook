package au.usyd.elec5619.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import au.usyd.elec5619.domain.Comment;
import au.usyd.elec5619.domain.Post;
import au.usyd.elec5619.domain.User;
import au.usyd.elec5619.service.CommentManager;
import au.usyd.elec5619.service.PostManager;
import au.usyd.elec5619.service.UserManager;

@Controller
@RequestMapping(value="/forum/**")
public class PostController {
	
	@Resource(name="postManager")
	private PostManager postManager;
	
	@Resource(name="commentManager")
	private CommentManager commentManager;
	
	@Resource(name="userManager")
	private UserManager userManager;
	
	@RequestMapping(value="/createPost")
	public String createPost(Model uiModel, HttpServletRequest httpServletRequest){
		
		long userID;
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID != 0)
		{
			for(User oneUser : this.userManager.getAllUser())
			{
				if(oneUser.getUserID() == userID)
				{
					uiModel.addAttribute("userName", oneUser.getName());
				}
			}
		}
		else
		{
			return "redirect:/register";
		}
		
		return "createPost";
	}
	
	@RequestMapping(value="/createPost", method=RequestMethod.POST)
	public String createPost(@RequestParam(value = "imgPath",required = false) MultipartFile file, HttpServletRequest httpServletRequest){
		
		String uploadRootPath = httpServletRequest.getSession().getServletContext().getRealPath("post_img/");
//		String uploadRootPath = "/Users/wangzhe/Library/ApacheTomcat/webapps/sook_user_img/";
		File uploadRootDir = new File(uploadRootPath);
		if(!uploadRootDir.exists()) {
			uploadRootDir.mkdirs(); 
		}
		String fileName = file.getOriginalFilename(); 
		File targetFile = new File(uploadRootPath+ fileName);
		if(fileName!=null&&fileName.length()>0){ 
			try { 
				file.transferTo(targetFile); 
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		long userID;
		String userName = "";
		HttpSession session = httpServletRequest.getSession(false);
		userID = (Long) session.getAttribute("USERID");
		for(User oneUser : this.userManager.getAllUser())
		{
			if(oneUser.getUserID() == userID)
			{
				userName = oneUser.getName();
			}
		}
		
		Post onePost = new Post();
		String creationTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		
		onePost.setAuthor(userName);
		onePost.setTitle(httpServletRequest.getParameter("title"));
		onePost.setType(httpServletRequest.getParameter("type"));
		onePost.setCreationTime(creationTime);
		onePost.setContent(httpServletRequest.getParameter("postContent"));
		onePost.setImgPath(fileName);		// imgPath now store the name of the picture
		this.postManager.addPost(onePost);
		return "redirect:/forum";
	}
	
	@RequestMapping(value="/seekBook")
	public String seekBook(Model uiModel, HttpServletRequest httpServletRequest){
		
		List<Post> seekBookPosts = new ArrayList<Post>();
		
		for(Post onePost : this.postManager.getAllPosts())
		{
			if(onePost.getType().equals("Seek"))
			{
				seekBookPosts.add(onePost);
			}
		}
		
		uiModel.addAttribute("seekBookPosts", seekBookPosts);
		
		long userID;
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID != 0)
		{
			for(User oneUser : this.userManager.getAllUser())
			{
				if(oneUser.getUserID() == userID)
				{
					uiModel.addAttribute("userName", oneUser.getName());
				}
			}
		}
		
		return "seekBook";
	}
	
	@RequestMapping(value="/shareBook")
	public String shareBook(Model uiModel, HttpServletRequest httpServletRequest){
		
		List<Post> shareBookPosts = new ArrayList<Post>();
		
		for(Post onePost : this.postManager.getAllPosts())
		{
			if(onePost.getType().equals("Share"))
			{
				shareBookPosts.add(onePost);
			}
		}
		
		uiModel.addAttribute("shareBookPosts", shareBookPosts);
		
		long userID;
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID != 0)
		{
			for(User oneUser : this.userManager.getAllUser())
			{
				if(oneUser.getUserID() == userID)
				{
					uiModel.addAttribute("userName", oneUser.getName());
				}
			}
		}
		
		return "shareBook";
	}
	
	@RequestMapping(value="/myPost")
	public String myPost(Model uiModel, HttpServletRequest httpServletRequest){
		
		List<Post> myPosts = new ArrayList<Post>();
		
		long userID;
		String userName = "";
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID != 0)
		{
			for(User oneUser : this.userManager.getAllUser())
			{
				if(oneUser.getUserID() == userID)
				{
					uiModel.addAttribute("userName", oneUser.getName());
					userName = oneUser.getName();
				}
			}
		}
		else
		{
			return "redirect:/register";
		}
		
		for(Post onePost : this.postManager.getAllPosts())
		{
			if(onePost.getAuthor().equals(userName))
			{
				myPosts.add(onePost);
			}
		}
		
		uiModel.addAttribute("myPosts", myPosts);
		
		return "myPost";
	}
	
	@RequestMapping(value="/viewPost/{postID}", method=RequestMethod.GET)
	public String viewPost(@PathVariable("postID") long postID,Model uiModel, HttpServletRequest httpServletRequest){
		
		Post thePost = this.postManager.getPostByID(postID);
		List<Comment> allComments = this.commentManager.getPostComment(postID);
		
		uiModel.addAttribute("thePost", thePost);
		uiModel.addAttribute("allComments", allComments);
		
		long userID;
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID != 0)
		{
			for(User oneUser : this.userManager.getAllUser())
			{
				if(oneUser.getUserID() == userID)
				{
					uiModel.addAttribute("userName", oneUser.getName());
				}
			}
		}
		
		return "viewPost";
	}
	
	@RequestMapping(value="/editPost/{postID}", method=RequestMethod.GET)
	public String editPost(@PathVariable("postID") long postID,Model uiModel, HttpServletRequest httpServletRequest){
		
		Post thePost = this.postManager.getPostByID(postID);
		
		uiModel.addAttribute("thePost", thePost);
		
		long userID;
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID != 0)
		{
			for(User oneUser : this.userManager.getAllUser())
			{
				if(oneUser.getUserID() == userID)
				{
					uiModel.addAttribute("userName", oneUser.getName());
				}
			}
		}
		else
		{
			return "redirect:/register";
		}
		
		return "editPost";
	}
	
	@RequestMapping(value="/editPost/**", method=RequestMethod.POST)
	public String editPost(@RequestParam(value = "imgPath",required = false) MultipartFile file, HttpServletRequest httpServletRequest){

		Post thePost = new Post();
		
		thePost.setAuthor(httpServletRequest.getParameter("author"));
		thePost.setCreationTime(httpServletRequest.getParameter("creationTime"));
		thePost.setPostID(Long.parseLong(httpServletRequest.getParameter("postID")));
		thePost.setTitle(httpServletRequest.getParameter("title"));
		thePost.setContent(httpServletRequest.getParameter("content"));
		thePost.setType(httpServletRequest.getParameter("type"));
		
		
		if(file.getOriginalFilename().equals(""))
		{
			thePost.setImgPath(httpServletRequest.getParameter("oldPath"));
		}
		else
		{
			String uploadRootPath = httpServletRequest.getSession().getServletContext().getRealPath("post_img/");
	//			String uploadRootPath = "/Users/wangzhe/Library/ApacheTomcat/webapps/sook_user_img/";
			File uploadRootDir = new File(uploadRootPath);
			if(!uploadRootDir.exists()) {
				uploadRootDir.mkdirs(); 
			}
			String fileName = file.getOriginalFilename(); 
			File targetFile = new File(uploadRootPath+ fileName);
			if(fileName!=null&&fileName.length()>0){ 
				try { 
					file.transferTo(targetFile); 
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			thePost.setImgPath(fileName);
		}
			
		this.postManager.updatePost(thePost);
		
		return "redirect:/forum/myPost";
	}
	
	@RequestMapping(value="/deletePost/{postID}", method=RequestMethod.GET)
	public String deletePost(@PathVariable("postID") long postID,Model uiModel, HttpServletRequest httpServletRequest){

		long userID;
		try
		{
			HttpSession session = httpServletRequest.getSession(false);
			userID = (Long) session.getAttribute("USERID");
			final Log logger = LogFactory.getLog(getClass());
			logger.info(userID);
		}
		catch(Exception e){
			userID = 0;
		}
		
		if(userID == 0)
		{
			return "redirect:/register";
		}

		this.postManager.deletePostByID(postID);
		
		this.commentManager.deleteComments(postID);
		
		return "redirect:/forum/myPost";
	}
	
	@RequestMapping(value="/createComment", method=RequestMethod.POST)
	public String createComment(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

		Comment oneComment = new Comment();
		String creationTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		
		long userID;
		String userName = "";
		HttpSession session = httpServletRequest.getSession(false);
		userID = (Long) session.getAttribute("USERID");
		for(User oneUser : this.userManager.getAllUser())
		{
			if(oneUser.getUserID() == userID)
			{
				userName = oneUser.getName();
			}
		}
		
		oneComment.setPostID(Long.parseLong(httpServletRequest.getParameter("postID")));
		oneComment.setWriter(userName);
		oneComment.setCreationTime(creationTime);
		oneComment.setContent(httpServletRequest.getParameter("replyContent"));
		this.commentManager.addComment(oneComment);
		return "redirect:/forum/viewPost/" + httpServletRequest.getParameter("postID");
	}

}
