package com.poscodx.mysite.listener;

import org.springframework.stereotype.Component;

import com.poscodx.mysite.vo.SiteVo;

@Component(value="site")
public class Site {

	private SiteVo siteVo;

	public SiteVo getSiteVo() {
		return siteVo;
	}

	public void setSiteVo(SiteVo siteVo) {
		this.siteVo = siteVo;
	}

}
