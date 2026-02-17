#include <bits/stdc++.h>
using namespace std;
int R, C, M;
int ret;
struct shark {
    int r, c, s, d, z;
};
const int dir[4][2] = {{-1 ,0}, {1, 0}, {0, 1}, {0, -1}};
map<int, deque<shark>> sharks;
vector<shark> tmp;
bool in(int r, int c) {
     return 0 <= r && r < R && 0 <= c && c < C; 
    }
int change(int d) {
    if (d == 0) return 1;
    if (d == 1) return 0;
    if (d == 2) return 3;
    return 2;
}

void move(shark &s, map<int, deque<shark>> &to) {
    int d = s.d;
    int speed = s.s;

    if (d == 0 || d == 1) {                
        int cycle = (R - 1) * 2;
        if (cycle) speed %= cycle;
        else speed = 0;
    } else {                               
        int cycle = (C - 1) * 2;
        if (cycle) speed %= cycle;
        else speed = 0;
    }
    while (speed--) {
        int nr = s.r + dir[d][0];
        int nc = s.c + dir[d][1];
        if (!in(nr, nc)) {
            d = change(d);
            nr = s.r + dir[d][0];
            nc = s.c + dir[d][1];
        }
        s.r = nr;
        s.c = nc;
    }
    s.d = d; 
    to[s.r * C + s.c].push_back(s);
}

void eat(deque<shark> &dq) {
    tmp.clear();
    int mx = 0;
    while (!dq.empty()) {
        auto it = dq.front(); dq.pop_front();
        mx = max(mx, it.z);
        tmp.push_back(it);
    }
    for (auto &x : tmp) {
        if (x.z == mx) {
            dq.push_back(x);
            return;
        }
    }
}

int main() {
    cin >> R >> C >> M;

    for (int i = 0; i < M; i++) {
        int r, c, s, d, z;
        cin >> r >> c >> s >> d >> z;
        r--; c--;
        sharks[r * C + c].push_back({r, c, s, d - 1, z});
    }

    for (int col = 0; col < C; col++) {

        // 1. 잡기 col열에서 가장 위, 행 r이 작은 상어
        for (int r = 0; r < R; r++) {
            int key = r * C + col;
            auto it = sharks.find(key);
            if (it != sharks.end() && !it->second.empty()) {
                ret += it->second.front().z;  
                it->second.pop_front();
                break;
            }
        }

        // 2. 이동 next에만 쌓기
        map<int, deque<shark>> nextSharks;
        for (auto &it : sharks) {
            while (!it.second.empty()) {
                shark s = it.second.front();
                it.second.pop_front();
                move(s, nextSharks);
            }
        }

        // 3) 같은 칸 먹기
        for (auto &it : nextSharks) {
            if ((int)it.second.size() > 1) eat(it.second);
        }

        sharks.swap(nextSharks);
    }

    cout << ret << '\n';
    return 0;
}