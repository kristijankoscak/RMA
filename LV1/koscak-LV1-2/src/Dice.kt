class Dice {
    private var value:Int
    private var isLocked:Boolean
    constructor(){
        this.value = 0
        this.isLocked = false
    }
    var valueRepresentation: Int
        get() = this.value
        set(value) { this.value = value }

    var stateRepresentation: Boolean
        get() = this.isLocked
        set(value) { this.isLocked = value }
}