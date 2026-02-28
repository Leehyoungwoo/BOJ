#include <bits/stdc++.h>
using namespace std;
int n;
int lis[1000001];
int len;
int num;
int main() {
    cin >> n; 
    for (int i = 0; i < n; i++) {
        cin >> num;
        auto lowerPos = lower_bound(lis, lis + len, num);
        if (lowerPos == lis + len) len++;
        *lowerPos = num;
    }

    cout << len << '\n';
    return 0;
}