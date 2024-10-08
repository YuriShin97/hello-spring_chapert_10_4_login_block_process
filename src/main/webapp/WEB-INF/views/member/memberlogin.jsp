<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>로그인 화면</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css" />
  </head>
  <body>
    <h1>로그인</h1>
    <form:form modelAttribute="loginMemberVO" method="post">
      <div>
        <form:errors path="email" element="div" cssClass="errors" />
        <form:errors path="password" element="div" cssClass="errors" />
        <c:if test="${not empty message}">
        	<div class="errors">${message}</div>
        </c:if>
      </div>

      <div class="grid4">
        <label for="email">이메일</label>
        <input
          id="email"
          type="email"
          name="email"
          value="${loginMemberVO.email}"
        />

        <label for="password">비밀번호</label>
        <input
          id="password"
          type="password"
          name="password"
          value="${loginMemberVO.password}"
        />

        <div class="btn-group">
          <div class="right-align">
            <input id="btn-regist" type="submit" value="로그인" />
          </div>
        </div>
      </div>
    </form:form>
  </body>
</html>
