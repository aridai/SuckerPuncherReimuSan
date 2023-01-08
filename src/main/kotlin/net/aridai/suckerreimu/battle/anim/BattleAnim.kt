package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide

/**
 * 対戦アニメーション
 */
internal sealed interface BattleAnim {

    /**
     * 直列アニメーション
     */
    data class Serial(val animations: List<BattleAnim>) : BattleAnim

    /**
     * 並列アニメーション
     */
    data class Parallel(val animations: List<BattleAnim>) : BattleAnim

    /**
     * 遅延
     */
    data class Delay(val delayInMs: Long) : BattleAnim

    /**
     * 背景のフェードインアニメ
     */
    data class BackgroundFadeInAnim(val durationInMs: Long = DEFAULT_DURATION_IN_MS) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 1000L
        }
    }

    /**
     * キャラクタ入場アニメ
     */
    data class CharacterEnteringAnim(
        val side: CharacterSide,
        val durationInMs: Long = DEFAULT_DURATION_IN_MS,
    ) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 250L
        }
    }

    /**
     * キャラクタ退出アニメ
     */
    data class CharacterExitingAnim(
        val side: CharacterSide,
        val durationInMs: Long = DEFAULT_DURATION_IN_MS,
    ) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 250L
        }
    }

    /**
     * キャラクタダメージアニメ
     */
    data class CharacterDamageAnim(
        val side: CharacterSide,
        val blinkCount: Int = DEFAULT_BLINK_COUNT,
        val durationInMs: Long = DEFAULT_DURATION_IN_MS,
    ) : BattleAnim {
        companion object {

            /**
             * デフォルト点滅回数
             */
            const val DEFAULT_BLINK_COUNT: Int = 2

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 750L
        }
    }

    /**
     * キャラクタ能力上昇アニメ
     */
    data class CharacterStatUpAnim(
        val side: CharacterSide,
        val durationInMs: Long = DEFAULT_DURATION_IN_MS,
    ) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 1200L
        }
    }

    /**
     * キャラクタ能力下降アニメ
     */
    data class CharacterStatDownAnim(
        val side: CharacterSide,
        val durationInMs: Long = DEFAULT_DURATION_IN_MS,
    ) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 1200L
        }
    }

    /**
     * キャラクタ持ち物アニメ
     */
    data class CharacterHeldItemAnim(
        val side: CharacterSide,
        val durationInMs: Long = DEFAULT_DURATION_IN_MS,
    ) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 1000L
        }
    }

    /**
     * キャラクタステータスバー入場アニメ
     */
    data class CharacterStatusBarEnteringAnim(
        val side: CharacterSide,
        val name: String,
        val currentHp: Int,
        val maxHp: Int,
        val durationInMs: Long = DEFAULT_DURATION_IN_MS,
    ) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 100L
        }
    }

    /**
     * メッセージ表示
     */
    data class Message(val text: String, val durationInMs: Long = DEFAULT_DURATION_IN_MS) : BattleAnim {
        companion object {

            /**
             * デフォルト期間
             */
            const val DEFAULT_DURATION_IN_MS: Long = 3000L
        }
    }
}
