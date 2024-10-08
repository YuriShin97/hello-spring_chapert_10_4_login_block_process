package com.ktdsuniversity.edu.hellospringhomework.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.hellospringhomework.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hellospringhomework.bbs.service.BoardService;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hellospringhomework.bbs.vo.WriteBoardVO;
import com.ktdsuniversity.edu.hellospringhomework.common.beans.FileHandler;
import com.ktdsuniversity.edu.hellospringhomework.common.vo.StoreResultVO;

// 서비스 구현체를 선언하고 이를 스프링에 등록
@Service
public class BoardServiceImpl implements BoardService {

	// application.yml 파일에서 app.multipart.base-dir 설정 값을 가져온다.
	// @Value 는 Spring Bean 에서만 사용이 가능.
	// Spring Bean : @Controller, @Service, @Repository
	// @Component: Spring Bean 을 만들어주는 Annotation
//	@Value("${app.multipart.base-dir}")
//	private String baseDirectory;
	
	@Autowired
	private FileHandler fileHandler;
	
    // BoardDao 선언
    @Autowired
    private BoardDao boardDao;

    // 모든 게시글을 가져오는 메소드
    @Override
    public BoardListVO getAllBoards() {
        BoardListVO boardListVO = new BoardListVO(); // 이 객체에 메소드 반환값 저장 예정
        // 게시글 개수 세팅
        boardListVO.setBoardCnt(boardDao.selectBoardAllCount());
        // 게시글 리스트 세팅
        boardListVO.setBoardList(boardDao.selectAllBoard());
        return boardListVO; // 세팅된 객체 반환
    }

    // 새로운 게시글을 생성하는 메소드
    @Override
    public boolean createNewBoard(WriteBoardVO writeBoardVO) {
    	
    	// 파일 업로드 처리
    	MultipartFile file = writeBoardVO.getFile();
    	
    	StoreResultVO storeResultVO = this.fileHandler.storeFile(file);
    	if(storeResultVO != null) {
    		writeBoardVO.setFileName(storeResultVO.getObfuscatedFileName());
    		writeBoardVO.setOriginFileName(storeResultVO.getOriginFileName());
    	}
    	
        // 삽입된 게시글 수를 확인
        int createCount = boardDao.insertNewBoard(writeBoardVO);
        return createCount > 0; // 생성된 게시글이 있으면 true 반환
    }

    // 하나의 게시글을 선택하는 메소드
    @Override
    public BoardVO selectOneBoard(int id, boolean isIncrease) {
        
    	if(isIncrease) {
    	// 조회수를 증가시킴
        int updateCount = boardDao.updateViewCount(id);

        // 조회수를 증가시킨 수가 0이면 예외를 던짐
        if (updateCount == 0) {
            throw new IllegalStateException("잘못된 접근입니다.");
        	}
    	}
        // 해당 id의 게시글 정보 가져옴
        BoardVO boardVO = this.boardDao.selectOneBoard(id);
        if(boardVO == null) {
        	throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        return boardVO;
    }
    
    @Override
    public boolean updateOneBoard(ModifyBoardVO modifyBoardVO) {
    	
    	// 기존의 파일을 삭제하기 위해서 업데이트 하기 전 게시글의 정보를 조회한다.
    	BoardVO boardVO = this.boardDao.selectOneBoard(modifyBoardVO.getId());
    	
    	MultipartFile file = modifyBoardVO.getFile();
    	
    	StoreResultVO storeResultVO = this.fileHandler.storeFile(file);
    	
    	if(storeResultVO != null) {
    		modifyBoardVO.setFileName(storeResultVO.getObfuscatedFileName());
    		modifyBoardVO.setOriginFileName(storeResultVO.getOriginFileName());
    		
    	}
    	int updateCount = this.boardDao.updateOneBoard(modifyBoardVO);
    	
    	if(updateCount > 0) {
    		this.fileHandler.deleteFile(boardVO.getFileName());
    	}
    	
    	return updateCount > 0;
    }
    
    @Override
    public boolean deleteOneBoard(int id) {
    	
    	BoardVO boardVO = this.boardDao.selectOneBoard(id);
    	
    	int deleteCount = this.boardDao.deleteOneBoard(id);
    	
    	if(deleteCount > 0) {
    		this.fileHandler.deleteFile(boardVO.getFileName());
    	}
    	return deleteCount > 0;
    }
}
