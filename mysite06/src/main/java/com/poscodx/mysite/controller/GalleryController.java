package com.poscodx.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.mysite.security.Auth;
import com.poscodx.mysite.service.FileUploadService;
import com.poscodx.mysite.service.GalleryService;
import com.poscodx.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getAllContents();
		model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@Auth(Role="ADMIN")
	@RequestMapping("/upload")
	public String upload(GalleryVo galleryVo, MultipartFile file) {
		
		String imgUrl = fileUploadService.restore(file);
		if(imgUrl != null) {
			galleryVo.setImageUrl(imgUrl);
			galleryService.addImage(galleryVo);
		}
		
		return "redirect:/gallery";
	}
	
	@Auth(Role="ADMIN")
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") int no) {
		
		galleryService.deleteByNo(no);
		
		return "redirect:/gallery";
	}
}
