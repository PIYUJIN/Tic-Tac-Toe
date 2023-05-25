import java.lang.Exception
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
    printBoard()
    play.inputNumber()
}

val size = 3
//보드판 생성 : board[][]
var board = Array(size) { CharArray(size) }

// board 값 초기화
fun initialBorad() {
    for (i in 0..2) {
        for (j in 0..2) {
            board[i][j] = ' '
        }
    }
}

// board 프린트
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
//        println(board[row][col])
        if (board[row][col] == ' ') {
            return true
        } else {
            println("해당 칸은 선택이 불가합니다. 다시 입력해주세요.")
            println()
            printBoard()
            player_num--
            return false
        }
    }

    // 게임이 끝났는지 확인
    fun isFinish(player : Int) : Boolean {
        var mark = ' '
        if (player==1) {
            mark = 'O'
        }
        else {
            mark = 'X'
        }
        for (i in 0 until size) {
            // 세로줄 확인
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)
                return true
            // 가로줄 확인
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark)
                return true
        }
        // 대각선 확인
        if(board[0][0] == mark && board[1][1] == mark && board[2][2] == mark)
            return true
        if(board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)
            return true

        return false
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
        var first_num = 0
        var second_num = 0
        while (true) {
            var player = if ((player_num%2)==1) 1 else 2
            // 사용자의 줄, 칸 입력
            // 입력 숫자의 유효성 검사
            try {
                print("Player${player} 입력 (줄,칸) : ")
                var inputNum = scan.next()
                // 입력된 number를 row,column로 ("," 기준) split
                var num_split: List<String> = inputNum.split(",")
                first_num = num_split[0].toInt()
                second_num = num_split[1].toInt()
//            println(num_split[0])
//            println(num_split[1])
                println()
            } catch(e: Exception) {
                println(e.printStackTrace())
                println("잘못입력하였습니다. 다시 입력해주세요.")
                continue
            }
            player_num++
            // 입력된 숫자의 범위 확인
            if(first_num>=0 && first_num<size && second_num>=0 && second_num<size) {
                print("")
            }
            else {
                println("입력된 숫자가 범위를 넘어갔습니다. 다시 입력해주세요.")
                player_num--
                println()
                printBoard()
                continue
            }
            // 해당 칸에 입력 가능한지 확인 후 boolean type 값 반환
            var empty_space = isEmpty(first_num, second_num)
//            println(empty_sapce)
            // empty space가 있는 경우 해당 칸 해당 유저의 mark(O or X)로 변경
            if(empty_space) {
                setBoard(player, first_num, second_num)
            }
            else {
                continue
            }
            printBoard()
            // 게임이 끝났는지 확인 후 boolean type 값 반환
            if(isFinish(player)) {
                println("Player$player 승리!")
                break
            }
            else {
                continue
            }
        }
    }
}
