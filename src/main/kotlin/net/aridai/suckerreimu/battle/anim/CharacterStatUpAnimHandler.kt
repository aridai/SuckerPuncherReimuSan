package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタ能力上昇アニメのアニメーションハンドラ
 */
internal object CharacterStatUpAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterStatUpAnim)

        //  指定されたキャラクタの能力上昇アニメーションを再生する。
        when (anim.side) {
            CharacterSide.OWN -> components.reimu.playStatUpEffect(anim.durationInMs)
            CharacterSide.OPPONENT -> components.marisa.playStatUpEffect(anim.durationInMs)
        }
    }
}
