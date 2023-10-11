import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int answer = 0;
    public static int turn = 1;
    public static void main(String[] args) {
        System.out.println("지금부터 게임을 진행합니다.");

        // 플레이어 입력
        String player_01 = InputName("첫번째");
        String player_02 = InputName("두번째");

        // 유저 숫자 입력 받기
        int sumPlayer1 = getPlayerInputNumber(player_01);
        int sumPlayer2 = getPlayerInputNumber(player_02);
        // 처음 정답
        answer = sumPlayer1 + sumPlayer2;

        System.out.println("\n번호 저장 완료.");

        System.out.println("\n자, 숫자는 제공되었고, 이제는 입력된 숫자가 모두 더해졌습니다.");
        System.out.println("\n우리는 그 답을 맞춰보도록 하겠습니다. 최대한 빠른 사람이 승리입니다.");

        System.out.printf("\n%s님, %s님 반갑습니다.\n", player_01, player_02);

        // 게임 시작
        System.out.println("\n===========================컴퓨터가 알려주는 지원=========================\n1.플레이어가 제시한 숫자보다 크거나 작을 시 컴퓨터가 알려줍니다.그럼 게임을 시작하겠습니다.\n");
        System.out.printf("%s님부터 시작합니다.\n", player_01);

        String currentPlayer = player_01;
        // 정답판별 변수
        boolean isMultipleHint = false;

        while (true) {
            // 5, 7의 배수 일때 정답 변경
            if(turn%5==0 && turn%7==0){
                answer+=640;
                isMultipleHint=true;
            } else if (turn%5==0) {
                answer+=1200;
                isMultipleHint=true;
            } else if (turn%7==0) {
                answer-=560;
                isMultipleHint=true;
            }

            if (baseRule(currentPlayer, answer) && isMultipleHint) {
                // 5와 7의 배수 힌트 제공
                break;
            } else if(baseRule(currentPlayer, answer)) {
                break;
            }
            turn++;
            currentPlayer = (currentPlayer.equals(player_01)) ? player_02 : player_01;
        }
    }

    public static String InputName(String order){
        System.out.print(order + " 번째 플레이어를 입력하세요 : ");
        String user_answer = sc.next();
        return user_answer;
    }
    // 유저에게 2개의 숫자 입력 받기
    public static int getPlayerInputNumber(String player){
        System.out.printf("%s님이 첫 번째 번호를 입력하세요: ", player);
        int firstInput = sc.nextInt();
        System.out.printf("%s님이 두 번째 번호를 입력하세요: ", player);
        int secondInput = sc.nextInt();

        return firstInput + secondInput;
    }

    // 정답판별
    public static boolean baseRule(String player, int answer) {
        System.out.printf("%s님이 입력할 번호 : ", player);
        int inputNumber = sc.nextInt();
        if (answer < inputNumber) {
            System.out.printf("%s님이 입력하신 숫자가 답보다 작습니다.\n", player);
            return false;
        } else if (answer > inputNumber) {
            System.out.printf("%s님이 입력하신 숫자보다 큽니다.\n", player);
            return false;
        } else {
            System.out.printf("정답입니다 !! 완벽하십니다!! 멋지세요!!\n%s님 승리!!", player);
            return true;
        }
    }

}
