<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先登録画面</title>

<style>
/* 登録画面全体 */
.regist-screen {
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

/* 画面中央の入力エリア */
.regist-content {
	width: 630px;
	margin: 0 auto;
	text-align: center;
}

/* タイトル */
.regist-title {
	margin: 0 0 38px 0;
}

/* 入力欄全体 */
.input-area {
	width: 100%;
	margin: 0 auto;
}

/* 入力項目1行分 */
.input-row {
	display: flex;
	align-items: center;
	margin-bottom: 18px;
	text-align: left;
}

/* ラベル */
.input-label {
	width: 135px;
	font-size: 16px;
}

/* 入力欄 */
.input-text {
	height: 28px;
	font-size: 16px;
	box-sizing: border-box;
}

.cust-name {
	width: 315px;
}

.tel-no {
	width: 190px;
}

.postal-code {
	width: 190px;
}

.address {
	width: 420px;
}

.discount-rate {
	width: 60px;
	text-align: right;
}

/* 割引率の％表示 */
.percent-label {
	margin-left: 10px;
	font-size: 16px;
}

/* 登録ボタン */
.regist-button-area {
	margin-top: 28px;
	text-align: center;
}

/* メッセージエリア：メッセージがある場合のみJSP側で表示する */
.message-area {
	width: 630px;
	min-height: 38px;
	margin: 30px auto 0 auto;
	border: 1px solid #000000;
	text-align: left;
	padding: 8px;
	box-sizing: border-box;
	color: red;
	font-weight: bold;
}

</style>

</head>
<body>

<jsp:include page="Header.jsp" />

<div class="regist-screen">

	<%-- 得意先管理メニュー画面への遷移ボタン --%>
	<div class="management-menu-area">
		<form action="/jsys_sales/jsysFC" method="post">
			<button type="submit" name="buttonId" value="c110">
				得意先管理メニューへ
			</button>
		</form>
	</div>

	<div class="regist-content">

		<h2 class="regist-title">得意先登録</h2>

		<form action="/jsys_sales/jsysFC" method="post">

			<div class="input-area">

				<div class="input-row">
					<label class="input-label" for="custName">得意先名</label>
					<input type="text"
					       id="custName"
					       class="input-text cust-name"
					       name="custName"
					       value="<c:out value="${param.custName}" />"
					       maxlength="32"
					       placeholder="例：Aストア">
				</div>

				<div class="input-row">
					<label class="input-label" for="telNo">電話番号</label>
					<input type="text"
					       id="telNo"
					       class="input-text tel-no"
					       name="telNo"
					       value="<c:out value="${param.telNo}" />"
					       maxlength="13"
					       placeholder="例：045-128-3581">
				</div>

				<div class="input-row">
					<label class="input-label" for="postalCode">郵便番号</label>
					<input type="text"
					       id="postalCode"
					       class="input-text postal-code"
					       name="postalCode"
					       value="<c:out value="${param.postalCode}" />"
					       maxlength="8"
					       placeholder="例：220-0001">
				</div>

				<div class="input-row">
					<label class="input-label" for="address">住所</label>
					<input type="text"
					       id="address"
					       class="input-text address"
					       name="address"
					       value="<c:out value="${param.address}" />"
					       maxlength="40"
					       placeholder="例：横浜市西区北幸2-1">
				</div>

				<div class="input-row">
					<label class="input-label" for="discountRate">割引率</label>
					<input type="text"
					       id="discountRate"
					       class="input-text discount-rate"
					       name="discountRate"
					       value="<c:out value="${param.discountRate}" />"
					       maxlength="2"
					       placeholder="0">
					<span class="percent-label">%</span>
				</div>

			</div>

			<div class="regist-button-area">
				<button type="submit" name="buttonId" value="c201">登録</button>
			</div>

		</form>

<%-- メッセージがある場合のみ、メッセージエリアを表示 --%>
	<c:if test="${not empty requestScope.errorMessage or not empty requestScope.errorMessageList}">
		<div class="message-area">
			<c:if test="${not empty requestScope.errorMessage}">
				<c:out value="${requestScope.errorMessage}" /><br>
			</c:if>

			<c:forEach var="message" items="${requestScope.errorMessageList}">
				<c:out value="${message}" /><br>
			</c:forEach>
		</div>
	</c:if>

	</div>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先登録の操作ヘルプ" />
	<jsp:param name="content" value="登録する得意先の「得意先名」「電話番号」「郵便番号」「住所」「割引率」を入力し、「登録」ボタンをクリックしてください。<br>電話番号は半角数字と半角ハイフンで入力してください。<br>郵便番号は「999-9999」の形式で入力してください。" />
</jsp:include>

</body>
</html>