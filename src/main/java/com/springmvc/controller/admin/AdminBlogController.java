package com.springmvc.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.springmvc.dto.BlogDTO;
import com.springmvc.dto.request.BlogRequest;
import com.springmvc.model.Blog;
import com.springmvc.model.Media;
import com.springmvc.model.ResponseJSON;
import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.impl.BlogService;
import com.springmvc.service.impl.CustomUserDetailsService;
import com.springmvc.service.impl.MediaFileService;
import com.springmvc.service.impl.MediaService;

@Controller
@RequestMapping(value = "/admin/blog")
public class AdminBlogController {
	
	@Autowired 
	private CustomUserDetailsService userDetailsService;
	
	@Autowired 
	private BlogService blogService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private MediaFileService mediaFileService;
	
	@GetMapping
	public ModelAndView blog(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/blog");
		mav.addObject("userService", userDetailsService); 
		mav.addObject("blogs", blogService.getAllBlogs());
		  
		return mav;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView addBlog(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/add-blog");
		
		return mav;
	}
	
	@GetMapping(value = "/edit")
	public ModelAndView updateBlogView(HttpServletRequest request, @RequestParam("id") Integer blogId) {
		if (blogId == null)
			return null;
		
		ModelAndView mav = new ModelAndView("admin/edit-blog");
		mav.addObject("blog", blogService.getById(blogId));
		mav.addObject("mediaService", mediaService);
		
		return mav;
	}
	
    @PostMapping(value = "/add")
    public ResponseEntity<?> addBlog(HttpServletRequest request,
    								  @ModelAttribute BlogRequest blogRequest) throws IOException {
    	HttpSession session = request.getSession();

    	Blog blog = new Blog();
    	String username = (String) session.getAttribute("username");
    	UserDetailsCustom user = userDetailsService.findUserByUsername(username);
    	
    	blog.setAuthorId(user.getId());
    	blog.setTitle(blogRequest.getTitle());
    	blog.setContent(blogRequest.getContent());
    	blog.setCreatedBy(username);
		
		if (mediaFileService.isUploadFile(blogRequest.getThumbnail())) {
			String path = mediaFileService.saveFile(blogRequest.getThumbnail());
			Media media = new Media();
			media.setPath(path);
			
			int thumbnailId = mediaService.insertMedia(media);
		
			if (thumbnailId > 0) {
				blog.setThumbnailId(thumbnailId);
				
				int blogId = blogService.insertBlog(blog);
				
				if (blogId > 0)
					return ResponseJSON.ok("Save blog success");
				
				return ResponseJSON.serverError("cannot save blog, server error");
			}
			
			return ResponseJSON.serverError("Cannot save blog thumbnail");
		}
		
		return ResponseJSON.badRequest("Missing thumbnail from client");
	}
    
    @PostMapping(value = "/update")
    public ResponseEntity<?> updateBlog(HttpServletRequest request,
    									@ModelAttribute BlogRequest blogRequest) throws IOException {
    	Blog blog = blogService.getById(blogRequest.getId());
    	HttpSession session = request.getSession();
		
		if (blog == null) 
			return ResponseJSON.badRequest("Data invalid");
				
		if (blogRequest.getTitle() != null)
			blog.setTitle(blogRequest.getTitle());
		
		if (blogRequest.getContent() != null)
			blog.setContent(blogRequest.getContent());
		
		if (mediaFileService.isUploadFile(blogRequest.getThumbnail())) {
			String path = mediaFileService.saveFile(blogRequest.getThumbnail());
			Media media = new Media();
			media.setPath(path);
			
			int thumbnailId = mediaService.insertMedia(media);
		
			if (thumbnailId > 0) {
				mediaFileService.deleteFile(mediaService.getMediaById(blog.getThumbnailId()).getPath());
				mediaService.deleteMedia(blog.getThumbnailId());
				blog.setThumbnailId(thumbnailId);
			} else
				return ResponseJSON.serverError("Cannot save blog thumbnail");
		}
		
		blog.setModifiedBy((String) session.getAttribute("username"));
		
		boolean isSuccess = blogService.updateBlog(blog);
		
		if (isSuccess)
			return ResponseJSON.ok("Update blog success");
		
		return ResponseJSON.serverError("Cannot update blog, server error");
	}
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteBlog(HttpServletRequest request, @RequestParam("id") Integer blogId) {
    	if (blogId == null)
			return ResponseJSON.badRequest("Missing data from client");
		
		boolean success = blogService.deleteBlog(blogId);
		
		if (success)
			return ResponseJSON.ok("Delete blog success");
		return ResponseJSON.serverError("Cannot delete this item");
    }
    
    @GetMapping(value = "/search")
	public ResponseEntity<?> searchBlog(HttpServletRequest request, @RequestParam("name") String keyword){
		
		List<Blog> listBlogs = blogService.searchByName(keyword);
		List<BlogDTO> listBlogDTOs = new ArrayList<BlogDTO>();
		
		for (Blog blog : listBlogs) {
			BlogDTO blogDto = new BlogDTO();
			
			blogDto.setId(blog.getId());
			blogDto.setTitle(blog.getTitle());
			blogDto.setCreatedDate(blog.getCreatedDate());
			blogDto.setModifiedDate(blog.getModifiedDate());
			blogDto.setAuthor(userDetailsService.getUserById(blog.getAuthorId()).getFullname());
			
			listBlogDTOs.add(blogDto);
		}

		Gson gson = new Gson();
		String json = gson.toJson(listBlogDTOs);
		
		// Use UTF-8 to transmit data
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json);
	}
}
