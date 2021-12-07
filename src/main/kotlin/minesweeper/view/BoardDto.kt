package minesweeper.view

import minesweeper.domain.BlockCell
import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.MineCell

data class BoardDto(val rows: List<RowDto>) {

    constructor(board: Board) : this(board.toRows())
}

data class RowDto(val cells: List<CellDto>)

fun RowDto(cells: List<Cell>): RowDto {
    return RowDto(cells.map(::CellDto))
}

data class CellDto(val mine: Boolean, val aroundMineCount: Int?) {

    constructor(cell: Cell) : this(cell.isMine(), cell.getAroundMineCount())
}

private fun Board.toRows(): List<RowDto> {
    val cellsSortedByPosition = cells.toSortedMap()
        .toList()
    val cellsGroupByRow = cellsSortedByPosition
        .groupBy(keySelector = { (position, _) -> position.row }, valueTransform = { (_, cell) -> cell })
        .toSortedMap()
        .values
    return cellsGroupByRow.map { RowDto(it) }
}

private fun Cell.getAroundMineCount(): Int? {
    return when(this) {
        is MineCell -> null
        is BlockCell -> aroundMineCount.value
    }
}
