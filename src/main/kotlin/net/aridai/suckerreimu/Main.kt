package net.aridai.suckerreimu

import kotlinx.browser.document

private fun main() {
    //  ログの出力設定を行う。
    Logger.isEnabled = !isReleaseBuild
    Logger.i { "スクリプト処理開始" }
}

//  リリースビルドで実行されているかどうかを判定する。
//  (NOTE: GitHub Pagesでホストするため、リリース環境では常にHTTPS化がされているものとする。)
private val isReleaseBuild: Boolean get() = document.location?.protocol?.lowercase()?.contains("https") ?: false
