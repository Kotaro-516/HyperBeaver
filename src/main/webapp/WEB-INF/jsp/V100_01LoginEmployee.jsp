<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員ログイン</title>
<style>
	body {
		font-family: sans-serif;
		margin: 0;
		padding: 0;
	}

	.login-area {
		text-align: center;
		margin-top: 80px;
	}

	.login-form {
		display: flex;
		flex-direction: column;
		align-items: center;
		gap: 18px;
		margin-top: 30px;
	}

	.input-row {
		display: flex;
		align-items: center;
		justify-content: center;
		gap: 10px;
	}

	.input-row label {
		width: 100px;
		text-align: right;
	}

	.input-row input {
		width: 180px;
		padding: 7px;
		box-sizing: border-box;
	}

	.login-button {
		margin-top: 12px;
	}

	.login-button button {
		width: 220px;
		padding: 10px 18px;
	}

	.error-message {
		color: red;
		font-weight: bold;
		margin-top: 20px;
		min-height: 24px;
	}
</style>
</head>

<body>
	<div class="login-area">
		<h2>従業員ログイン画面</h2>

		<div class="error-message">
			<%-- エラーメッセージがある場合、出力 --%>
			<c:out value="${requestScope.errorMessage}" />
			<c:forEach var="message" items="${requestScope.errorMessageList}">
				<c:out value="${message}" /><br>
			</c:forEach>
		</div>

		<form action="${pageContext.request.contextPath}/jsysFC" method="post">
			<div class="login-form">
				<div class="input-row">
					<label for="empNo">従業員番号：</label>
					<input type="text"
						id="empNo"
						name="empNo"
						value="<c:out value='${param.empNo}' />"
						placeholder="例：A99999"
						autocomplete="off">
				</div>

				<div class="input-row">
					<label for="password">パスワード：</label>
					<input type="password"
						id="password"
						name="password"
						value="<c:out value='${param.password}' />">
				</div>

				<div class="login-button">
					<button type="submit" name="buttonId" value="c001">ログイン</button>
				</div>
			</div>
		</form>
	</div>

	<jsp:include page="Help.jsp">
		<jsp:param name="title" value="ログイン画面の操作ヘルプ" />
		<jsp:param name="content" value="従業員番号とパスワードを入力してください。<ul><li><b>従業員番号</b>：半角英数字6桁(例：A12345)</li><li><b>パスワード</b>：半角英数字6桁(例：B12345)</li></ul>" />
	</jsp:include>
</body>
</html>
