#include <bits/stdc++.h>
using namespace std;
int n;
int cnt;
int main() {
    cin >> n;
    // 규칙에 따라 만개 만들고 정렬할까
    // 아 앞뒤로만 붙이면 안되는거 같은데
    int start = 665;
    while(cnt < n) {
        start++;
        if (to_string(start).find("666") != string::npos) {
            cnt++;
        }
    }
    cout << start << "\n";

    return 0;
}