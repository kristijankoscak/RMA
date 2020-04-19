abstract class Rules {
    open abstract fun checkRule(dices: List<Dice>):Boolean
    open abstract fun checkResult()
}
