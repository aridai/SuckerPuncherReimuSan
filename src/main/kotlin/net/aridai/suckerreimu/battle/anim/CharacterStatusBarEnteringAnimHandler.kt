package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタステータスバー入場アニメのアニメーションハンドラ
 */
internal object CharacterStatusBarEnteringAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterStatusBarEnteringAnim)
        val (side, name, currentHp, maxHp, duration) = anim

        when (side) {

            //  自分側 (れーむさん)
            CharacterSide.OWN -> components.reimuStatusBar.showStatusBar(name, currentHp, maxHp, duration)

            //  相手側 (まりさちゃん)
            CharacterSide.OPPONENT -> components.marisaStatusBar.showStatusBar(name, currentHp, maxHp, duration)
        }
    }
}
