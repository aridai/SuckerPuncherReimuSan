package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタ退出アニメのアニメーションハンドラ
 */
internal object CharacterExitingAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterExitingAnim)

        //  指定されたキャラクタの退出アニメーションを再生する。
        when (anim.side) {
            CharacterSide.OWN -> components.reimu.exit(anim.durationInMs)
            CharacterSide.OPPONENT -> components.marisa.exit(anim.durationInMs)
        }
    }
}
