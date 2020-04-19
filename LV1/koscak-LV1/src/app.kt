import kotlin.random.Random
fun main(){
    println("Welcome to Blackjack game! Please enter your name:")
    var playerName = readLine()
    var newgame: GameController = GameController(playerName.toString())
    newgame.startGame()
}



