package net.aridai.suckerreimu.battle

import net.aridai.suckerreimu.battle.anim.BattleAnim

/**
 * 対戦シーンの台本
 */
internal object BattleScenario {

    //  まりさちゃんの最大HP実数値
    //  (種族値140・個体値31・努力値0)
    private const val MARISA_MAX_HP: Int = 215

    //  れーむさんの最大HP実数値
    //  (種族値150・個体値31・努力値236)
    private const val REIMU_MAX_HP: Int = 255

    //  マスタースパークのダメージ量 (れーむさん ← まりさちゃん)
    //  (威力180フェアリータイプ一致・特性まほうつかいの威力2倍補正対象技・れーむさんに半減)
    //  (まりさちゃん特攻: 種族値180・個体値31・努力値252・性格補正なし・実数値232)
    //  (れーむさん特防: 種族値130・個体値31・努力値0・性格補正なし・実数値140)
    private const val DAMAGE_AMOUNT_OF_MASTER_SPARK_FROM_MARISA_TO_REIMU: Int = 198

    //  むそうふういんのダメージ量 (まりさちゃん ← れーむさん)
    //  (威力180炎タイプ一致・攻撃1段階上昇・まりさちゃんに半減)
    //  (れーむさん攻撃: 種族値180・個体値31・努力値252・性格補正あり・実数値255・ランク+1・ランク込み実数値382)
    //  (まりさちゃん防御: 種族値100・個体値31・努力値0・性格補正なし・実数値120・ランク-2・ランク込み実数値60)
    //  (実ダメージ378・きあいのタスキ耐え)
    private const val DAMAGE_AMOUNT_OF_FANTASY_SEAL_FROM_REIMU_TO_MARISA: Int = MARISA_MAX_HP - 1

    /**
     * 初期状態を生成する。
     */
    fun createInitialState(): BattleSceneState {
        val animQueue = listOf(
            //  背景のフェードイン
            BattleAnim.BackgroundFadeInAnim(),

            //  まりさちゃんの入場+メッセージ
            BattleAnim.Parallel(
                animations = listOf(
                    BattleAnim.CharacterEnteringAnim(side = CharacterSide.OPPONENT),
                    BattleAnim.Message(text = "まりさちゃんが勝負をしかけてきた!"),
                ),
            ),

            //  れーむさんの入場+メッセージ
            BattleAnim.Parallel(
                animations = listOf(
                    BattleAnim.CharacterEnteringAnim(side = CharacterSide.OWN),
                    BattleAnim.Message(text = "いざ勝負!"),
                ),
            ),

            //  お互いのステータスバー表示
            BattleAnim.Parallel(
                animations = listOf(
                    BattleAnim.CharacterStatusBarEnteringAnim(
                        side = CharacterSide.OWN,
                        name = "れーむさん",
                        currentHp = REIMU_MAX_HP,
                        maxHp = REIMU_MAX_HP,
                    ),
                    BattleAnim.CharacterStatusBarEnteringAnim(
                        side = CharacterSide.OPPONENT,
                        name = "まりさちゃん",
                        currentHp = MARISA_MAX_HP,
                        maxHp = MARISA_MAX_HP,
                    ),
                ),
            ),
        )

        val turn = BattleTurn.InitialState
        return BattleSceneState.PlayingAnimations(turn, animQueue)
    }

    /**
     * 最初のターン (自動操作) の状態を生成する。
     */
    fun createFirstTurnState(): BattleSceneState {
        val abilityStatAnimDuration = BattleAnim.CharacterStatDownAnim.DEFAULT_DURATION_IN_MS
        val abilityMessageDuration = BattleAnim.Message.DEFAULT_DURATION_IN_MS
        val abilityTotalDuration = abilityStatAnimDuration * 2 + abilityMessageDuration * 2

        val animQueue = listOf(
            BattleAnim.Delay(delayInMs = 500L),

            //  まりさちゃんの攻撃
            BattleAnim.Message(text = "まりさちゃんのマスタースパーク!"),

            //  れーむさんのダメージ
            BattleAnim.CharacterDamageAnim(side = CharacterSide.OWN),
            BattleAnim.CharacterStatusBarHpUpdatingAnim(
                side = CharacterSide.OWN,
                updatedHp = REIMU_MAX_HP - DAMAGE_AMOUNT_OF_MASTER_SPARK_FROM_MARISA_TO_REIMU,
            ),

            //  タイプ相性メッセージ
            BattleAnim.Message(text = "効果はいまひとつのようだ..."),

            //  まりさちゃんの技の追加効果
            BattleAnim.CharacterStatDownAnim(side = CharacterSide.OPPONENT),
            BattleAnim.Message(text = "まりさちゃんの防御と特防ががくっと下がった!"),

            //  れーむさんの特性発動
            BattleAnim.Parallel(
                animations = listOf(
                    BattleAnim.AbilityPopupAnim(
                        side = CharacterSide.OWN,
                        abilityName = "ぶちぎれいむ",
                        durationInMs = abilityTotalDuration,
                    ),
                    BattleAnim.Serial(
                        animations = listOf(
                            BattleAnim.CharacterStatDownAnim(
                                side = CharacterSide.OWN,
                                durationInMs = abilityStatAnimDuration,
                            ),
                            BattleAnim.Message(
                                text = "れーむさんの特攻が下がった!",
                                durationInMs = abilityMessageDuration,
                            ),
                            BattleAnim.CharacterStatUpAnim(
                                side = CharacterSide.OWN,
                                durationInMs = abilityStatAnimDuration,
                            ),
                            BattleAnim.Message(
                                text = "れーむさんの攻撃が上がった!",
                                durationInMs = abilityMessageDuration,
                            ),
                        ),
                    ),
                ),
            ),

            //  れーむさんの攻撃
            BattleAnim.Message(text = "れーむさんのむそうふういん!"),

            //  まりさちゃんのダメージ
            BattleAnim.CharacterDamageAnim(side = CharacterSide.OPPONENT),
            BattleAnim.CharacterStatusBarHpUpdatingAnim(
                side = CharacterSide.OPPONENT,
                updatedHp = MARISA_MAX_HP - DAMAGE_AMOUNT_OF_FANTASY_SEAL_FROM_REIMU_TO_MARISA,
            ),

            //  タイプ相性メッセージ
            BattleAnim.Message(text = "効果はいまひとつのようだ..."),

            //  れーむさんの技の追加効果
            BattleAnim.CharacterStatDownAnim(side = CharacterSide.OWN),
            BattleAnim.Message(text = "れーむさんの防御と特防ががくっと下がった!"),

            //  まりさちゃんのきあいのタスキ発動
            BattleAnim.CharacterHeldItemAnim(side = CharacterSide.OPPONENT),
            BattleAnim.Message(text = "まりさちゃんはきあいのタスキでもちこたえた!"),
        )

        val turn = BattleTurn.FirstTurn
        return BattleSceneState.PlayingAnimations(turn, animQueue)
    }
}
