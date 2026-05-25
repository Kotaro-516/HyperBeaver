<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得意先検索画面</title>
</head>
<body>
<div style="text-align:center">
 <h2>得意先検索</h2><br>
 得意先番号を入力して、検索ボタンをクリックしてください。
 <div style="text-align:center; color:red; font-weight:bold;">
 <%--エラーメッセージがある場合、出力--%>
  <c:out value="${requestScope.errorMessage }" />
  <c:forEach var="message" items="${requestScope.errorMessageList}">
   <c:out value="${message}" />
  </c:forEach>
 </div>
 
 <div style="text-align:center">
 <form action="/jsys_sales/jsysFC" method="post">
  得意先コード<input type="text" name="custCode" value="<c:out value="${ param.custCode }"/>"> <br>
  <button type="submit" name="buttonId" value="c102">検索</button><br>
  </form>
 </div>
 
 <div style="text-align:right">
  <form action="/jsys_sales/jsysFC" method="post">
  <button type="submit" name="buttonId" value="c100">メニュー画面に戻る</button><br>
  </form>
 </div>
</div>
</body>
</html>