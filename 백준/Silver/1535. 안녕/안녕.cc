#include <bits/stdc++.h>
using namespace std;
int n;
int hp[21];
int hppy[21];
int dp[101];
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) cin >> hp[i];
    for (int i = 0; i < n; i++) cin >> hppy[i];
    
    for(int i = 0; i < n; i++) {
        for (int h = 99; h >= hp[i]; h--) {
            dp[h] = max(dp[h], dp[h - hp[i]] + hppy[i]);
        }
    }
    cout << dp[99] << '\n';
    return 0;
}