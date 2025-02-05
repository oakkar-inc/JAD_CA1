<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="sidebar-container">
	<div class="sidebar">
		<div class="sidebar-nav h3">
			<a href="<%=request.getContextPath()%>/manageUser">
				<div class="sidebar-navitem">
					<img src="<%=request.getContextPath()%>/view/assets/user-square.png">
					<p>User</p>
				</div>
			</a> 
			<a href="<%=request.getContextPath()%>/view/manageCategory">
				<div class="sidebar-navitem">
					<img src="<%=request.getContextPath()%>/view/assets/menu.png" style="filter: invert(100%);">
					<p>Category</p>
				</div>
			</a>
		</div>
	</div>
</div>