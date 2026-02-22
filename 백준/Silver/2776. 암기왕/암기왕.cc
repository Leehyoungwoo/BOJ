#include <bits/stdc++.h>
using namespace std;
int t;
int n, m;
int one[1000001];
int two[1000001];
string ret;
int bs(int target) {
    int l = 0;
    int r = n - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (one[mid] == target) {
            return 1;
        } else if (one[mid] < target) {
            l = mid + 1;
        } else{
            r = mid - 1;
        }
    }

    return 0;
}
int main() {
    cin >> t;
    for (int tc = 0; tc < t; tc++) {
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> one[i];
        }

        cin >> m;
        for (int i = 0; i < m; i++) {
            cin >> two[i];
        }

        sort(one, one + n);
        for (int i = 0; i < m; i++) {
            ret+=to_string(bs(two[i])) + '\n';
        }
    }

    cout << ret << '\n';
    return 0;
}