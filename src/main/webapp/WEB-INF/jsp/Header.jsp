<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- === 共通ヘッダー（ログイン従業員情報の表示） === -->
<style>
	.global-header {
		width: 100%;
		background-color: #f8f9fa;
		border-bottom: 1px solid #dee2e6;
		padding: 10px 20px;
		display: flex;
		justify-content: flex-end; /* 右寄せに配置 */
		align-items: center;
		box-sizing: border-box;
		margin-bottom: 25px;
		font-family: "Helvetica Neue", Arial, "Hiragino Kaku Gothic ProN", Meiryo, sans-serif;
	}
	.header-user-info {
		font-size: 13px;
		color: #495057;
	}
	.header-user-name {
		font-weight: bold;
		color: #212529;
		margin-right: 5px;
	}
	.header-user-code {
		color: #6c757d;
		font-size: 12px;
	}
</style>

<div class="global-header">
	<div class="header-user-info">
		<c:choose>
			<c:when test="${not empty sessionScope.loginEmployee}">
				ログイン中：<span class="header-user-name"><c:out value="${sessionScope.loginEmployee.empName}" /></span> さん 
				<span class="header-user-code">（従業員番号：<c:out value="${sessionScope.loginEmployee.empNo}" />）</span>
			</c:when>
			<c:otherwise>
				<span style="color: #dc3545; font-weight: bold;">ログインしていません</span>
			</c:otherwise>
		</c:choose>
	</div>
</div>
