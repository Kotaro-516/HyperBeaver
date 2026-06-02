<%-- 得意先登録画面 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先登録</title>
<style>
.container { width: 560px; margin: 30px auto; }
.login-user { text-align: right; margin-bottom: 20px; }
.form-row { display: flex; margin: 12px 0; align-items: center; }
.form-row label { width: 140px; }
.form-row input { width: 300px; padding: 5px; }
.error { color: red; font-weight: bold; margin: 15px 0; }
.actions { margin-top: 20px; display: flex; justify-content: space-between; }
</style>
</head>
<body>
<div class="container">
    <div class="login-user">
        従業員番号：<c:out value="${sessionScope.loginEmployee.empNo}" /><br>
        従業員氏名：<c:out value="${sessionScope.loginEmployee.empName}" />
    </div>
    <h2>得意先登録</h2>
    <div class="error">
        <c:if test="${not empty requestScope.errorMessage}">
            <c:out value="${requestScope.errorMessage}" /><br>
        </c:if>
        <c:forEach var="message" items="${requestScope.errorMessageList}">
            <c:out value="${message}" /><br>
        </c:forEach>
    </div>
    <form action="/jsys_sales/jsysFC" method="post">
        <div class="form-row">
            <label for="custName">得意先名</label>
            <input id="custName" type="text" name="custName" maxlength="32" value="<c:out value='${param.custName}' />">
        </div>
        <div class="form-row">
            <label for="telNo">電話番号</label>
            <input id="telNo" type="text" name="telNo" maxlength="13" placeholder="例：045-128-3581" value="<c:out value='${param.telNo}' />">
        </div>
        <div class="form-row">
            <label for="postalCode">郵便番号</label>
            <input id="postalCode" type="text" name="postalCode" maxlength="8" placeholder="例：220-0001" value="<c:out value='${param.postalCode}' />">
        </div>
        <div class="form-row">
            <label for="address">住所</label>
            <input id="address" type="text" name="address" maxlength="40" value="<c:out value='${param.address}' />">
        </div>
        <div class="form-row">
            <label for="discountRate">割引率</label>
            <input id="discountRate" type="number" name="discountRate" min="0" max="99" value="<c:out value='${param.discountRate}' />">％
        </div>
        <div class="actions">
            <button type="submit" name="buttonId" value="c201">登録</button>
        </div>
    </form>
    <form action="/jsys_sales/jsysFC" method="post">
        <button type="submit" name="buttonId" value="c100">得意先管理メニューへ</button>
    </form>
</div>
</body>
</html>
