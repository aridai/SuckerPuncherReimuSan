package net.aridai.suckerreimu

import org.w3c.dom.Image
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * リソース
 */
internal object Resources {

    /**
     * 画像リソース
     */
    object Images {

        /**
         * 背景
         */
        lateinit var background: Image; private set

        suspend fun load() {
            Logger.v { "画像リソース読み込み 開始" }

            this.background = this.loadImage("img/background.png")

            Logger.v { "画像リソース読み込み 終了" }
        }

        //  画像を読み込む。
        private suspend fun loadImage(path: String): Image = suspendCoroutine { continuation ->
            val image = Image()

            image.onload = { continuation.resume(image) }
            image.onerror = { _, _, _, _, _ ->
                Logger.e { "画像読み込みエラー: $path" }
                continuation.resumeWithException(Exception("画像読み込みエラー: $path"))
            }

            image.src = path
        }
    }

    /**
     * リソースの読み込みを行う。
     */
    suspend fun load() {
        //  画像リソースの読み込みを行う。
        Images.load()
    }
}
