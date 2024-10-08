package com.ktdsuniversity.edu.hellospringhomework.bbs.vo;

import java.util.List;

// 게시글 리스트와 게시글 수를 합친 VO 클래스.
// 이 클래스로 서비스 에서 데이터를 리턴하면 컨트롤러와 뷰 측이 두 데이터를 한번에 전달받을 수 있음.
public class BoardListVO {
    private int boardCnt; // 게시글 수를 저장하는 필드
    private List<BoardVO> boardList; // 게시글 목록을 저장하는 필드

    // 게터, 세터 메서드 정의
    public int getBoardCnt() {
        return boardCnt;
    }

    public void setBoardCnt(int boardCnt) {
        this.boardCnt = boardCnt;
    }

    public List<BoardVO> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<BoardVO> boardList) {
        this.boardList = boardList;
    }
}
