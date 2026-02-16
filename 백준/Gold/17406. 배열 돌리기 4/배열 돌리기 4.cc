#include <bits/stdc++.h>
using namespace std;
int n, m, k;
int mp[51][51];
// 400만까지
// n까지 범위의 소수를 구하기 -> 어떻게 구하지?
// vector에 넣어놓고 정렬한다음에 투포인터 로직? 연속된 수인데
// 소수를 구해보자 -> 소수는 나말고는 나누어떨어지지 않는 수 -> 2부터 sqrt(n)까지 나머지가 0인 수
// 400만 ->  sqrt는 2000?
const int dir[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
int ret = INT_MAX;
int cp[51][51];
vector<vector<int>> v;
void rollback(){
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            mp[i][j] = cp[i][j];
        }
    }
}
int findMin() {
    int mn = INT_MAX;
    for (int i = 0; i < n; i++) {
        int sum = 0;
        for (int j = 0; j < m; j++) {
            sum+=mp[i][j];
        }
        mn = min(mn, sum);
    }
    return mn;
}
void print(){
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cout << mp[i][j] << " ";
        }
        cout << '\n';
    }
}
bool in(int r, int c, int sr, int sc, int er, int ec) {
    return sr <= r && r <= er && sc <= c && c <= ec; 
}
void go(int R, int C, int s) {
    // cout << '\n';
    // r - s, c-s ~ r + s, c + s까지 돌리는건데
    // 테두리만 돌리는게 아님 -> s--하면서 s는 0이되면 스톰
    while (s > 0) {
        // 범위
        int startR = R - s;
        int startC = C - s;
        int endR = R + s;
        int endC = C + s;
        // cout << startR << " " << startC << " " << endR << " " << endC << "\n";

        int cr = startR;
        int cc = startC;
        int cnt = 0;
        int prev = 0;
        int d = 0;
        while (true) {
            int nr = cr + dir[d][0];
            int nc = cc + dir[d][1];
            // 방향 가능 여부부터
            if (!in(nr, nc, startR, startC, endR, endC)) {
                cnt++;
                d = (d + 1) % 4;
                nr = cr + dir[d][0];
                nc = cc + dir[d][1];
                continue;
            }
            int temp = mp[cr][cc];
            mp[cr][cc] = prev;
            prev = temp;
            cr = nr;
            cc = nc;
            if (cnt == 4) {
                break;
            }
        }
        s--;
        // cout << "s는 " << s << '\n'; 
        // print();
        // cout << '\n';
    }
}
int main() {
    cin >> n >> m >> k;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> mp[i][j];
            cp[i][j] = mp[i][j];
        }
    }

    for (int i = 0; i < k; i++) {
        int r, c, s;
        cin >> r >> c >> s;
        v.push_back({r - 1, c - 1, s});
    }
    int a[(int)v.size()];
    for (int i = 0; i < (int)v.size(); i++) {
        a[i] = i;
    }
    do{
        for(int i : a){
            int r = v[i][0];
            int c = v[i][1];
            int s = v[i][2];
            go(r, c, s);
        }
        int value = findMin();
        ret = min(value, ret);
        rollback();
    }while(next_permutation(a, a + (int)v.size()));
    cout << ret << '\n';
    return 0;
}