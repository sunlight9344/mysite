package com.poscodx.mysite.listener;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

@Component(value="listener")
public class SiteEventListener {
	
	@Autowired
	private TestSite testSite;
	
	@Autowired
	private SiteService siteService;
	
	@PostConstruct
    public void initialize() {
		testSite.setTestVo(siteService.getSite());
    }
    
    @EventListener
    public void alram(SiteVo siteVo) {
    	testSite.setTestVo(siteVo);
    }
}