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
<jsp:include page="Header.jsp" />
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
  得意先コード<input type="text" name="custCode" value="<c:out value="${ param.custCode }"/>" placeholder="例：KA0001"> <br>
  <button type="submit" name="buttonId" value="c102">検索</button><br>
  </form>
 </div>
 
 <div style="text-align:right">
  <form action="/jsys_sales/jsysFC" method="post">
  <button type="submit" name="buttonId" value="c100">メニュー画面に戻る</button><br>
  </form>
 </div>
</div>

<jsp:include page="Help.jsp">
	<jsp:param name="title" value="得意先検索の操作ヘルプ" />
	<jsp:param name="content" value="検索したい得意先の「得意先コード（半角英数字6桁）」を入力し、「検索」ボタンをクリックしてください。<br>（例: KA0001 など）" />
</jsp:include>

</body>
</html>