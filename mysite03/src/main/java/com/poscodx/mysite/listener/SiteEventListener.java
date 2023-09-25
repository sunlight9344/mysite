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
	private SiteService siteService;
	
	private SiteVo testSiteVo;
	
	@PostConstruct
    public void initialize() {
        testSiteVo = siteService.getSite();
    }
    
    @EventListener
    public void alram(SiteVo vo) {
    	testSiteVo = vo;
    }

	public SiteVo getTestSiteVo() {
		return testSiteVo;
	}

	public void setTestSiteVo(SiteVo testSiteVo) {
		this.testSiteVo = testSiteVo;
	}
}