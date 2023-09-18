package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	public UserVo findByEmailAndPassword(String email, String password) {
		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =  null;
		try {
			conn = getConnection();
			
			String sql = "select no, name"
					+ " from user"
					+ " where email=?"
					+ " and password=password(?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userVo;
	}
	
	public void insert(UserVo vo) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
				
				String sql = "insert into user values(null, ?, ?, password(?), ?, current_date())";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getEmail());
				pstmt.setString(3, vo.getPassword());
				pstmt.setString(4, vo.getGender());
				
				pstmt.executeQuery();
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	public UserVo findByNo(Long x) {
		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =  null;
		try {
			conn = getConnection();
			
			String sql = "select no, name, email, gender "
					+ " from user "
					+ " where no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, x);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);
				
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setGender(gender);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userVo;
	}
	
	
	public void update(UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			if("".equals(userVo.getPassword())) {
				String sql = "update user"
						+ " set name = ?,"
						+ " gender = ?"
						+ " where no = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userVo.getName());
				pstmt.setString(2, userVo.getGender());
				pstmt.setLong(3, userVo.getNo());
			}else {
				String sql = "update user"
						+ " set name = ?,"
						+ " gender = ?,"
						+ " password = password(?)"
						+ " where no = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userVo.getName());
				pstmt.setString(2, userVo.getGender());
				pstmt.setString(3, userVo.getPassword());
				pstmt.setLong(4,userVo.getNo());
			}
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.180:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}



}
