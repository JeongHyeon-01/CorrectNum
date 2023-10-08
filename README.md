# CorrectNum

1. 프로젝트 개요
    - 두명의 플레이어가 각각 입력한 숫자의 합을 맞추는 게임
2. 기능 요구사항
    - 기본 진행
        - 플레이어 2명 입력
        - 각각의 플레이어는 2개의 숫자 입력
        - 각각의 플레이어의 입력한 값의 합이 정답
        - 서로 한턴씩 정답을 입력 진행
        - 정답 입력시 크다, 작다 문구 출력
        - 정답시 정답입니다, 해당 플레이어 승리 문구 출력
    - 턴의 배수에 따른 정답 변경
        - 5의 배수 번째 정답을 입력한 경우 오답으로 표출 정답+=1200
        - 7의 배수 번째 정답을 입력한 경우 오답으로 표출 정답-=560
        - 8의 배수 번째 이후에도 정답이 나오지 않을 시 정답의 각 자리수 합을 힌트로 제공한다.
            - 예시) 정답이 2500일 경우 7을 제공
        - 10턴의 배수 턴까지 정답이 나오지 않을 경우 정답의 자리수를 힌트로 제공한다.
            - 예시) 정답이 450일 경우 3자리 수로 힌트가 제공
    - 힌트 제공 규칙
        - 힌트 - 위 조건 이후 플레이어 응답과 정답의 오차범위가 +- 10 인경우 타음턴에 아래 힌트를 제공
        - 5의 배수 7의 배수에 못 맞출경우 힌트 제공(힌트는 처음 힌트1,2번 선택할수 있게 진행)
            - 1번 힌트 : 20이상의 약수 제공
            - 2번 힌트 : 최근 5턴 동안 입력한 숫자중 정답 유무 출력
            - 2번째 힌트에서 못풀경우 1번 약수 수에 따른 변경
                - 1개  정답+=100
                - 3개 정답+=360
                - 5개 정답+=1700
    - 페널티 - 힌트가 나온턴이후에 정답을 못 맞춘 경우-(힌트턴 이후 적용)
        - 힌트 1번에서 알려준 약수 개수가 3개 미만이면 +1
        - 2번에서 5번 턴중 정답이 있었으면 +3
        - 3번에서 나온 값을 포함하여 정답이 2000 이상이면 +7
        - 3번에서 나온값이 2000미만이면 -9
        - 기본 제공은 +3
