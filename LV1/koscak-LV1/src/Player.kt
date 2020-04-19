class Player{
    private val name: String
    private var sum: Int
    private var canPlay: Boolean
    private var hand =  mutableListOf<Card>()

    constructor(name:String){
        this.name = name
        this.sum = 0
        this.canPlay = true
    }
    fun getPlayStatus():Boolean{
        return this.canPlay
    }
    fun forbidPlaying(){
        this.canPlay = false
    }
    fun getName():String{
        return this.name
    }
    fun getSum(): Int{
        return this.sum
    }
    fun addSum(value:Int){
        this.sum +=value
    }
    fun resetValues(){
        this.sum = 0
        this.hand = mutableListOf<Card>()
        this.canPlay = true
    }
    fun addCard(card:Card){
        this.hand.add(card)

    }
    fun getHand(): List<Card>{
        return this.hand
    }
}