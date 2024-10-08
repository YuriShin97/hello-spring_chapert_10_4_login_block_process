<%--
  Created by IntelliJ IDEA.
  User: sinpyungho
  Date: 24. 10. 8.
  Time: 오후 2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL 을 위한 Directive 선언 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="right-align">
    <ul class="horizontal-list">
        <c:choose>
            <c:when test="${empty sessionScope._LOGIN_USER_}">
                <li>
                    <a href="/member/regist">회원가입</a>
                </li>
                <li>
                    <a href="/member/login">로그인</a>
                </li>
            </c:when>
            <c:otherwise>
                <li style="margin-right: 15px;">
                        ${sessionScope._LOGIN_USER_.name}
                    (${sessionScope._LOGIN_USER_.email})
                </li>
                <li>
                    <a href="/member/logout">로그아웃</a>
                </li>
                <li>
                    <a href="/member/delete-me">탈퇴</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
