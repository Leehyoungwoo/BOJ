#include <bits/stdc++.h>
using namespace std;
int n;
int x, y, d, g;
int mp[101][101];
// x중가 y감소 x감소 y 증가
const int dir[4][2] = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
int ret;
// n개의 드래곤 커브
// 0 ≤ x ≤ 100, 0 ≤ y ≤ 100만 유효한 좌표
void print(){
    cout << '\n';
    for (int i = 0; i <= 10; i++) {
        for (int j = 0; j <= 10; j++) {
            cout << mp[i][j] << ' ';
        }
        cout << '\n';
    }
}
void draw(int x, int y, int d, int g) {
    // 이동 기준을 예로 들면 
    // 방향을 담는다고 치자 -> 0세대 0, 1세대 1, 2세대는 2번 그려야하는데 2, 3이 아니라 2, 1임 -> 왜냐하면 커브에 대한 90도니까 끝점부터 
    // -> 끝점 90도 돌리고 시작점 돌리고
    // 그럼 위치가 있고 세대별로 이동하는 방향을 담는데 -> 전세대 역순으로 +1 %4
    vector<int> total;
    total.push_back(d);
    for (int i = 0; i < g; i++) {
        int sz = (int) total.size();
        vector<int> temp;
        for (int j = sz - 1; j >= 0; j--) {
            int new_d = (total[j] + 1) % 4;
            temp.push_back(new_d);
        }
        for (auto it : temp) {
            total.push_back(it);
        }
    }
    mp[y][x] = 1;
    for(int di : total) {
        y += dir[di][0];
        x += dir[di][1];
        mp[y][x] = 1;
    }
}
void calculate() {
    for (int i = 0; i <= 99; i++) {
        for (int j = 0; j <= 99; j++) {
            if (mp[i][j] && mp[i + 1][j] && mp[i][j + 1] && mp[i + 1][j + 1]) ret++;
        }
    }
}
int main(){
    cin >> n;
    for(int i = 0; i < n; i++) {
        cin >> x >> y >> d >> g;
        draw(x, y, d, g);
    }
    calculate();

    cout << ret << '\n';
    return 0;
}