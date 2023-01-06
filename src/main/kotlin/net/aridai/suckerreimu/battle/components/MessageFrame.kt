package net.aridai.suckerreimu.battle.components

import kotlinx.coroutines.delay
import org.w3c.dom.CanvasRenderingContext2D

/**
 * メッセージフレーム
 */
internal class MessageFrame {

    /**
     * 描画処理を行う。
     */
    fun render(context: CanvasRenderingContext2D) {
        //  TODO: 実装
    }

    /**
     * メッセージを表示する。
     */
    suspend fun showMessage(text: String, durationInMs: Long) {
        //  TODO: 実装
        delay(durationInMs)
    }
}
