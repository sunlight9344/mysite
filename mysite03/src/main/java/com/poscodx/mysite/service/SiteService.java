package com.poscodx.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.SiteRepository;
import com.poscodx.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private SiteRepository siteRepository;
	
	public SiteVo getSite() {
		return siteRepository.find();
	}
	
	public void UpdateSite(SiteVo vo) {
		publisher.publishEvent(vo);
		siteRepository.update(vo);
	}
}
