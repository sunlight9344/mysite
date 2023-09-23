package com.poscodx.mysite.listener;

import com.poscodx.mysite.vo.SiteVo;

public class SiteEvent {

	private SiteVo siteVo;

    public SiteEvent(SiteVo siteVo) {
        this.siteVo = siteVo;
    }

    public SiteVo getSiteVo() {
        return siteVo;
    }
}