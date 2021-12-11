package minesweeper.domain.board

private const val ZERO = 0

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > ZERO) { "Height는 양의 정수여야합니다." }
    }
}

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > ZERO) { "Width는 양의 정수여야합니다." }
    }
}

