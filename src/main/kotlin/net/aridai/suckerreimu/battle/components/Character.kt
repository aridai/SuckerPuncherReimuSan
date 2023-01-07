package net.aridai.suckerreimu.battle.components

import net.aridai.suckerreimu.Game
import net.aridai.suckerreimu.Resources
import net.aridai.suckerreimu.battle.CharacterSide
import org.w3c.dom.CanvasRenderingContext2D

/**
 * キャラクタ
 */
internal class Character(private val side: CharacterSide) {
    companion object {

        //  自分側キャラクタの中心座標
        private val OWN_CENTER_POSITION: Pair<Double, Double> = Game.CANVAS_WIDTH / 4 to Game.CANVAS_HEIGHT * 3 / 4

        //  相手側キャラクタの中心座標
        private val OPPONENT_CENTER_POSITION: Pair<Double, Double> = Game.CANVAS_WIDTH * 2 / 3 to Game.CANVAS_HEIGHT / 4
    }

    /**
     * 描画処理を行う。
     */
    fun render(context: CanvasRenderingContext2D) {
        val (cx, cy) = when (this.side) {
            CharacterSide.OWN -> OWN_CENTER_POSITION
            CharacterSide.OPPONENT -> OPPONENT_CENTER_POSITION
        }
        val image = when (this.side) {
            CharacterSide.OWN -> Resources.Images.characterReimu
            CharacterSide.OPPONENT -> Resources.Images.characterMarisa
        }
        context.drawImage(image = image, dx = cx - image.width / 2, dy = cy - image.height / 2)
    }
}
