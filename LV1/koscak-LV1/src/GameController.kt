import kotlin.random.Random

class GameController{
    private var onMove : Boolean
    private var isOver: Boolean
    private val player1 : Player
    private val player2 : Player

    constructor(name:String){
        player1 = Player("computer")     // on move if - onMove = false
        player2 = Player(name)                 // on move if - onMove = true
        onMove = true
        isOver = false
    }
    fun startGame(){
        giveStartCards()
        var playerAtMove : Player
        do {
            playerAtMove = getPlayer(onMove)
            if(playerAtMove.getPlayStatus() ) {
                playMove(playerAtMove)
            }
            else if(player1.getPlayStatus() == false && player2.getPlayStatus() ==false){
                isOver = true
            }
            //  case:   player doesn't want card, computer takes card(sum is under 16), player
            //          isn't allowed anymore, computer takes card(sum is under 16)
            else{
                onMove = !onMove
            }
        } while( isOver != true)

        println("============================================================")
        println(player1.getName()+" result: "+player1.getSum())
        println(player2.getName()+" result: "+player2.getSum())
        announceWinner()
        println("============================================================")
        println("Do you want a play again?[Y/N]")
        if(processAnswer(readLine().toString().toUpperCase())){
            player1.resetValues()
            player2.resetValues()
            onMove = true
            isOver = false
            startGame()
        }
        else println("The end of program!")
    }
    private fun decide(playerAtMove: Player,state: Boolean){
        //  player wants the card
        if (state) {
            playerAtMove.addCard(getCard())
            sumHand(playerAtMove)
            if(playerAtMove.getName()!="computer") println("Your card: "+playerAtMove.getHand().last().getSymbol())
            isOver21(playerAtMove)
        }
        // player doesn't want the card
        else{
            playerAtMove.forbidPlaying()
        }
        onMove = !onMove
    }
    private fun playMove(playerAtMove: Player){
        if(playerAtMove.getName()!="computer"){
            showCards(playerAtMove)
            println(playerAtMove.getName()+" ,do you want a card? [Y/N]")
            decide(playerAtMove,processAnswer(readLine().toString().toUpperCase()))
        }
        else{
            decide(playerAtMove,playerAtMove.getSum()<=16)
        }
    }

    private fun showCards(playerAtMove: Player){
        print("Your hand: ")
        for(card in playerAtMove.getHand()){
            print(card.getSymbol()+" ")
        }
        println()
        println("Your sum: "+playerAtMove.getSum())
    }
    private fun announceWinner(){
        if(player1.getSum() > player2.getSum() && player1.getSum()<22)
            println("Winner is: "+ player1.getName())
        else if(player2.getSum()> player1.getSum() && player2.getSum()<22)
            println("Winner is: "+ player2.getName())
        else if(player2.getSum()> 21)
            println("Winner is: "+ player1.getName())
        else if(player1.getSum()> 21)
            println("Winner is: "+ player2.getName())
        else println("It's draw!!")
    }
    private fun giveStartCards(){
        for(x in 0..1){
            player1.addCard(getCard())
            sumHand(player1)
            player2.addCard(getCard())
            sumHand(player2)
        }
    }
    private fun getPlayer(identifier :Boolean):Player{
        if(identifier) return player2
        else return player1
    }
    private fun processAnswer(isplaying:String):Boolean{
        if( isplaying == "Y") { return true }
        return false
    }
    private fun sumHand(playerAtMove:Player){
        if(playerAtMove.getName()=="computer") {
            val lastCardSymbol = playerAtMove.getHand().last().getSymbol()
            if(lastCardSymbol == "A" && (playerAtMove.getSum()+ 11) > 21){
                playerAtMove.addSum(1)
            }
            else{
                playerAtMove.addSum(playerAtMove.getHand().last().getValue())
            }
        }
        else{
            playerAtMove.addSum(playerAtMove.getHand().last().getValue())
        }
    }
    private fun isOver21(playerAtMove: Player){
        if(playerAtMove.getSum() > 21){
            isOver = true
        }
    }
    private fun getCard():Card{
        var randomValue : Int = Random.nextInt(2,15)
        return when(randomValue){
            2->  Card("2",2)
            3->  Card("3",3)
            4->  Card("4",4)
            5->  Card("5",5)
            6->  Card("6",6)
            7->  Card("7",7)
            8->  Card("8",8)
            9->  Card("9",9)
            10->  Card("10",10)
            11->  Card("A",11)
            12->  Card("K",10)
            13->  Card("Q",10)
            14->  Card("J",10)
            else -> Card()
        }
    }
}