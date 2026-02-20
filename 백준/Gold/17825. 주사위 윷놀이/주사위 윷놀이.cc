#include <bits/stdc++.h>
using namespace std;
int dice[10];
int value[33];
int pos[4];
int visited[33];
int mx;
vector<int> nxt[33];
void setting() {
    // 끝에 도착
    nxt[20].push_back(32);
    for (int i = 0; i < 20; i++) {
        nxt[i].push_back(i + 1);
    }
    int start = 5;
    for(int i = 21; i < 27; i++) {
        nxt[start].push_back(i);
        start = i;
    }
    nxt[26].push_back(20);

    start = 10;
    for (int i = 27; i < 29; i++) {
        nxt[start].push_back(i);
        start = i;
    }
    nxt[28].push_back(24);

    start = 15;
    for (int i = 29; i < 32; i++) {
        nxt[start].push_back(i);
        start = i;
    }
    nxt[31].push_back(24);
    // 값넣기
    for (int i = 0; i <= 20; i++) {
        value[i] = 2 * i;
    }
    value[21] = 13;
    value[22] = 16;
    value[23] = 19;
    value[24] = 25;
    value[25] = 30;
    value[26] = 35;
    value[27] = 22;
    value[28] = 24;
    value[29] = 28;
    value[30] = 27;
    value[31] = 26;
    value[32] = 0;
}
void go(int idx, int sum) {
    if (idx == 10) {
        mx = max(mx, sum);
        return;
    }
    // 네개중 하나를 선택해서 dice[idx]만큼 움직일거는 고정
    for (int i = 0; i < 4; i++) {
        // 현재 위치
        int cpos = pos[i];
        int cnt = dice[idx];
        // 도착했거나 그 칸에 다른 말이 있으면 못움직임
        // 끝에 도착한 말일 경우 스킵
        if (cpos == 32) continue;
        // 이동 거리 계산
        int d = 0;
        if (cpos == 5 || cpos == 10 || cpos == 15) {
            d = 1;
        }
        int nt = cpos;
        while (cnt--) {
            nt = nxt[nt][d];
            d = 0;
            if (nt == 32) {
                break;
            }  
        }
        // 다음위치에 이미 말이 있으면 넘겨
        if (visited[nt] && nt != 32) continue;
        pos[i] = nt;
        visited[cpos] = false;
        visited[nt] = true;
        go(idx + 1, sum + value[nt]);
        pos[i] = cpos;
        visited[cpos] = true;
        visited[nt] = false;
    }
}
int main(){
    for (int i = 0; i < 10; i++) cin >> dice[i];
    setting();
    go(0, 0);

    cout << mx << '\n';
    return 0;
}