<%-- 得意先検索画面 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先検索画面</title>

<style>
/* 検索画面全体 */
.find-screen {
	position: relative;
	min-height: 540px;
	padding-top: 18px;
}

/* 得意先管理メニューへのボタン：画面右上 */
.management-menu-area {
	position: absolute;
	top: 20px;
	right: 115px;
}

/* 画面中央の表示エリア */
.find-content {
	width: 630px;
	margin: 0 auto;
	text-align: center;
}

/* タイトル */
.find-title {
	margin: 0 0 38px 0;
}

/* エラーメッセージエリア */
.error-message-area {
	min-height: 38px;
	margin: 15px auto;
	text-align: center;
	color: red;
	font-weight: bold;
}
</style>

</head>
<body>

<jsp:include page="Header.jsp" />

<div class="find-screen">

	<%-- 得意先管理メニュー画面への遷移ボタン --%>
	<div class="management-menu-area">
		<form action="/jsys_sales/jsysFC" method="post">
			<button type="submit" name="buttonId" value="c110">
				得意先管理メニューへ
			</button>
		</form>
	</div>

	<div class="find-content">
		<h2 class="find-title">得意先検索</h2>
		得意先コードを入力して、検索ボタンをクリックしてください。
		
		<div class="error-message-area">
			<%--エラーメッセージがある場合、出力--%>
			<c:out value="${requestScope.errorMessage}" />
			<c:forEach var="message" items="${requestScope.errorMessageList}">
				<c:out value="${message}" /><br>
			</c:forEach>
		</div>

		<div style="text-align:center">
			<form action="/jsys_sales/jsysFC" method="post">
				得意先コード：<input type="text" name="custCode" value="<c:out value="${param.custCode}"/>" placeholder="例：KA0001" style="padding: 6px; font-size: 14px;"> <br>
				<button type="submit" name="buttonId" value="c102" style="margin-top: 15px; padding: 6px 14px; font-size: 14px; cursor: pointer;">検索</button><br>
			</form>
		</div>
	</div>

</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先検索の操作ヘルプ" />
	<jsp:param name="content" value="検索したい得意先の「得意先コード（半角英数字6桁）」を入力し、「検索」ボタンをクリックしてください。<br>（例: KA0001 など）" />
</jsp:include>

</body>
</html>