package net.aridai.suckerreimu.battle

import net.aridai.suckerreimu.battle.anim.BattleAnim

/**
 * 対戦シーン状態
 */
internal sealed interface BattleSceneState {

    /**
     * アニメーション再生中
     */
    data class PlayingAnimations(val animQueue: List<BattleAnim>) : BattleSceneState

    /**
     * 技選択中
     */
    object SelectingMoves : BattleSceneState
}
