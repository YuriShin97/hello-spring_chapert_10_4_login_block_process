package com.ktdsuniversity.edu.hellospringhomework.bbs.vo;

// 게시글을 표현하는 VO(Value Object) 클래스
public class BoardVO {
    private int id; // 게시글 ID
    private String subject; // 게시글 제목
    private String content; // 게시글 내용
    private String email; // 이메일
    private int viewCnt; // 조회수
    private String crtDt; // 생성 날짜
    private String mdfyDt; // 수정 날짜
    private String fileName; // 파일 이름
    private String originFileName; // 원본 파일 이름

    // 게터, 세터 메서드 정의
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public String getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(String crtDt) {
        this.crtDt = crtDt;
    }

    public String getMdfyDt() {
        return mdfyDt;
    }

    public void setMdfyDt(String mdfyDt) {
        this.mdfyDt = mdfyDt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }
}
