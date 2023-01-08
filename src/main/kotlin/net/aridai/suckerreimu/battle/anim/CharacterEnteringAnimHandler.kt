package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタ入場アニメのアニメーションハンドラ
 */
internal object CharacterEnteringAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterEnteringAnim)

        //  指定されたキャラクタの入場アニメーションを再生する。
        when (anim.side) {
            CharacterSide.OWN -> components.reimu.enter(anim.durationInMs)
            CharacterSide.OPPONENT -> components.marisa.enter(anim.durationInMs)
        }
    }
}
