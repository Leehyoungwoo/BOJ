#include <bits/stdc++.h>
using namespace std;
int n, k;
int coin[100];
int money[10001];
const int INF = 98765421;
int main(){
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> coin[i];
    }

    fill(money, money + 10001, INF);
    money[0] = 0;
    for (int i = 0; i <= 10000; i++) {
        for (int j = 0; j < n; j++) {
            if (i + coin[j] > 10000) continue;
            money[i + coin[j]] = min(money[i + coin[j]], money[i] + 1);
        }
    }

    if (money[k] == INF) {
        cout << -1 << '\n';
        return 0;
    } 
    
    cout << money[k] << '\n';
    return 0;
}