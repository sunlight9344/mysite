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
	
	private SiteVo siteVo;
	
	@PostConstruct
    public void initialize() {
		siteVo = siteService.getSite();
    }
    
    @EventListener
    public void alram(SiteVo siteVo) {
    	this.siteVo = siteVo;
    }

	public SiteVo getSiteVo() {
		return siteVo;
	}

	public void setSiteVo(SiteVo siteVo) {
		this.siteVo = siteVo;
	}
}