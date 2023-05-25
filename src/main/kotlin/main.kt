import java.util.Scanner

// player 1과 player 2가 번갈아가면서 게임 진행
// 한 칸 선택 (X,Y 좌표 이용)
// 가로, 세로, 대각선으로 같은 player가 놓는 경우 승리!
// 이미 한번 선택된 칸이라면 선택 불가

// 1. 게임판 생성
// 2. 숫자 입력시 표시 및 순서 이동 (O, X로 구분)
// 3. 게임판에 가로, 세로, 대각선 중 1개 라인 다 채웠는지 확인
// 4-1. 게임판에 가로, 세로, 대각선 중 1개 라인 다 채워지지 않았을 시 다음 player로 순서 이동
// 4-2. 게임판에 가로, 세로, 대각선 중 1개 라인 다 채워졌을 시 해당 player 승



fun main() {
    var play = Play()
//    initialBorad()
//    println()
    printBoard()
    play.inputNumber()
}

val size = 3
//보드판 생성 : board[][]
var board = Array(size) { CharArray(size) }

fun initialBorad() {
    // board 값 초기화
    for (i in 0..2) {
        for (j in 0..2) {
            board[i][j] = ' '
        }
    }

//    // board 값 프린트
//    for (i in 0..2) {
//        println()
//        for (j in 0..2) {
//            print(board[i][j])
//        }
//    }

}

fun printBoard() {

    // 보드판 가로 숫자 표시 줄
    print(" ")
    for (firstRow in 0 until size) {
        print(" $firstRow")
    }

    // 3*3 보드판 만들기
    for (i in 0 until size)
    {
        // 보드판 세로 숫자 표시 줄
        println()
        print("$i ")
        for (j in 0 until size) {
            if(j==size-1) {
                println(board[i][j])
            }
            else {
                print(board[i][j])
                print("|")
            }
        }
        if(i==size-1) {
            print("")
        }
        else {
            print("  -+-+-")
        }
    }
}

// 게임 진행
class Play {

    // play 시작시 board 초기화
    init {
        initialBorad()
    }
    var player_num = 1

    //해당 칸이 입력 가능한지 확인
    // 입력 가능 : true, 입력 불가능 : false
    fun isEmpty(row: Int, col: Int): Boolean {
        println(board[row][col])
        if (board[row][col] == ' ') {
            return true
        } else {
            println("해당 칸은 선택이 불가합니다.")
            player_num--
            return false
        }
    }

    //입력된 board 칸을 해당 player의 표시로 변경
    fun setBoard(player:Int, row:Int, col:Int) {
        if(player==1) {
            board[row][col] = 'O'
        }
        else {
            board[row][col] = 'X'
        }
    }

    //숫자 입력받아 게임 진행
    fun inputNumber() {
        var scan = Scanner(System.`in`)
        while (true) {
            var player = player_num%2
            print("Player${player} 입력 (줄,칸) : ")
            var inputNum = scan.next()
            // 입력된 number를 row,column로 ("," 기준) split
            var num_split: List<String> = inputNum.split(",")
            var first_num = num_split[0].toInt()
            var second_num = num_split[1].toInt()
            println(num_split[0])
            println(num_split[1])
            player_num++
            // 해당 칸에 입력 가능한지 확인 후 boolean type 값 반환
            var empty_sapce = isEmpty(first_num, second_num)
//            println(empty_sapce)
            if(empty_sapce) {
                setBoard(player, first_num, second_num)
            }
            printBoard()
        }
    }
}
