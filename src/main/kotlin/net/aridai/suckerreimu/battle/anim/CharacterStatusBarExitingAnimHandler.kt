package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタステータスバー退出アニメのアニメーションハンドラ
 */
internal object CharacterStatusBarExitingAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterStatusBarExitingAnim)

        when (anim.side) {

            //  自分側 (れーむさん)
            CharacterSide.OWN -> components.reimuStatusBar.hideStatusBar(anim.durationInMs)

            //  相手側 (まりさちゃん)
            CharacterSide.OPPONENT -> components.marisaStatusBar.hideStatusBar(anim.durationInMs)
        }
    }
}
