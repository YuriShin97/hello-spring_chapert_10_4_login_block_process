package com.ktdsuniversity.edu.hellospringhomework.bbs.service;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.WriteBoardVO;

// 게시판 관련 서비스를 정의한 인터페이스
public interface BoardService {
    // 모든 게시글을 가져오는 메소드를 정의함
    public BoardListVO getAllBoards();

    // 새 게시글을 생성하는 메소드를 정의함
    public boolean createNewBoard(WriteBoardVO writeBoardVO);

    // 특정 게시글을 가져오는 메소드를 정의함
    public BoardVO selectOneBoard(int id, boolean isIncrease);
    
    // 특정 게시글을 수정하는 메소드를 정의함.
    public boolean updateOneBoard(ModifyBoardVO modifyBoardVO);
    
    // 특정 게시글을 삭제하는 메소드를 정의함.
    public boolean deleteOneBoard(int id);
}
