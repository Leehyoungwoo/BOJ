#include <bits/stdc++.h>
using namespace std;
int n, k, l;
int mp[101][101];
int ret;
// 뱀은 몸길이 늘려서 머리 다음칸에 위치
// 벽이나 자기 자신의 몸과 부딪히면 게임이 끝남
// 이동한 칸에 사과가 있으면 그 칸 사과 없어지고 꼬리 움직이지 않음
// 이동한 칸에 사과 없으면 몸길이 줄여서 꼬리가 위치한 칸을 비워줌, 몸길이 변화 x
const int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
deque<pair<int, int>> snake;
bool finish;
int d;
int t[101];
char o[101];
vector<pair<int, char>> v;
void print() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << mp[i][j] << ' ';
        }
        cout << '\n';
    }
}
bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < n;
}
void go() {
    int nr = snake.front().first + dir[d][0];
    int nc = snake.front().second + dir[d][1];
    if (!in(nr, nc) || mp[nr][nc] == 1) {
        finish = true;
        return;
    }
    snake.push_front({nr, nc});
    if (mp[nr][nc] == 2) {
        mp[nr][nc] = 1;
    } else {
        auto it = snake.back();
        mp[nr][nc] = 1;
        mp[it.first][it.second] = 0;
        snake.pop_back();
    }
}
void changeDir(char o) {
    // L 왼쪽, D 오른쪽
    if (o == 'L') {
        d = (d + 3) % 4;
    } else {
        d = (d + 1) % 4;
    }
}
int main() {
    cin >> n >> k;
    for (int i = 0; i < k; i++) {
        int r, c;
        cin >> r >> c;
        mp[r - 1][c - 1] = 2;
    }
    mp[0][0] = 1;
    snake.push_back({0, 0});
    cin >> l;
    // 사과 2
    
    for (int i = 0; i < l; i++) {
        int b;
        char c;
        cin >> b >> c;
        v.push_back({b, c});
    }
    v.push_back({-1, 'a'});
    int time = 0;
    // 마지막에 바뀐 방향으로 이동하기 루프 한번 더 해야지 그냥 break되고 종료되면 안됨
    for (int i = 0; i < (int) v.size(); i++) {
        int tm = v[i].first;
        char ord = v[i].second;
        // 뱀이 나아가고 초 증가는 맞는듯?
        // 다음 루프에서 초가 같으면 나가서 방향 바꿔와
        while (true) {
            if (time == tm) {
                changeDir(ord);
                break;
            }
            go();
            time++;
            if (finish) {
                break;
            }
        }
        if (finish) {
            break;
        }
    }
    cout << time << '\n';
    return 0;
}