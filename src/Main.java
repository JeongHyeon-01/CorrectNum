import java.util.Scanner;
import java.util.Random;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int answer = 0;
    public static int turn = 1;
    public static int penalty_trun =0;

    public static int hintCount = 0;
    public static int wronganswer = 0;
    public static int inputNumber;

    public static int recentGuess1;
    public static int recentGuess2;
    public static int recentGuess3;
    public static int recentGuess4;
    public static int recentGuess5;

    public static void main(String[] args) {
        System.out.println("숫자 게임을 시작합니다.");

        // 플레이어 입력
        String player1 = InputName("첫 번째");
        String player2 = InputName("두 번째");

        // 플레이어 초기 합계 입력
        int sumPlayer1 = getPlayerInputNumber(player1);
        int sumPlayer2 = getPlayerInputNumber(player2);
        answer = sumPlayer1 + sumPlayer2;

        System.out.println("\n초기 숫자 입력이 완료되었습니다.");
        System.out.println("\n이제 정답을 맞춰봅시다. 먼저 정답을 찾는 플레이어가 승리합니다.");

        System.out.printf("\n%s님과 %s님, 환영합니다!\n", player1, player2);

        // 게임 시작
        System.out.println("\n===========================컴퓨터가 알려주는 지원=========================\n1. 플레이어가 제시한 숫자보다 크거나 작을 때 컴퓨터가 알려줍니다. 게임을 시작합니다.\n");
        System.out.printf("%s님부터 시작합니다.\n", player1);

        String currentPlayer = player1;
        boolean isMultipleHint = false;

        while (true) {
            // 5, 7의 배수일 때 정답 변경
            if (turn % 5 == 0 && penalty_trun==0) {
                System.out.println("오답입니다.");
                answer += 1200;
                isMultipleHint = true;
            } else if (turn % 7 == 0 && penalty_trun==0) {
                System.out.println("오답입니다.");
                answer -= 560;
                isMultipleHint = true;
            }
            //추가힌트 8 과 10의 배수
            else if (turn % 8 == 0 && penalty_trun==0) {
                //자리수합
                int sumDigit = calculateSumDigits(answer);
                System.out.println("힌트 : 정답의 각 자리수 합은 " + sumDigit + "입니다.");
            } else if (turn % 10 == 0 && penalty_trun==0) {
                // 각 정답의 자리수 힌트 제공 ex answer = 100 제공 = 3
                int numDigit = countDigits(answer);
                System.out.println("힌트 : 정답은 " + numDigit + " 자리 숫자 입니다.");
            }
            inputNumber = baseRule(currentPlayer, answer);

            if (inputNumber == answer) {
                if (isMultipleHint && wronganswer >= 2) {
                    provideHint(currentPlayer);
                }
                break;
            }

            // 패널티가 있을땐
            if (penalty_trun != 0) {
                penalty_trun -= 1;
            }
            turn++;
            currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
            }
        }


    public static String InputName(String order) {
        System.out.print(order + " 플레이어의 이름을 입력하세요: ");
        String playerName = sc.next();

        return playerName;
    }

    public static int getPlayerInputNumber(String player) {
        System.out.printf("%s님이 첫 번째 숫자를 입력하세요: ", player);
        int firstInput = sc.nextInt();
        System.out.printf("%s님이 두 번째 숫자를 입력하세요: ", player);
        int secondInput = sc.nextInt();
        return firstInput + secondInput;
    }

    public static int baseRule(String player, int answer) {
        System.out.printf("%s님이 정답을 추측하세요: ", player);
        int inputNumber = sc.nextInt();
        // 5개 저장
        if (turn % 5 ==1){
            recentGuess1 = inputNumber;
        } else if (turn % 5 ==2) {
            recentGuess2 = inputNumber;
        } else if (turn % 5 ==3) {
            recentGuess3 = inputNumber;
        } else if (turn % 5 ==4){
            recentGuess4 = inputNumber;
        } else if (turn % 5 == 0) {
            recentGuess5 = inputNumber;
        }

        if (answer < inputNumber) {
            System.out.printf("%s님이 입력하신 숫자가 정답보다 작습니다.\n", player);
            return inputNumber;
        } else if (answer > inputNumber) {
            System.out.printf("%s님이 입력하신 숫자가 정답보다 큽니다.\n", player);
            return inputNumber;
        } else {
            System.out.printf("정답입니다! %s님 승리!\n", player);
            return inputNumber;
        }
    }
    public static void provideHint(String player) {
        hintCount++;

        System.out.printf("%s님에게 힌트를 제공합니다 (힌트 번호 선택: 1 또는 2): ", player);
        int hintChoice = sc.nextInt();

        if (hintChoice == 1) {
            int factor = findFactor(answer);
            penalty_trun = fristHintPenalty(factor, penalty_trun);
            System.out.println("힌트 1: " + factor + " 이상의 약수");
        } else if (hintChoice == 2) {
            System.out.println("힌트 2: 최근 5턴 동안 입력한 숫자 중 정답 여부 출력");
            boolean isAnswerFound = isAnswerFoundInRecentGuesses();
            penalty_trun = secondHintPenalty(penalty_trun, isAnswerFound);
            if (isAnswerFound){
                System.out.println("이전 최근 5턴 중에서 정답이 존재합니다");
            }else {
                System.out.println("이전 최근 5턴 중에서 정답이 존재하지 않습니다.");
            }
        }
    }
    // 패널티 턴이 존재하면 힌트 제공 X
    // 힌트 1번에 대한 패널티 약수 개수로 판별,
    public static int fristHintPenalty(int measure, int penalty_trun){
        if (measure<3) {
            penalty_trun += 1;
        }
        penalty_trun+=3;
        return penalty_trun;
    }
    // 힌트 2번에 대한 패널티
    public static int secondHintPenalty(int penalty_trun, boolean isAnswerFound){
        if(isAnswerFound){
            penalty_trun +=3;
        }
        penalty_trun+=3;
        return penalty_trun;
    }

    // 힌트 3번에 대한 패널티
    public static int thirdHintPenalty(int penalty_trun, int answer){
        if(answer>=2000){
            penalty_trun += 7;
        } else{
            penalty_trun -= 9;

            if(penalty_trun<0){
                penalty_trun=0;
            }
        }
        penalty_trun+=3;
        return penalty_trun;
    }


    public static int findFactor(int number) {
        // 약수
        int count = 0;
        for (int i = 20; i <= number; i++) {
            if (number % i == 0) {
                System.out.println("정답이 " + i + "의 배수 입니다.");
                count++;
            }
        }
        System.out.println("더이상 일치하는 배수가 없습니다.");
        return count;
    }
    public static boolean isAnswerFoundInRecentGuesses(){
        if (recentGuess1 == answer || recentGuess2 ==answer || recentGuess3 ==answer || recentGuess4 ==answer || recentGuess5 ==answer){
            return true;
        }
        return false;
    }
    public static int calculateSumDigits(int number){
        //자리수 합
        int sum = 0;
        while (number > 0){
            int digit = number % 10;
            sum += digit;
            number /= 10;
        }
        return sum;
    }
    public static int countDigits(int number){
        //자리수 계산
        int count = 0;
        while (number >0){
            number /=10;
            count ++;
        }
        return count;
    }
}
