<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>月別受注集計</title>
<style>
body {
	font-family: sans-serif;
	margin: 0;
	padding: 0;
}

.container {
	width: 850px;
	margin: 32px auto;
}

h1 {
	text-align: center;
	color: #444;
	margin-bottom: 28px;
}

.search-area {
	display: flex;
	gap: 8px;
	align-items: center;
	justify-content: center;
	margin: 28px 0;
}

.search-area input {
	width: 90px;
	padding: 5px;
}

button {
	border: 1px solid #999;
	background-color: #f5f5f5;
	padding: 6px 20px;
	cursor: pointer;
}

button:hover {
	background-color: #e6e6e6;
}

.message-area {
	width: 680px;
	margin: 15px auto;
	padding: 10px;
	border: 1px solid #444;
	color: #c00;
	background-color: #f9f9f9;
	box-sizing: border-box;
}

table {
	width: 680px;
	border-collapse: collapse;
	margin: 22px auto;
}

th, td {
	border: 1px solid #333;
	padding: 8px;
}

th {
	text-align: center;
	background-color: #eee;
}

.amount {
	text-align: right;
}

.total {
	font-weight: bold;
}

.navigation {
	text-align: center;
	margin-top: 25px;
}
</style>
</head>

<body>

<!-- =========================
     共通ヘッダー
     ・ハンバーガーメニュー
     ・ログイン中の従業員情報
========================= -->
<jsp:include page="/WEB-INF/jsp/Header.jsp" />

<div class="container">
	<h1>月別受注集計</h1>

	<form action="${pageContext.request.contextPath}/jsysFC" method="post">
		<div class="search-area">
			<label>集計する月</label>

			<input type="number"
				name="year"
				min="1000"
				max="9999"
				value="<c:out value='${year}'/>"
				required>
			<span>年</span>

			<input type="number"
				name="month"
				min="1"
				max="12"
				value="<c:out value='${month}'/>"
				required>
			<span>月</span>

			<button type="submit" name="buttonId" value="c312">集計</button>
		</div>
	</form>

	<!-- エラーメッセージ表示 -->
	<c:if test="${not empty errorMessage}">
		<div class="message-area">
			<c:out value="${errorMessage}"/>
		</div>
	</c:if>

	<c:if test="${not empty errorMessageList}">
		<div class="message-area">
			<c:forEach var="message" items="${errorMessageList}">
				<div><c:out value="${message}"/></div>
			</c:forEach>
		</div>
	</c:if>

	<!-- 集計結果表示 -->
	<c:if test="${not empty summaryList}">
		<table>
			<thead>
				<tr>
					<th>得意先コード</th>
					<th>得意先名</th>
					<th>得意先別合計金額</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="summary" items="${summaryList}">
					<tr>
						<td><c:out value="${summary.custCode}"/></td>
						<td><c:out value="${summary.custName}"/></td>
						<td class="amount">
							<fmt:formatNumber value="${summary.totalPrice}" pattern="#,##0"/> 円
						</td>
					</tr>
				</c:forEach>
				<tr class="total">
					<td colspan="2">総計</td>
					<td class="amount">
						<fmt:formatNumber value="${total}" pattern="#,##0"/> 円
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>

	<div class="navigation">
		<form action="${pageContext.request.contextPath}/jsysFC" method="post">
			<button type="submit" name="buttonId" value="c310">前画面へ戻る</button>
		</form>
	</div>
</div>

<!-- =========================
     共通ヘルプボタン
========================= -->
<jsp:include page="/WEB-INF/jsp/Help.jsp">
	<jsp:param name="title" value="月別受注集計の操作ヘルプ" />
	<jsp:param name="content" value="集計する年と月を半角数字で入力し、「集計」ボタンを押してください。指定月の受注情報を得意先ごとに集計し、得意先別合計金額と総計を表示します。集計は受注日を基準に行います。" />
</jsp:include>

</body>
</html>
