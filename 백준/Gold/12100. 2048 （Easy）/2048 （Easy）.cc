#include <bits/stdc++.h>
using namespace std;

int n;
int mp[21][21];
int cp[21][21];
int cpcnt[21][21];
int cnt[21][21];
int per[5];
int mx;

const int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

bool in(int r, int c) {
    return 0 <= r && r < n && 0 <= c && c < n;
}

void copyBoard(){
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cp[i][j] = mp[i][j];

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cpcnt[i][j] = cnt[i][j];
}

void mov(int r, int c, int d) {
    if (cp[r][c] == 0) return;

    int curR = r;
    int curC = c;
    int val = cp[r][c];

    while (true) {
        int nr = curR + dir[d][0];
        int nc = curC + dir[d][1];

        if (!in(nr, nc)) break;

        if (cp[nr][nc] == 0) {
            curR = nr;
            curC = nc;
            continue;
        }

        if (cp[nr][nc] == val && cpcnt[nr][nc] == 0) {
            cp[nr][nc] *= 2;
            cpcnt[nr][nc] = 1;
            cp[r][c] = 0;
            return;
        }

        break;
    }

    if (curR != r || curC != c) {
        cp[curR][curC] = val;
        cp[r][c] = 0;
    }
}

void game(int d) {
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            cpcnt[i][j] = 0;

    if (d == 0) {
        for (int i = 0; i < n; i++)
            for (int j = n - 1; j >= 0; j--)
                if (cp[i][j])
                    mov(i, j, d);
    }

    if (d == 1) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (cp[i][j])
                    mov(i, j, d);
    }

    if (d == 2) {
        for (int i = n - 1; i >= 0; i--)
            for (int j = 0; j < n; j++)
                if (cp[i][j])
                    mov(i, j, d);
    }

    if (d == 3) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (cp[i][j])
                    mov(i, j, d);
    }
}

void go(int idx) {
    if (idx == 5) {
        copyBoard();

        for (int i = 0; i < 5; i++)
            game(per[i]);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mx = max(mx, cp[i][j]);

        return;
    }

    for (int i = 0; i < 4; i++) {
        per[idx] = i;
        go(idx + 1);
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) {
            cin >> mp[i][j];
            if (mp[i][j] != 0)
                cnt[i][j] = 1;
        }

    go(0);

    cout << mx << '\n';
    return 0;
}
