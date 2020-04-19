import kotlin.random.Random

class GameController {
    private var dices = mutableListOf<Dice>()
    private var isOver: Boolean
    private var jambpoker: SameDices
    private var scala : Scala

    constructor(){

        this.isOver = false
        this.jambpoker = SameDices()
        this.scala = Scala()
    }

    fun startGame(){
        initializeDices()
        do {

            println("Do you want throw dices?[Y/N]")
            if (processAnswer(readLine().toString().toUpperCase())){
                makeMove()
            }
            else {
                this.isOver = true
            }
        }while(!isOver)

        println("Do you want play again?[Y/N]")
        if (processAnswer(readLine().toString().toUpperCase())){
            this.isOver = false
            this.dices = mutableListOf<Dice>()
            this.jambpoker = SameDices()
            this.scala = Scala()
            startGame()
        }
        else{
            println("The end of program!")
        }
    }
    private fun makeMove(){
        throwDices()
        showDices()
        if(this.scala.checkRule(this.dices)||jambpoker.checkRule(this.dices)){
            this.isOver = true
        }
        else{
            continueMove()
        }
    }
    private fun continueMove(){
        println("How many dices you want to lock?(0 ,if you don't want)")
        var dicesForLock = readLine().toString()
        for(x in 0 until dicesForLock.toInt()){
            println("Enter dice index from left to right which you want to lock(1-6):")
            var diceIndex = readLine().toString()
            this.dices[diceIndex.toInt()-1].stateRepresentation = true
        }
    }
    private fun processAnswer(answer:String):Boolean{
        if(answer=="Y") return true
        return false
    }
    private fun initializeDices(){
        for(x in 0..5){
            this.dices.add(Dice())
        }
    }
    private fun throwDices(){
        for(dice in dices){
            if(!dice.stateRepresentation)
                dice.valueRepresentation = generateNumber()
        }
    }
    private fun showDices(){
        println("your dices: ")
        for(dice in dices){
            if(dice.stateRepresentation){
                print(" ["+dice.valueRepresentation+"] ")
            }
            else{
                print(" "+dice.valueRepresentation+" ")
            }
        }
        println()
    }
    private fun generateNumber():Int{
        return Random.nextInt(1,7)
    }
}