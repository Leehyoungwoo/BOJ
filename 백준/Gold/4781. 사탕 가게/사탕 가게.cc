#include <bits/stdc++.h>
using namespace std;
int n;
double m;
int c;
double p;
string ret;
pair<int, double> candy[5001];
int dp[100000];
int main() {
    while(1) {
        memset(dp, 0, sizeof(dp));
        cin >> n >> m;
        if (n == 0 && m == 0.00) break;

        for (int i = 0; i < n; i++) {
            cin >> c >> p;
            candy[i] = {c, p};
        }
        int money = (int)(m * 100 + 0.5);
        for (int i = 0; i < n; i++) {
            int cal = candy[i].first;
            int price = (int)(candy[i].second * 100 + 0.5);
            for (int j = 0; j <= money; j++) {
                if (j - price < 0) continue; 
                dp[j] = max(dp[j], dp[j - price] + cal);
            }
        }
        ret+=to_string(dp[money]) + '\n';
    }   
    
    cout << ret << '\n';
    return 0;
}