
class Card{
    private val symbol : String
    private val value: Int

    constructor(symbol:String="X",value:Int=0){
        this.symbol = symbol
        this.value = value
    }
    fun getSymbol(): String{
        return this.symbol
    }
    fun getValue(): Int{
        return this.value
    }
}