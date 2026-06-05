<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>得意先一覧</title>

<style>

/* =========================
   タイトル
========================= */
h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #444;
}

/* =========================
   テーブル全体
   ・中央寄せ
   ・軽い影
========================= */
table {
    border-collapse: separate;
    border-spacing: 0;
    width: 100%;
}

/* =========================
   テーブルコンテナ（重要）
   ・高さ制限
   ・スクロール
========================= */
.table-container {
    width: 90%;
    margin: 0 auto;
    max-height: 320px;      /* ★ 約10件分表示 */
    overflow-y: auto;       /* ★ 縦スクロール */
    border: 1px solid #999;
}

/* =========================
   ヘッダ（固定）
========================= */
thead th {
    position: sticky;       /* スクロールしても固定 */
    top: 0;
    background-color: #eee;
    z-index: 1;
}

/* =========================
   セル
========================= */
th, td {
    border: 1px solid #999;
    padding: 6px;
    height: 30px;           /* ★ 行高さを揃える */
}

/* =========================
   しましま
========================= */
tbody tr:nth-child(odd) {
    background-color: #fffacd;
}
tbody tr:nth-child(even) {
    background-color: #ffffff;
}

/* =========================
   ホバー（マウス乗せ）
========================= */
tbody tr:hover {
    background-color: #f0f8ff;
}

/* =========================
   選択行
========================= */
tr.selected {
    background-color: #cce5ff !important;
}

/* =========================
   メッセージボックス
========================= */
.message {
    margin: 20px auto;
    border: 1px solid #999;
    padding: 10px;
    width: 50%;
    text-align: center;
    background-color: #f9f9f9;
}

/* =========================
   ボタン（シンプル）
========================= */
button {
    border: 1px solid #999;
    background-color: #f5f5f5;
    padding: 5px 10px;
    cursor: pointer;
}

button:hover {
    background-color: #e6e6e6;
}

</style>

<script>

/* =========================
   行選択処理
   ・ラジオ選択
   ・行を青くする
========================= */
function selectRow(radio, rowId) {
    let rows = document.querySelectorAll("tbody tr");
    rows.forEach(r => r.classList.remove("selected"));

    document.getElementById(rowId).classList.add("selected");
}

</script>

</head>

<body>

<!-- ヘッダー -->
<jsp:include page="/WEB-INF/jsp/Header.jsp" />

<h2>得意先一覧</h2>

<form action="jsysFC" method="post">

<!-- =========================
     ボタンエリア
========================= -->
<div style="text-align:right;">
    <button type="submit" name="buttonId" value="c200">登録</button>
    <button type="submit" name="buttonId" value="c202" disabled>変更</button>
    <button type="submit" name="buttonId" value="c300">削除</button>
    <button type="submit" name="buttonId" value="c110">得意先管理メニューへ</button>
</div>

<br><br>

<!-- =========================
     一覧（スクロール付き）
========================= -->
<div class="table-container">
<table>
<thead>
<tr>
    <th>選択</th>
    <th>得意先コード</th>
    <th>得意先名</th>
    <th>電話番号</th>
    <th>郵便番号</th>
    <th>住所</th>
    <th>割引率</th>
</tr>
</thead>

<tbody>
<c:forEach var="customer" items="${customerList}" varStatus="status">
<tr id="row${status.index}">

    <!-- ラジオボタン -->
    <td>
        <input type="radio" name="custCode"
               value="${customer.custCode}"
               onclick="selectRow(this,'row${status.index}')">
    </td>

    <!-- データ表示 -->
    <td>${customer.custCode}</td>
    <td>${customer.custName}</td>
    <td>${customer.telNo}</td>
    <td>${customer.postalCode}</td>
    <td>${customer.address}</td>
    <td>${customer.discountRate}%</td>

</tr>
</c:forEach>
</tbody>
</table>
</div>

<!-- =========================
     メッセージ表示
========================= -->
   <c:if test="${not empty errorMessage}">
    <div class="message">
        ${errorMessage}
    </div>
    </c:if>

</div>

</form>

<!-- =========================
     ヘルプ
========================= -->
<jsp:include page="/WEB-INF/jsp/Help.jsp">
    <jsp:param name="title" value="得意先一覧の操作ヘルプ" />
    <jsp:param name="content" value="登録されている得意先情報を一覧で確認できます。<br>
	削除する場合は、左側のラジオボタンで対象の得意先を1件選択し、「削除」ボタンをクリックしてください。<br>
	「登録」ボタンをクリックすると、得意先登録画面へ移動します。<br>
	「変更」ボタンは今回使用できません。<br>
	一覧が多い場合は、表の中をスクロールして確認してください。" />
</jsp:include>

</body>
</html>