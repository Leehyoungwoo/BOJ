#include <bits/stdc++.h>
using namespace std;
int n;
double d[10001];
double mx;
int main() {
    cin >> n;
    for (int i = 0; i < n; i++) cin >> d[i];
    mx = d[0];
    double ret = d[0];
    for (int i = 1; i < n; i++) {
        if (ret < 1) {
            ret = d[i];
        } else {
            ret*=d[i];
        }
        mx = max(ret, mx);
    }

    printf("%.3f", mx);
    return 0;
}