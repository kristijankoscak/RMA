class Scala:Rules {
    private var isScala:Boolean
    private var dicesValue = mutableListOf<Int>()
    constructor(){
        this.isScala = false
    }
    override fun checkRule(dices: List<Dice>):Boolean {
        getValues((dices))
        checkResult()
        return this.isScala
    }
    override fun checkResult() {
        this.isScala = ( getScalaStatus(1) || getScalaStatus(2))
        if(this.isScala) println("Scala!!")
    }
    private fun getValues(dices: List<Dice>){
        this.dicesValue = mutableListOf<Int>()
        for (dice in dices) {
            this.dicesValue.add(dice.valueRepresentation)
        }
    }
    private fun getScalaStatus(startValue:Int):Boolean{
        var tempStatus :Boolean =true
        for(x in startValue until startValue+5){
            if(this.dicesValue.contains(x) && tempStatus){
                tempStatus = true
            }
            else tempStatus = false
        }
        return tempStatus
    }
}