package net.aridai.suckerreimu.battle

import net.aridai.suckerreimu.battle.anim.BattleAnim

/**
 * 対戦シーンの台本
 */
internal object BattleScenario {

    /**
     * 初期状態を生成する。
     */
    fun createInitialState(): BattleSceneState {
        //  TODO: 対戦シーン開始時の会話などの実装
        val animQueue = listOf(
            BattleAnim.Message("テストメッセージ"),
        )
        return BattleSceneState.PlayingAnimations(animQueue)
    }
}
