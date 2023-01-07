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

        when (state) {

            //  アニメーション再生中
            is BattleSceneState.PlayingAnimations -> {
                //  アニメーションを順に消費していく。
                for (anim in state.animQueue) {
                    BattleAnimHandler.of(anim).handle(anim, this.components)
                }
            }

            //  技選択中
            is BattleSceneState.SelectingMoves -> {
                //  TODO: 仮 (選択結果を管理部分に通知)
                Logger.v { "対戦シーン 技選択開始" }
                val selectedMoveIndex = this.components.moveMenu.showMoveMenu(
                    listOf(
                        MoveSnapshot(moveName = "ふいうち", remainingPp = 8, maxPp = 8),
                        MoveSnapshot(moveName = "むそうふういん", remainingPp = 5, maxPp = 5),
                    ),
                )
                Logger.v { "対戦シーン 技選択終了: $selectedMoveIndex" }
            }
        }

        //  状態の終了を通知する。
        this.manager.onStateEnded(state)
    }
}
