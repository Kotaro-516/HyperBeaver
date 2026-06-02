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
				</head>

				<body>
					<div style="text-align:center">
						<h2>従業員ログイン画面</h2>
						<div style="color:red; font-weight:bold;">
							<%-- エラーメッセージがある場合、出力 --%>
								<c:out value="${requestScope.errorMessage}" />
								<c:forEach var="message" items="${requestScope.errorMessageList}">
									<c:out value="${message}" /><br>
								</c:forEach>
						</div>
						<div>
							<form action="/jsys_sales/jsysFC" method="post">
								従業員番号：<input type="text" name="empNo" value="<c:out value="${param.empNo}" />" placeholder="例：A99999"><br>
								パスワード：<input type="password" name="password" value="<c:out value="${param.password}" />" placeholder="例：A99999"><br>
								<button type="submit" name="buttonId" value="c001">ログイン</button>
							</form>
						</div>
					</div>

					<jsp:include page="Help.jsp">
					<jsp:param name="title" value="ログイン画面の操作ヘルプ" />
					<jsp:param name="content" value="従業員番号とパスワードを入力してください。<ul><li><b>従業員番号</b>：半角英数字6桁(例：A12345)</li><li><b>パスワード</b>：半角英数字6桁(例：B12345)</li></ul>" />
					</jsp:include>

				</body>

				</html>