#include<bits/stdc++.h>
using namespace std;
int n, m;
int main() {
    cin >> n;
    cin >> m;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    // 1 2 3 4 5 
    // 고윺의 번호
    sort(a, a + n);
    int l = 0; int r = n - 1;
    int result = 0;
    while (l < r) {
        int sum = a[l] + a[r];
        if (sum < m) l++;
        else if (sum > m) r--;
        else {
            result++;
            l++;
        } 
    }

    cout << result << "\n";
    return 0;
}