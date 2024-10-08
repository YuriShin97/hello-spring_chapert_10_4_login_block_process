<%--
  Created by IntelliJ IDEA.
  User: sinpyungho
  Date: 24. 9. 28.
  Time: 오전 8:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL 을 위한 Directive 선언 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 조회</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
</head>
<body>
	<div class="grid2">
		<div class="right-align">
			총 ${boardListVO.boardCnt}건의 게시글이 검색되었습니다.
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>이메일</th>
					<th>조회수</th>
					<th>등록일</th>
					<th>수정일</th>
				</tr>
			</thead>
			<tbody>
				<!--
				${boardListVO.boardList} 가 비어있지 않다면
				forEach 를 통해 목록을 보여주고
				그렇지 않다면
				"게시글이 없습니다"를 보여준다.
				-->
				<c:choose>
					<c:when test="${not empty boardListVO.boardList}">
						<c:forEach items="${boardListVO.boardList}" var="board">
							<tr>
								<td>${board.id}</td>
								<td>
								<a href="/board/view/${board.id}">
								${board.subject}</td>
								</a>
								<td>${board.email}</td>
								<td>${board.viewCnt}</td>
								<td>${board.crtDt}</td>
								<td>${board.mdfyDt}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">게시글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="right-align">
			<a href="/board/write">게시글 등록</a>
		</div>
	</div>
	
</body>
</html>