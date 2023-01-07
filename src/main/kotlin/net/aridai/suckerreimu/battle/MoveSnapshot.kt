package net.aridai.suckerreimu.battle

/**
 * 技データのスナップショット
 */
internal data class MoveSnapshot(

    /**
     * 技名
     */
    val moveName: String,

    /**
     * 残りPP
     */
    val remainingPp: Int,

    /**
     * 最大PP
     */
    val maxPp: Int
)
