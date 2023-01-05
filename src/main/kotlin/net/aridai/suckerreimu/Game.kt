package net.aridai.suckerreimu

import org.w3c.dom.CanvasRenderingContext2D

/**
 * ゲーム
 */
internal class Game {
    companion object {

        /**
         * Canvasの描画横幅
         */
        const val CANVAS_WIDTH: Double = 1280.0

        /**
         * Canvasの描画高さ
         */
        const val CANVAS_HEIGHT: Double = 720.0

        /**
         * 描画時の拡大率
         * (モバイル環境でのぼやけ防止用)
         */
        const val RENDERING_SCALE: Int = 2

        /**
         * ゲームのシングルトンインスタンス
         */
        val INSTANCE: Game by lazy { Game() }
    }

    /**
     * 描画処理を行う。
     */
    fun render(context: CanvasRenderingContext2D) {
        //  TODO: 仮
        context.drawImage(Resources.Images.background, 0.0, 0.0)
    }
}
