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
                when (val turn = state.turn) {

                    //  初期状態だった場合
                    is BattleTurn.InitialState -> {
                        //  最初のターン (自動操作) のアニメーションを再生させる。
                        this._state.value = BattleScenario.createFirstTurnState()
                    }

                    //  最初のターンだった場合
                    is BattleTurn.FirstTurn -> {
                        //  次のターンの技選択を行わせる。
                        this._state.value = BattleSceneState.SelectingMoves(turn = turn.next)
                    }

                    //  それ以降のターンだった場合
                    is BattleTurn.PlayableTurn -> {
                        //  TODO: 決着判定

                        //  次のターンの技選択を行わせる。
                        this._state.value = BattleSceneState.SelectingMoves(turn = turn.next)
                    }
                }
            }

            //  技選択中状態が終了した場合
            is BattleSceneState.SelectingMoves -> {
                //  TODO: 計算&アニメーション再生
            }
        }
    }
}
