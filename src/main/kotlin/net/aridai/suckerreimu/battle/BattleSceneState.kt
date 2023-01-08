package net.aridai.suckerreimu.battle

import net.aridai.suckerreimu.battle.anim.BattleAnim

/**
 * 対戦シーン状態
 */
internal sealed interface BattleSceneState {

    /**
     * 対戦ターン
     */
    val turn: BattleTurn

    /**
     * アニメーション再生中
     */
    data class PlayingAnimations(override val turn: BattleTurn, val animQueue: List<BattleAnim>) : BattleSceneState

    /**
     * 技選択中
     */
    data class SelectingMoves(override val turn: BattleTurn.PlayableTurn) : BattleSceneState
}
