package com.poscodx.mysite.listener;

import org.springframework.stereotype.Component;

import com.poscodx.mysite.vo.SiteVo;

@Component(value="testsite")
public class TestSite {
	private SiteVo testVo;

	public SiteVo getTestVo() {
		return testVo;
	}

	public void setTestVo(SiteVo testVo) {
		this.testVo = testVo;
	}
}