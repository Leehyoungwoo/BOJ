#include<bits/stdc++.h>
using namespace std;
int n, k;

int main (){
    cin >> n; cin >> k;
    int a[n];
    for (int i = 0; i < n; i++) {
        int t;
        cin >> t;
        a[i] = t;
    }    

    int pres[n + 1];
    for (int i = 1; i <= n; i++) {
        pres[i] = pres[i - 1] + a[i - 1];
    }

    int max = -100000000;
    for (int i = k; i <= n; i++) {
        if (max < pres[i] - pres[i - k]) max = pres[i] - pres[i - k];
    }

    cout << max << "\n";
    return 0;
}