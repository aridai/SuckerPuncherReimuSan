package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタ持ち物アニメのアニメーションハンドラ
 */
internal object CharacterHeldItemAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterHeldItemAnim)

        //  指定されたキャラクタの持ち物アニメーションを再生する。
        when (anim.side) {
            CharacterSide.OWN -> components.reimu.playHeldItemEffect(anim.durationInMs)
            CharacterSide.OPPONENT -> components.marisa.playHeldItemEffect(anim.durationInMs)
        }
    }
}
