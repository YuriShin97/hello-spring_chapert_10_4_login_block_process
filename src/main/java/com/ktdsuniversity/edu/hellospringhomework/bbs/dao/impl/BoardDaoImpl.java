package com.ktdsuniversity.edu.hellospringhomework.bbs.dao.impl;

import com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.WriteBoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository 어노테이션을 사용하여 Dao(Data Access Object - DB의 데이터에 접근하기 위한 객체)구현 클래스임을 명시
@Repository
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao {

    // 스프링이 제공하는 SqlSessionTemplate 을 세팅하는 메서드. 이 메서드는 스프링이 자동으로 처리
    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    // 전체 게시판 글 수를 반환하는 메서드
    @Override
    public int selectBoardAllCount() {
        return getSqlSession().selectOne("com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao.selectBoardAllCount");
    }

    // 전체 게시판 내용을 반환하는 메서드
    @Override
    public List<BoardVO> selectAllBoard() {
        return getSqlSession().selectList("com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao.selectAllBoard");
    }

    // 새 게시판 글을 삽입하는 메서드. writeBoardVO 객체에 담긴 내용으로 새 글을 삽입
    @Override
    public int insertNewBoard(WriteBoardVO writeBoardVO) {
        return this.getSqlSession().insert("com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao.insertNewBoard", writeBoardVO);
    }

    // 게시판 글의 조회수를 증가시키는 메서드. 조회수를 증가시키는 대상인 게시글 ID를 파라미터로 받음
    @Override
    public int updateViewCount(int id) {
        return this.getSqlSession().update("com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao.updateViewCount", id);
    }

    // 특정 ID를 가진 게시글의 정보를 반환하는 메서드
    @Override
    public BoardVO selectOneBoard(int id) {
        return this.getSqlSession().selectOne("com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao.selectOneBoard", id);
    }
    
    @Override
    public int updateOneBoard(ModifyBoardVO modifyBoardVO) {
    	return this.getSqlSession().update("com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao.updateOneBoard", modifyBoardVO);
    }
    
    @Override
    public int deleteOneBoard(int id) {
    	return this.getSqlSession().delete("com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao.deleteOneBoard", id);
    }
}
