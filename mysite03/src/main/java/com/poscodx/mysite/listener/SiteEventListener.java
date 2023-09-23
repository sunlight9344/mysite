package com.poscodx.mysite.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SiteEventListener {
	
	@Autowired
	private Site site;
	
    @EventListener
    public void alram(SiteEvent event) {
    	site.setSiteVo(event.getSiteVo());
        //System.out.println("------->" + event.getSiteVo());
    }
}