package com.poscodx.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void UpdateHit(int no) {
		sqlSession.update("board.updateHit", no);
	}
	
	public int findAllCount(String tempKwd) {
		String kwd = "%" + tempKwd + "%";
		return sqlSession.selectOne("board.count", kwd);
	}

	public void ModifyByNo(BoardVo boardVo) {
		sqlSession.update("board.modify", boardVo);
	}
	
	public List<BoardVo> findAll(int curPage, int listPerPage, String kwd) {
		Map<String, Object> map = new HashMap<>();
		int tempCurPage = (curPage-1)*listPerPage;
		map.put("curPage", tempCurPage);
		map.put("listPerPage", listPerPage);
		map.put("kwd", "%" + kwd + "%");
		return sqlSession.selectList("board.findAll", map);
	}
	
	public BoardVo getBoardInfoByNo(int no) {
		return sqlSession.selectOne("board.getBoardInfoByNo", no);
	}
	
	public void write(BoardVo boardVo) {
		sqlSession.insert("board.insert", boardVo);
	}

	public void refreshNo(int g_no, int o_no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =  null;
		try {
			conn = getConnection();
			
			String sql = "update board"
					+ " set o_no = o_no + 1"
					+ " where g_no = ? and o_no >= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, g_no);
			pstmt.setInt(2, o_no);
			pstmt.executeUpdate();
			
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
	}

	public void DeleteByNo(int x) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =  null;
		try {
			conn = getConnection();
			
			String sql = "delete from board where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, x);
			pstmt.executeUpdate();
			
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
