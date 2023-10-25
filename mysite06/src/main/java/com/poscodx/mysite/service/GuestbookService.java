package com.poscodx.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poscodx.mysite.repository.GuestbookRepository;
import com.poscodx.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}
	
	public void deleteContents(int no, String password) {
		guestbookRepository.deleteByNo(no, password);
	}
	
	public void addContents(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}

	public List<GuestbookVo> getContentsList(int sno, int k) {
		return guestbookRepository.findTop(sno, k);
	}

	public GuestbookVo findByNo(int no) {
		return guestbookRepository.findByNo(no);
	}

	public int getLastIndex() {
		return guestbookRepository.getLastIndex();
	}
}
