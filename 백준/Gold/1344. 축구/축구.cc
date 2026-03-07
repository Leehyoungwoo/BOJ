#include <bits/stdc++.h>
using namespace std;
int a, b;
// 구간, 골수안에 확률 저장
double dp[19][19];
// 구간은 5분, 각 득점 확률(합쳐서 100이 넘을수도 잇음) 
// 모든 구간에서 전부 골을 넣어도 16골 이상은 못넣네
// 둘 중 하나가 소수만큼 골을 넣었을 확률? -> 1 - 둘다 소수가 아닐 확률인가?
const int prime[] = {2, 3, 5, 7, 11, 13, 17, 19};
double go(double dp[19][19], int per) {
    dp[0][0] = 1;
    for (int i = 0; i < 18; i++) {
        for (int j = 0; j <= i; j++) {
            dp[i + 1][j] += dp[i][j] * (1.0 - (per / 100.0));
            dp[i + 1][j + 1] += dp[i][j] * (per / 100.0);
        }
    }

    double ret = 0.0;
    for (int x : prime) ret+= dp[18][x];
    return ret;
}
int main() {
    cin >> a >> b;
    memset(dp, 0, sizeof(dp));
    double aprob = go(dp, a);
    memset(dp, 0, sizeof(dp));
    double bprob = go(dp, b);

    cout << 1.0 - (1.0 - aprob) * (1.0 - bprob) << '\n';
    return 0;
}