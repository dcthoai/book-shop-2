package com.springmvc.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.dto.request.SlideRequest;
import com.springmvc.model.Media;
import com.springmvc.model.ResponseJSON;
import com.springmvc.model.Slide;
import com.springmvc.service.impl.MediaFileService;
import com.springmvc.service.impl.MediaService;
import com.springmvc.service.impl.SlideService;

@Controller
@RequestMapping(value = "/admin/slide")
public class AdminSlideController {
	
	@Autowired
	private SlideService slideService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private MediaFileService mediaFileService;
	
	@GetMapping
	public ModelAndView slide(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/slide");
		mav.addObject("slides", slideService.getAllSlides());
		
		return mav;
	}

	@GetMapping(value = "/add")
	public ModelAndView addSlideView(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/add-slide");
		mav.addObject("slides", slideService.getAllSlides());
		
		return mav;
	}
	
	@GetMapping(value = "/update")
	public ModelAndView updateSlideView(HttpServletRequest request, @RequestParam("id") Integer slideId) {
		if (slideId == null)
			return null;
		
		ModelAndView mav = new ModelAndView("admin/edit-slide");
		mav.addObject("slide", slideService.getById(slideId));
		mav.addObject("mediaService", mediaService);
		
		return mav;
	}
	
    @PostMapping(value = "/add")
    public ResponseEntity<?> addSlide(HttpServletRequest request,
    								  @ModelAttribute SlideRequest slideRequest) throws IOException {
    	HttpSession session = request.getSession();
    	Slide slide = new Slide();
		slide.setCaption(slideRequest.getCaption());
		slide.setContent(slideRequest.getContent());
		slide.setLink(slideRequest.getLink());
		slide.setCreatedBy((String) session.getAttribute("username"));
		
		if (mediaFileService.isUploadFile(slideRequest.getThumbnail())) {
			String path = mediaFileService.saveFile(slideRequest.getThumbnail());
			Media media = new Media();
			media.setPath(path);
			int thumbnailId = mediaService.insertMedia(media);
		
			if (thumbnailId > 0) {
				slide.setThumbnailId(thumbnailId);
				
				int slideId = slideService.insertSlide(slide);
				
				if (slideId > 0)
					return ResponseJSON.ok("Save slide success");
				
				return ResponseJSON.serverError("cannot save slide, server error");
			}
			
			return ResponseJSON.serverError("Cannot save slide thumbnail");
		}
		
		return ResponseJSON.badRequest("Missing thumbnail from client");
	}
    
    @PostMapping(value = "/update")
    public ResponseEntity<?> updateSlide(HttpServletRequest request,
    									@ModelAttribute SlideRequest slideRequest) throws IOException {
    	Slide slide = slideService.getById(slideRequest.getId());
    	HttpSession session = request.getSession();
		
		if (slide == null) 
			return ResponseJSON.badRequest("Data invalid");
				
		if (slideRequest.getCaption() != null)
			slide.setCaption(slideRequest.getCaption());
		
		if (slideRequest.getContent() != null)
			slide.setContent(slideRequest.getContent());
		
		if (slideRequest.getLink() != null)
			slide.setLink(slideRequest.getLink());
		
		if (mediaFileService.isUploadFile(slideRequest.getThumbnail())) {
			String path = mediaFileService.saveFile(slideRequest.getThumbnail());
			Media media = new Media();
			media.setPath(path);
			
			int thumbnailId = mediaService.insertMedia(media);
		
			if (thumbnailId > 0) {
				mediaFileService.deleteFile(mediaService.getMediaById(slide.getThumbnailId()).getPath());
				mediaService.deleteMedia(slide.getThumbnailId());
				slide.setThumbnailId(thumbnailId);
			} else
				return ResponseJSON.serverError("Cannot save slide thumbnail");
		}
		
		slide.setModifiedBy((String) session.getAttribute("username"));
		boolean isSuccess = slideService.updateSlide(slide);
		
		if (isSuccess)
			return ResponseJSON.ok("Update slide success");
		
		return ResponseJSON.serverError("Cannot update slide, server error");
	}
    
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteSlide(HttpServletRequest request, @RequestParam("id") Integer slideId) {
    	if (slideId == null)
			return ResponseJSON.badRequest("Missing data from client");
		
		boolean success = slideService.deleteSlide(slideId);
		
		if (success)
			return ResponseJSON.ok("Delete slide success");
		return ResponseJSON.serverError("Cannot delete this item");
    }
}
