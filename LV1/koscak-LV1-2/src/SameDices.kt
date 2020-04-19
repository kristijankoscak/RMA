import java.util.ArrayList

class SameDices : Rules {
    private var numberOfSame = mutableListOf<Int>()
    private var hasSame: Boolean
    constructor() {
        this.numberOfSame = mutableListOf(0,0,0,0,0,0)
        this.hasSame = false
    }
    override fun checkRule(dices: List<Dice>):Boolean {
        this.numberOfSame = mutableListOf(0,0,0,0,0,0)
        for(x in 0..5){
            findSame(x+1,dices)
        }
        checkResult()
        return this.hasSame
    }
    override fun checkResult() {
        if(this.numberOfSame.contains(4)){
            this.hasSame = true
            println("Jamb!!")
        }
        if(this.numberOfSame.contains(5)){
            this.hasSame = true
            println("Poker!!")
        }
    }
    private fun findSame(value:Int,dices: List<Dice>){
        for(dice in dices){
            if(dice.valueRepresentation==value){
                this.numberOfSame[value-1]++
            }
        }
    }
}