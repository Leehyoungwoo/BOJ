#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int n, k;
int coin[100];
ll money[10001];
int main(){
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        cin >> coin[i];
    }

    money[0] = 1L;
    for (int i = 0; i < n; i++) {
        for (int j = coin[i]; j <= k; j++) {
            money[j] += money[j - coin[i]];
        }
    }
    cout << money[k] << '\n';
    return 0;
}