package net.aridai.suckerreimu.battle.anim

import net.aridai.suckerreimu.battle.CharacterSide
import net.aridai.suckerreimu.battle.components.BattleSceneComponents

/**
 * キャラクタステータスバーHP更新アニメのアニメーションハンドラ
 */
internal object CharacterStatusBarHpUpdatingAnimHandler : BattleAnimHandler {

    override suspend fun handle(anim: BattleAnim, components: BattleSceneComponents) {
        require(anim is BattleAnim.CharacterStatusBarHpUpdatingAnim)

        //  指定されたキャラクタのHP更新アニメーションを再生する。
        when (anim.side) {
            CharacterSide.OWN -> components.reimuStatusBar.updateCurrentHp(anim.updatedHp, anim.durationInMs)
            CharacterSide.OPPONENT -> components.marisaStatusBar.updateCurrentHp(anim.updatedHp, anim.durationInMs)
        }
    }
}
