<%-- 得意先登録結果画面 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先登録結果画面</title>

<style>
/* 登録結果画面全体 */
.result-screen {
	min-height: 540px;
	padding-top: 20px;
	text-align: center;
}

/* 画面中央の表示エリア */
.result-content {
	width: 560px;
	margin: 0 auto;
	text-align: center;
}

/* タイトル */
.result-title {
	margin: 0 0 30px 0;
}

/* 完了メッセージ */
.complete-message {
	display: inline-block;
	margin-bottom: 28px;
	padding: 14px 24px;
	border: 1px solid #000000;
	font-size: 20px;
}

/* 登録結果テーブル */
.result-table {
	width: 350px;
	margin: 0 auto;
	border-collapse: collapse;
	text-align: left;
	font-size: 16px;
}

.result-table th,
.result-table td {
	border: 1px solid #000000;
	padding: 5px 8px;
}

.result-table th {
	width: 125px;
	font-weight: normal;
}

.result-table td {
	width: 225px;
}

/* 前画面へ戻るボタン */
.actions {
	margin-top: 22px;
	text-align: center;
}
</style>

</head>
<body>

<%-- ハンバーガーメニューおよびログイン従業員情報を表示 --%>
<jsp:include page="Header.jsp" />

<div class="result-screen">
	<div class="result-content">

		<h2 class="result-title">得意先登録結果画面</h2>

		<div class="complete-message">
			得意先情報を登録しました。
		</div>

		<table class="result-table">
			<tr>
				<th>得意先コード</th>
				<td><c:out value="${requestScope.customer.custCode}" /></td>
			</tr>
			<tr>
				<th>得意先名</th>
				<td><c:out value="${requestScope.customer.custName}" /></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><c:out value="${requestScope.customer.telNo}" /></td>
			</tr>
			<tr>
				<th>郵便番号</th>
				<td><c:out value="${requestScope.customer.postalCode}" /></td>
			</tr>
			<tr>
				<th>住所</th>
				<td><c:out value="${requestScope.customer.address}" /></td>
			</tr>
			<tr>
				<th>割引率</th>
				<td>
					<fmt:formatNumber value="${requestScope.customer.discountRate}"
					                  minFractionDigits="1"
					                  maxFractionDigits="1" />%
				</td>
			</tr>
		</table>

		<div class="actions">
			<form action="/jsys_sales/jsysFC" method="post">
				<button type="submit" name="buttonId" value="c200">
					前画面へ戻る
				</button>
			</form>
		</div>

	</div>
</div>

<%-- ヘルプボタンを表示 --%>
<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先登録結果の操作ヘルプ" />
	<jsp:param name="content" value="登録された得意先情報を確認してください。<br>入力画面へ戻る場合は、「前画面へ戻る」ボタンをクリックしてください。" />
</jsp:include>

</body>
</html>
