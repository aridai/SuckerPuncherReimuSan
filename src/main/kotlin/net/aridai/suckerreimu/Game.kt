package net.aridai.suckerreimu

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
    }
}
