#include<bits/stdc++.h>
using namespace std;
int r, c, k;
int cnt;
char grid[101][101];
bool visited[10][10];
const int dir[4][4] = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
bool isInRange(int cr, int cc) {
    return 0 <= cr && cr < r && 0 <= cc && cc < c;  
}
void findWay(int curR, int curC, int endR, int endC, int dis) {
    if (curR == endR && curC == endC) {
        if (dis == k) {
            cnt++;
        }
        return;
    }
    
    for (int i = 0; i < 4; i++) {
        int nr = curR + dir[i][0];
        int nc = curC + dir[i][1];
        if (isInRange(nr, nc) && grid[nr][nc] != 'T' && !visited[nr][nc]) {
            visited[nr][nc] = true;
            findWay(nr, nc, endR, endC, dis + 1);
            visited[nr][nc] = false;
        }
    }
}
int main(){
    cin >> r >> c >> k;
    for (int i = 0; i < r; i++) {
        string input;
        cin >> input;
        for (int j = 0; j < c; j++) {
            grid[i][j] = input[j];
        }
    }
    visited[r -1][0] = true;
    findWay(r - 1, 0, 0, c - 1, 1);

    cout << cnt << "\n";
    return 0;
}   