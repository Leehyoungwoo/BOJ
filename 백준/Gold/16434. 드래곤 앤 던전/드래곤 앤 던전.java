import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static long atk; // 백만까지
    private static Room[] room;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
    }

    private static void init() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(input.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        atk = Long.parseLong(tokenizer.nextToken());
        room = new Room[n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(input.readLine());
            int flag = Integer.parseInt(tokenizer.nextToken());
            long a = Long.parseLong(tokenizer.nextToken());
            long b = Long.parseLong(tokenizer.nextToken());
            long[] arr = new long[]{a, b};
            room[i] = new Room(flag, arr);
        }
    }

    private static void findAnswer() {
        // 최소한의 hp를 구해야하는데 이 HP는 첫 시작 HP
        // HP가 깎일수도 있고 포션을 먹어서 늘어날수도 있음
        // 매방에서 체크하고 증감 후 다음방 이동인가?
        // 규칙
        // 용사의 공격력 HATK만큼 몬스터의 생명력을 깎습니다.
        //몬스터의 생명력이 0 이하이면 전투가 종료되고 용사는 다음 방으로 이동합니다.
        //몬스터의 공격력만큼 용사의 생명력 HCurHP를 깎습니다.
        //용사의 생명력이 0 이하이면 전투가 종료되고 용사는 사망합니다.
        //다시 1부터 진행합니다.
        long answer = 0;
        long left = 1L;
        long right = 9_000_000_000_000_000_000L;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (canClear(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canClear(long initHp) {
        long curHp = initHp;
        long attackPt = atk;
        for (int i = 0; i < n; i++) {
            Room cur = room[i];
            if (cur.portion == null) {
                // 몬스터인거
                long[] monster = cur.monster;
                long monsterAtk = monster[0];
                long monsterHp = monster[1];
                // 몬스터를 처리하는 규칙은 한대씩 주고 받는건데 while문으로 돌리기엔 너무 큰 값임
                // 그럼 어떻게 비교하지?
                // 서로의 체력 / 서로의 공격력이 용사가 낮거나 같으면 죽음, 다르면 몬스터체력 / 내 공격력 * 몬스터 공격력만큼 내 체력에서 빼주면 되지 않나?
                // 정수 나눗셈은 무조건 내림 처리하기 떄문에 올림 처리를 해야 최소 타격수가 나옴
                // (a + b - 1) / b;
                long need = (monsterHp + attackPt - 1) / attackPt;
                long taken = (need - 1) * monsterAtk;
                if (curHp <= taken) {
                    return false;
                }
                curHp -= taken;
            } else {
                // 포션인거
                // 공격력 증가
                attackPt += cur.portion[0];

                // 최대보다 커질수는 없음
                curHp = Math.min(initHp, curHp + cur.portion[1]);
            }
        }

        // 모든 방 통과한거 아닌가?
        return true;
    }
}

class Room {
    long[] portion;
    long[] monster;

    public Room(int flag, long[] arr) {
        if (flag == 1) {
            monster = arr;
        } else {
            portion = arr;
        }
    }
}