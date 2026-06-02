<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>得意先一覧</title>

<style>
table {
    border-collapse: collapse;
    width: 100%;
}
th, td {
    border: 1px solid #000;
    padding: 5px;
}
th {
    background-color: #eee;
}


/* ★しましま追加 */
tbody tr:nth-child(odd) {
    background-color: #fffacd;  /* 薄い黄色 */
}

tbody tr:nth-child(even) {
    background-color: #ffffff;  /* 白 */
}

/* ★選択行を優先表示 */
tr.selected {
    background-color: #cce5ff!important;
}
.message {
    margin-top: 20px;
    border: 1px solid #000;
    padding: 10px;
    width: 400px;
}
</style>

<script>
function selectRow(radio, rowId) {
    // 全行リセット
    let rows = document.querySelectorAll("tbody tr");
    rows.forEach(r => r.classList.remove("selected"));

    // 選択行をハイライト
    document.getElementById(rowId).classList.add("selected");
}
</script>

</head>

<body>

<h2 style="text-align:center;">得意先一覧</h2>

<form action="jsysFC" method="post">


<!-- ボタン -->
<div style="text-align:right;">
    <button type="submit" name="buttonId" value="c201">登録</button>
    <button type="submit" name="buttonId" value="c202">変更</button>
    <button type="submit" name="buttonId" value="c203">削除</button>
    <button type="submit" name="buttonId" value="c100">メニューへ</button>
</div>

<br><br>

<!-- 一覧 -->
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
    <td>
        <input type="radio" name="custCode"
               value="${customer.custCode}"
               onclick="selectRow(this,'row${status.index}')">
    </td>
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

<!-- メッセージ -->
<div class="message">
    <c:if test="${not empty errorMessage}">
        ${errorMessage}
    </c:if>
</div>

</form>

</body>
</html>