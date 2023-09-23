package com.poscodx.mysite.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class SiteEventListener {
	
    @EventListener
    public void alram(SiteEvent event) {
        System.out.println("------->" + event.getSiteVo());
    }
    
}