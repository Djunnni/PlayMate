<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="student_menu" class="container">

	<div class="row">
		<div class="w-100">
			<div class="col-sm">메 뉴</div>
		</div>
		<div class="col-sm">
			<a href="#" onclick="menu(this)" id="1">가정통신문</a>
		</div>
		<div class="col-sm">
			<a href="#" onclick="menu(this)" id="2">학교 일정</a>
		</div>
		<div class="w-100"></div>
		<div class="col-sm">
			<a href="#" onclick="menu(this)" id="3">학급 공지사항</a>
		</div>
		<div class="col-sm">
			<a href="#" onclick="menu(this)" id="4">과제방</a>
		</div>
		<sec:authorize access="hasRole('TEACHER')">
	   	<div class="w-100">
				<div class="col-sm">
					<a href="#" onclick="menu(this)" id="5">성적확인</a>
				</div>
		</sec:authorize>
	</div>
</div>
</div>
