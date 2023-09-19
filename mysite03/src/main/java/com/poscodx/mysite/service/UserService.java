package com.poscodx.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.UserRepository;
import com.poscodx.mysite.vo.UserVo;

@Service
public class UserService {
//	@Autowired
//	private MailSender mailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	public void erpProcess01(UserVo vo) {
//		erpEmployee1Repository.insert(vo);
//		erpJobRepository.insert(vo);
//		erpSalaryRepository.insert(vo);
	}

	public void join(UserVo vo) {
		userRepository.insert(vo);
//		mailSender.send(vo.getEmail(), "title", "contents");
	}

	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}
	
	public void update(UserVo userVo) {
		userRepository.update(userVo);
	}
}
