#include <bits/stdc++.h>
using namespace std;
int n;
int ret1;
int ret2;
int dp[41];
int fibo(int idx) {
    if (idx == 1 || idx == 2) {
        ret1++;
        return 1;
    }
    return fibo(idx - 1) + fibo(idx - 2);
}
int main(){
    cin >> n;
    fibo(n);
    dp[1] = 1;
    dp[2] = 1;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
        ret2++;
    }
    cout << ret1 << " " << ret2 << '\n';
    return 0;
}