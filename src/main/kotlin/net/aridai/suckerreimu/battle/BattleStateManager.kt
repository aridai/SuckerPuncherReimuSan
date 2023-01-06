package net.aridai.suckerreimu.battle

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.aridai.suckerreimu.Logger

/**
 * 対戦シーンの状態管理ロジック
 */
internal class BattleStateManager {

    //  対戦シーンの状態
    private val _state = MutableStateFlow(BattleScenario.createInitialState())

    /**
     * 対戦シーンの状態
     */
    val state: StateFlow<BattleSceneState> = this._state.asStateFlow()

    /**
     * 状態が終了したとき。
     */
    fun onStateEnded(state: BattleSceneState) {
        Logger.d { "対戦シーン 状態終了: $state" }

        when (state) {

            //  アニメーション再生中状態が終了した場合
            is BattleSceneState.PlayingAnimations -> {
                //  TODO: 仮
                //  技選択中状態に遷移する。
                val next = BattleSceneState.SelectingMoves
                this._state.value = next
            }

            //  技選択中状態が終了した場合
            is BattleSceneState.SelectingMoves -> {
                //  TODO: 計算&アニメーション再生
            }
        }
    }
}
