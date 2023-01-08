package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタ能力下降アニメのアニメーションハンドラ
 */
internal object CharacterStatDownAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterStatDownAnim)

        //  指定されたキャラクタの能力下降アニメーションを再生する。
        when (anim.side) {
            CharacterSide.OWN -> components.reimu.playStatDownEffect(anim.durationInMs)
            CharacterSide.OPPONENT -> components.marisa.playStatDownEffect(anim.durationInMs)
        }
    }
}
