#include<bits/stdc++.h>
using namespace std;
int n;
int main() {
    string result;
    while (cin >> n){
        int ret = 1;
        int cnt = 1;
        while (ret % n != 0) {
            ret = (ret * 10) % n + 1;
            cnt++;
        }
        cout << cnt << "\n";
    }

    return 0;
}