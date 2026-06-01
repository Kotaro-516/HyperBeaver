<%-- pageディレクティブの設定 --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- taglibディレクティブで、使用するタグライブラリを宣言 --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- === 共通ヘルプボタンおよびポップアップモーダル === -->
<style>
	/* フローティング「？」ボタン */
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

	/* モーダルオーバーレイ（暗幕） */
	.help-modal {
		display: none;
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.5);
		backdrop-filter: blur(3px);
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

	/* モーダルコンテンツカード */
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

	/* ヘッダー・閉じるボタン */
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

	/* 本文テキスト */
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

<!-- フローティング「？」ボタン -->
<div class="help-trigger" id="helpBtn">？</div>

<!-- ポップアップモーダル -->
<div class="help-modal" id="helpModal">
	<div class="help-content-card">
		<div class="help-header">
			<h4 class="help-title">${param.title}</h4>
			<button class="help-close" id="closeBtn">&times;</button>
		</div>
		<div class="help-body">
			${param.content}
		</div>
	</div>
</div>

<!-- ポップアップ開閉用のJSスクリプト -->
<script>
	(function() {
		const helpBtn = document.getElementById('helpBtn');
		const helpModal = document.getElementById('helpModal');
		const closeBtn = document.getElementById('closeBtn');

		if (helpBtn && helpModal && closeBtn) {
			helpBtn.addEventListener('click', () => {
				helpModal.classList.add('show');
			});

			closeBtn.addEventListener('click', () => {
				helpModal.classList.remove('show');
			});

			helpModal.addEventListener('click', (e) => {
				if (e.target === helpModal) {
					helpModal.classList.remove('show');
				}
			});
		}
	})();
</script>