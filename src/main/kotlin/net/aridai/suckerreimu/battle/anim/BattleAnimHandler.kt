package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * 対戦アニメーションのハンドラ
 */
internal interface BattleAnimHandler {

    /**
     * アニメーションをハンドリングする。
     */
    suspend fun handle(anim: BattleAnim, components: BattleSceneComponents)

    companion object {

        /**
         * 指定アニメーションのハンドラを取得する。
         */
        fun of(anim: BattleAnim): BattleAnimHandler {
            return when (anim) {
                is BattleAnim.Serial -> SerialAnimHandler
                is BattleAnim.Parallel -> ParallelAnimHandler
                is BattleAnim.Delay -> DelayAnimHandler
                is BattleAnim.Message -> MessageAnimHandler
                is BattleAnim.CharacterStatusBarEnteringAnim -> CharacterStatusBarEnteringAnimHandler
            }
        }
    }
}
