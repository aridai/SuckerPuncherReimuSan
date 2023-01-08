package net.aridai.suckerreimu.battle

import kotlinx.coroutines.Job
import net.aridai.suckerreimu.Logger
import net.aridai.suckerreimu.TouchEvent
import net.aridai.suckerreimu.battle.anim.BattleAnimHandler
import net.aridai.suckerreimu.battle.components.BattleSceneComponents
import net.aridai.suckerreimu.launchCoroutine
import org.w3c.dom.CanvasRenderingContext2D

/**
 * 対戦シーン
 */
internal class BattleScene {

    //  対戦シーンの状態管理ロジック
    private lateinit var manager: BattleStateManager

    //  対戦シーンのコンポーネント群
    private lateinit var components: BattleSceneComponents

    //  対戦シーンの状態の購読
    private lateinit var stateSubscription: Job

    /**
     * 初期化処理を行う。
     */
    fun init() {
        this.manager = BattleStateManager()
        this.components = BattleSceneComponents()

        //  対戦シーンの状態変更を購読する。
        this.stateSubscription = launchCoroutine { this.manager.state.collect(this::onBattleStateChanged) }
    }

    /**
     * 描画処理を行う。
     */
    fun render(context: CanvasRenderingContext2D) {
        this.components.render(context)
    }

    /**
     * タッチイベントが発生したとき。
     */
    fun onTouchEventOccurred(event: TouchEvent) {
        this.components.onTouchEventOccurred(event)
    }

    /**
     * 対戦状態が変化したとき。
     */
    private suspend fun onBattleStateChanged(state: BattleSceneState) {
        Logger.d { "対戦シーン 状態変化: $state" }

        val result = when (state) {

            //  アニメーション再生中
            is BattleSceneState.PlayingAnimations -> {
                //  アニメーションを順に消費していく。
                for (anim in state.animQueue) {
                    BattleAnimHandler.of(anim).handle(anim, this.components)
                }

                BattleSceneStateResult.PlayingAnimations(state)
            }

            //  技選択中
            is BattleSceneState.SelectingMoves -> {

                //  ユーザの技選択を待機する。
                this.components.reimu.startStandbyAnim()
                val moves = state.status.reimuMoves
                val selectedMove = this.components.moveMenu.showMoveMenu(moves)
                this.components.reimu.stopStandbyAnim()

                BattleSceneStateResult.SelectingMoves(state, selectedMove.moveKind)
            }
        }

        //  状態の終了を通知する。
        this.manager.onStateEnded(result)
    }
}
