package com.poscodx.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poscodx.mysite.dto.JsonResult;
import com.poscodx.mysite.service.GuestbookService;
import com.poscodx.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {

	@Autowired
	GuestbookService guestbookService;
	
	@PostMapping
	@Transactional
	public JsonResult create(@RequestBody GuestbookVo vo) {
	    guestbookService.addContents(vo);
//	    int no = guestbookService.getLastIndex();
//	    vo.setNo(no);
		return JsonResult.success(vo);
	}
	
	@GetMapping("{sno}")
	public JsonResult read(
		@PathVariable("sno") int sno
		) {
		System.out.println(sno);
		List<GuestbookVo> list = guestbookService.getContentsList(sno, 5);
		return JsonResult.success(list);
	}
	
	@DeleteMapping("{no}")
	public JsonResult delete(
			@PathVariable("no") int no,
			@RequestParam(value="password", required=true, defaultValue="") String password
			) {
		
		GuestbookVo vo = guestbookService.findByNo(no);
		guestbookService.deleteContents(no, password);
		return JsonResult.success(vo);
	}
}
