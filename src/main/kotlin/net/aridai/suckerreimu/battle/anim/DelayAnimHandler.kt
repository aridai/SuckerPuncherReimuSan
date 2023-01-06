package net.aridai.suckerreimu.battle.anim

import kotlinx.coroutines.delay
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * 遅延のアニメーションハンドラ
 */
internal object DelayAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.Delay)

        //  指定期間だけ遅延させる。
        delay(anim.delayInMs)
    }
}
