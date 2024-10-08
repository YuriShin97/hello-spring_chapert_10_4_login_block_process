package com.ktdsuniversity.edu.hellospringhomework.bbs.dao.impl;

// 필요한 import 문들
import com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.WriteBoardVO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// BoardDao의 구현체에 대한 테스트 클래스
@MybatisTest  // 스프링 부트 아키텍처 슬라이싱을 사용하여 MYBATIS에 대한 테스트를 지원
@AutoConfigureTestDatabase(replace = Replace.NONE)  // 데이터베이스 자동 설정을 하지 않음
@Import(BoardDaoImpl.class)  // 테스트에 필요한 클래스를 Import 함
public class BoardDaoImplTest {

    // BoardDao 객체를 선언한다. 즉, @Autowired 어노테이션이 이 테스트 클래스에서 boardDao 객체를 사용할 수 있게 한다.
    @Autowired
    private BoardDao boardDao;

    // 게시글 전체 수를 조회하는 테스트 메소드
    @Test
    public void testSelectBoardAllCount() {
        // boardDao에서 selectBoardAllCount 메소드를 호출하고, 그 결과값을 count에 담는다.
        int count = this.boardDao.selectBoardAllCount();
        // count 값을 콘솔에 출력한다.
        System.out.println("boardCount: " + count);
    }

    // 모든 게시글 정보를 조회하는 테스트 메소드
    @Test
    public void testSelectAllBoard() {
        // boardDao에서 selectAllBoard 메소드를 호출하고, 그 결과값을 boardList에 담는다.
        List<BoardVO> boardList = this.boardDao.selectAllBoard();
        // boardList 값을 콘솔에 출력한다.
        System.out.println("boardList: " + boardList);
    }

    // 새 게시글을 작성하는 테스트 메소드
    @Test
    public void testInesrtNewBoard(){
        // WriteBoardVO 객체인 writeBoardVO을 생성하고, 필드 값을 설정한다.
        WriteBoardVO writeBoardVO = new WriteBoardVO();
        writeBoardVO.setContent("Test Content");
        writeBoardVO.setEmail("Test Email");
        writeBoardVO.setSubject("Test Subject");

        // boardDao에서 insertNewBoard 메소드에 writeBoardVO을 파라미터로 넘겨 호출하고, 그 결과값을 count에 담는다.
        int count = this.boardDao.insertNewBoard(writeBoardVO);
        // count 값 (삽입된 행 수)이 1인지 확인한다.
        assertTrue(count == 1); // assert 문을 사용하여 테스트 케이스를 검증. 즉, 게시글 1개가 삽입되었는지 확인
        System.out.println("boardCount: " + count); // 결과값 출력
    }

    // 게시글의 조회수를 증가시키는 기능에 대한 테스트 메소드
    @Test
    public void testUpdateViewCount(){
        int boardId = 4; // 테스트할 게시글 ID
        
        // 조회수를 증가시키기 전 해당 게시글 정보를 가져옵니다.
        BoardVO beforeIncreaseViewCount = this.boardDao.selectOneBoard(boardId);
        int viewBeforeIncrease = beforeIncreaseViewCount.getViewCnt(); // 증가 전 조회수를 저장한다.
        System.out.println("조회수 증가 전: " + viewBeforeIncrease); // 증가 전 조회수 출력

        // 게시글의 조회수를 증가시킵니다.
        boardDao.updateViewCount(boardId);

        // 조회수 증가 후 해당 게시글 정보를 다시 가져옵니다.
        BoardVO afterIncreaseViewCount = this.boardDao.selectOneBoard(boardId);
        int viewAfterIncrease = afterIncreaseViewCount.getViewCnt(); // 증가 후 조회수를 저장한다.

        // 조회수가 잘 증가되었는지 검증한다. 있다면, 참을 반환한다.
        assertTrue(viewAfterIncrease > viewBeforeIncrease);
        System.out.println("조회수 증가 후: " + viewAfterIncrease); // 증가 후 조회수 출력
    }

    // 특정 게시글을 상세 조회하는 기능에 대한 테스트 메소드
    @Test
    public void testSelectOneBoard(){
        int boardId = 4; // 테스트할 게시글 ID

        // 주어진 ID를 가진 게시글을 조회하여 가져옵니다.
        BoardVO board = this.boardDao.selectOneBoard(boardId);

        // 가져온 게시글이 null이 아니며, 가져온 게시글의 ID가 입력한 ID와 일치하는지 확인한다.
        assertNotNull(board);
        assertEquals(boardId, board.getId());

        // 마지막으로, 가져온 게시글의 정보를 출력한다.
        System.out.println("boardId: " + board.getId());
        System.out.println("subject: " + board.getSubject());
        System.out.println("email: " + board.getEmail());
        System.out.println("viewCnt: " + board.getViewCnt());
        System.out.println("subject: " + board.getSubject());
    }
}