package net.aridai.suckerreimu

import org.w3c.dom.CanvasRenderingContext2D
import kotlin.math.PI

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

    private var point: Pair<Double, Double>? = null

    /**
     * 初期化処理を行う。
     */
    fun init() {
        //  TODO: 実装
    }

    /**
     * 描画処理を行う。
     */
    fun render(context: CanvasRenderingContext2D) {
        //  TODO: 仮
        context.drawImage(Resources.Images.background, 0.0, 0.0)
        val point = this.point
        if (point != null) {
            val (x, y) = point
            context.beginPath()
            context.fillStyle = "#000000"
            context.arc(x, y, 10.0, 0.0, 2 * PI)
            context.fill()
            context.font = "48px Arial"
            context.fillText("(${x.toInt()}, ${y.toInt()})", x + 10.0 * 2, y + 12)
        }
    }

    /**
     * タッチイベントが発生したとき。
     */
    fun onTouchEventOccurred(event: TouchEvent) {
        //  TODO: 仮
        this.point = when (event) {
            is TouchEvent.PointerDown -> event.x to event.y
            is TouchEvent.PointerMove -> if (this.point != null) event.x to event.y else null
            is TouchEvent.PointerUp, is TouchEvent.PointerCancel -> null
        }
    }
}
