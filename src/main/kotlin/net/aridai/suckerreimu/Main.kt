package net.aridai.suckerreimu

import kotlinx.browser.document
import kotlinx.browser.window

private fun main() {
    //  ログの出力設定を行う。
    Logger.isEnabled = !isReleaseBuild
    Logger.i { "スクリプト処理開始" }

    //  画面のリサイズコールバックを登録する。
    //  (初回のコールバックは手動で呼び出す。)
    window.onresize = { onResize() }
    onResize()
}

//  リリースビルドで実行されているかどうかを判定する。
//  (NOTE: GitHub Pagesでホストするため、リリース環境では常にHTTPS化がされているものとする。)
private val isReleaseBuild: Boolean get() = document.location?.protocol?.lowercase()?.contains("https") ?: false
