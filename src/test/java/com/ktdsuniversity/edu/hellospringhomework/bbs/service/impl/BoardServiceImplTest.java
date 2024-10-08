package com.ktdsuniversity.edu.hellospringhomework.bbs.service.impl;

import com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hellospringhomework.bbs.dao.impl.BoardDaoImpl;
import com.ktdsuniversity.edu.hellospringhomework.bbs.service.BoardService;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.WriteBoardVO;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.*;

// BoardService 구현체에 대한 테스트 클래스
@SpringBootTest
@Import({BoardServiceImpl.class, BoardDaoImpl.class})
public class BoardServiceImplTest {

    // BoardService 객체를 선언
    @Autowired
    private BoardService boardService;

    // Mock 객체를 사용해 BoardDao 선언
    @MockBean
    private BoardDao boardDao;

    // 새 게시글을 생성하는 기능에 대한 테스트
    @Test
    public void testCreateNewBoard(){
        WriteBoardVO writeBoardVO = new WriteBoardVO();

        // Mock 객체를 이용해 boardDao.insertNewBoard(writeBoardVO)가 호출되면 1을 리턴하도록 설정
        BDDMockito.given(boardDao.insertNewBoard(writeBoardVO)).willReturn(1);

        // 실제 메소드 호출
        boolean isSuccess = this.boardService.createNewBoard(writeBoardVO);

        // 성공했다면 true를 반환
        assertTrue(isSuccess);
        System.out.println("isSuccess: " + isSuccess);
    }
}

/*
Mock 객체는 우리가 필요로 하는 객체를 구현하는데 있어서 필요하지만 실제로 준비하기엔 여러가지 어려움이 따르는 대상을 필요한 부분만큼만 채워넣어서 만들어진 객체

Mock 객체를 만들 때

'의존성'의 원인으로 테스트를 하기 힘들 때

테스트 작성을 위한 환경 구축이 어려워서
환경 구축을 위한 작업 시간이 많이 필요한 경우 Mock 객체를 사용
경우에 따라서는 특정 모듈을 아직 갖고 있지 않아서 테스트 환경을 구축하지 못할 수도 있다.
타 부서와의 협의나 정책이 필요한 경우에도 Mock이 필요
테스트가 특정 경우나 순간에 의존적이라서
테스트 시간이 오래 걸려서
 */