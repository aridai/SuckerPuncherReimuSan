package net.aridai.suckerreimu.battle.components

import net.aridai.suckerreimu.Resources
import org.w3c.dom.CanvasRenderingContext2D

/**
 * 対戦背景
 */
internal class BattleBackground {

    /**
     * 描画処理を行う。
     */
    fun render(context: CanvasRenderingContext2D) {
        //  背景画像を描画する。
        context.globalAlpha = 1.0
        context.drawImage(image = Resources.Images.background, dx = 0.0, dy = 0.0)
    }
}
