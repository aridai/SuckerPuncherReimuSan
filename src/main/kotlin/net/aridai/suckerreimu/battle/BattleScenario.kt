package net.aridai.suckerreimu.battle

import net.aridai.suckerreimu.battle.anim.BattleAnim

/**
 * 対戦シーンの台本
 */
internal object BattleScenario {

    /**
     * 初期状態を生成する。
     */
    fun createInitialState(): BattleSceneState {
        //  TODO: 対戦シーン開始時の会話などの実装
        val animQueue = listOf(
            BattleAnim.BackgroundFadeInAnim(),
            BattleAnim.CharacterStatusBarEnteringAnim(
                side = CharacterSide.OPPONENT,
                name = "まりさちゃん",
                currentHp = 64,
                maxHp = 215,
                durationInMs = 0L,
            ),
            BattleAnim.Message("テストメッセージ"),
            BattleAnim.CharacterStatusBarEnteringAnim(
                side = CharacterSide.OWN,
                name = "れーむさん",
                currentHp = 64,
                maxHp = 255,
            ),
            BattleAnim.Parallel(
                animations = listOf(
                    BattleAnim.CharacterEnteringAnim(side = CharacterSide.OWN),
                    BattleAnim.CharacterEnteringAnim(side = CharacterSide.OPPONENT),
                ),
            ),
            BattleAnim.CharacterStatDownAnim(side = CharacterSide.OPPONENT),
            BattleAnim.CharacterStatUpAnim(side = CharacterSide.OPPONENT),
            BattleAnim.CharacterHeldItemAnim(side = CharacterSide.OPPONENT),
            BattleAnim.CharacterDamageAnim(side = CharacterSide.OWN),
            BattleAnim.AbilityPopupAnim(side = CharacterSide.OWN, abilityName = "ぶちぎれいむ"),
            BattleAnim.CharacterExitingAnim(side = CharacterSide.OPPONENT),
        )
        return BattleSceneState.PlayingAnimations(animQueue)
    }
}
