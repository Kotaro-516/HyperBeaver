<%-- 得意先登録結果画面 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先登録結果</title>
<style>
.container { width: 560px; margin: 30px auto; }
.login-user { text-align: right; margin-bottom: 20px; }
.message { margin: 20px 0; font-weight: bold; }
.detail dt { float: left; clear: left; width: 140px; margin: 6px 0; }
.detail dd { margin-left: 150px; padding: 6px 0; }
.actions { margin-top: 25px; }
</style>
</head>
<body>
<div class="container">
    <div class="login-user">
        従業員番号：<c:out value="${sessionScope.loginEmployee.empNo}" /><br>
        従業員氏名：<c:out value="${sessionScope.loginEmployee.empName}" />
    </div>
    <h2>得意先登録結果画面</h2>
    <div class="message">得意先情報を登録しました。</div>
    <dl class="detail">
        <dt>得意先コード</dt><dd><c:out value="${requestScope.customer.custCode}" /></dd>
        <dt>得意先名</dt><dd><c:out value="${requestScope.customer.custName}" /></dd>
        <dt>電話番号</dt><dd><c:out value="${requestScope.customer.telNo}" /></dd>
        <dt>郵便番号</dt><dd><c:out value="${requestScope.customer.postalCode}" /></dd>
        <dt>住所</dt><dd><c:out value="${requestScope.customer.address}" /></dd>
        <dt>割引率</dt><dd><c:out value="${requestScope.customer.discountRate}" />％</dd>
    </dl>
    <div class="actions">
        <form action="/jsys_sales/jsysFC" method="post">
            <button type="submit" name="buttonId" value="c200">前画面へ戻る</button>
        </form>
    </div>
</div>
</body>
</html>
