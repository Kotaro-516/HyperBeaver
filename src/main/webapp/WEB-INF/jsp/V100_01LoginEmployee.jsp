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
					<style>
						/* === プレミアムヘルプボタン＆モーダルのCSS === */
						/* 1. フローティング「？」ボタン */
						.help-trigger {
							position: fixed;
							bottom: 25px;
							right: 25px;
							width: 50px;
							height: 50px;
							background: linear-gradient(135deg, #3498db, #2980b9);
							color: white;
							border-radius: 50%;
							text-align: center;
							line-height: 50px;
							font-size: 24px;
							font-weight: bold;
							box-shadow: 0 4px 10px rgba(52, 152, 219, 0.4);
							cursor: pointer;
							user-select: none;
							transition: transform 0.2s ease, box-shadow 0.2s ease;
							z-index: 999;
						}

						.help-trigger:hover {
							transform: scale(1.1);
							box-shadow: 0 6px 15px rgba(52, 152, 219, 0.6);
						}

						/* 2. モーダルオーバーレイ（暗幕） */
						.help-modal {
							display: none;
							/* 初期状態は非表示 */
							position: fixed;
							top: 0;
							left: 0;
							width: 100%;
							height: 100%;
							background-color: rgba(0, 0, 0, 0.5);
							/* 半透明の黒 */
							backdrop-filter: blur(3px);
							/* 背景を少しぼかす（高級感） */
							z-index: 1000;
							opacity: 0;
							transition: opacity 0.3s ease;
						}

						.help-modal.show {
							display: flex;
							align-items: center;
							justify-content: center;
							opacity: 1;
						}

						/* 3. モーダルコンテンツカード */
						.help-content-card {
							background-color: white;
							padding: 24px 30px;
							border-radius: 12px;
							box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
							max-width: 480px;
							width: 90%;
							transform: scale(0.9);
							transition: transform 0.3s ease;
							position: relative;
						}

						.help-modal.show .help-content-card {
							transform: scale(1);
						}

						/* 4. モーダルのヘッダー・閉じるボタン */
						.help-header {
							display: flex;
							justify-content: space-between;
							align-items: center;
							border-bottom: 1px solid #eee;
							padding-bottom: 12px;
							margin-bottom: 16px;
						}

						.help-title {
							margin: 0;
							font-size: 18px;
							color: #2c3e50;
							font-weight: bold;
						}

						.help-close {
							font-size: 24px;
							font-weight: bold;
							color: #aaa;
							cursor: pointer;
							background: none;
							border: none;
							padding: 0;
							line-height: 1;
						}

						.help-close:hover {
							color: #333;
						}

						/* 5. モーダルの本文テキスト */
						.help-body {
							font-size: 14px;
							line-height: 1.6;
							color: #555;
							text-align: left;
						}

						.help-body b {
							color: #2c3e50;
						}

						.help-body ul {
							margin: 8px 0;
							padding-left: 20px;
						}
					</style>
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

					<!-- === ヘルプ用のHTML要素 === -->
					<!-- フローティング「？」ボタン -->
					<div class="help-trigger" id="helpBtn">？</div>
					<!-- ポップアップモーダル -->
					<div class="help-modal" id="helpModal">
						<div class="help-content-card">
							<div class="help-header">
								<h4 class="help-title">ログイン操作ヘルプ</h4>
								<button class="help-close" id="closeBtn">&times;</button>
							</div>
							<div class="help-body">員
								従業番号とパスワードを入力してください。
								<ul>
									<li><b>従業員番号</b>: 半角英数字6桁（例: A99999）</li>
									<li><b>パスワード</b>: 半角英数字6桁（例: A99999）</li>
								</ul>
								入力が完了したら、中央の「ログイン」ボタンをクリックします。
							</div>
						</div>
					</div>
					<!-- === ヘルプ表示用のJavaScript制御 === -->
					<script>
						const helpBtn = document.getElementById('helpBtn');
						const helpModal = document.getElementById('helpModal');
						const closeBtn = document.getElementById('closeBtn');
						// モーダルを開く処理
						helpBtn.addEventListener('click', () => {
							helpModal.classList.add('show');
						});
						// モーダルを閉じる処理（閉じるボタン）
						closeBtn.addEventListener('click', () => {
							helpModal.classList.remove('show');
						});
						// モーダルを閉じる処理（モーダル外の背景クリック時）
						helpModal.addEventListener('click', (e) => {
							if (e.target === helpModal) {
								helpModal.classList.remove('show');
							}
						});
					</script>

				</body>

				</html>