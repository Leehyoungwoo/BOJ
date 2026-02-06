#include <bits/stdc++.h>
using namespace std;
int n, m, j;
int d;
int main(){
    cin >> n >> m >> j;
    int l = 1;
    int r = l + m - 1;
    for (int i = 0; i < j; i++) {
        int x;
        cin >> x;
        if (l <= x && x <= r) {
            // 들어옴
            continue;
        }

        // 왼쪽 기준으로 움직이는 경우
        if (x < l) {
            d+=l - x;
            r-=l - x;
            l = x;
        } else {
            // 오른쪽 기준으로 움직이는 경우
            d+=x - r;
            l+=x - r;
            r = x;
        }
    }

    cout << d << "\n";

    return 0;
}