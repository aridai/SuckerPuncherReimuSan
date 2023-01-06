package net.aridai.suckerreimu.battle.anim

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
