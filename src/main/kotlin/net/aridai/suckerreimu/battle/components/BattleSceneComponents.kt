package net.aridai.suckerreimu.battle.components

import net.aridai.suckerreimu.TouchEvent
import org.w3c.dom.CanvasRenderingContext2D

/**
 * 対戦シーンのコンポーネント群
 */
internal class BattleSceneComponents {

    /**
     * 対戦背景
     */
    val battleBackground: BattleBackground = BattleBackground()

    /**
     * メッセージフレーム
     */
    val messageFrame: MessageFrame = MessageFrame()

    /**
     * 描画処理を行う。
     */
    fun render(context: CanvasRenderingContext2D) {
        //  コンポーネントの描画処理を呼び出していく。
        this.battleBackground.render(context)
        this.messageFrame.render(context)
    }

    /**
     * タッチイベントが発生したとき。
     */
    fun onTouchEventOccurred(event: TouchEvent) {
        //  TODO: 子コンポーネントのタッチイベントコールバックの呼び出し
    }
}
