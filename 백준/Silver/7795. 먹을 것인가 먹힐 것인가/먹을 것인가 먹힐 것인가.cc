#include <bits/stdc++.h>
using namespace std;
int t, n, m;
int a[20001];
int b[20001];
string ret;
int main(){
    cin >> t;
    for (int tc = 0; tc < t; tc++) {
        cin >> n >> m;
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }
        for (int i = 0; i < m; i++) {
            cin >> b[i];
        }
        // 둘중 하나 정렬하고, b 정렬
        sort(b, b + m);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += lower_bound(b, b + m, a[i]) - b;
        }
        ret+=to_string(sum) + '\n';
    }

    cout << ret;
    return 0;
}