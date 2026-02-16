#include <bits/stdc++.h>
using namespace std;
int n;
// 소수를 백터 안에 정의
bool visited[4000001];
vector<int> v;
int prefix[4000002];
int ret;
void print() {
    for (int i = 0; i < v.size(); i++) cout << v[i] << " ";
    cout << '\n';
    for (int i = 0; i < v.size() + 1; i++) cout << prefix[i] << ' ';
    cout << '\n'; 
}
void setting(){
    for (int i = 2; i * i <= n; i++) {
        for (int j = 2; j <= n / i; j++) {
            visited[i * j] = true;
        }
    }
    visited[1] = true; 
    for (int i = 1; i <= n; i++) {
        if(!visited[i]) v.push_back(i); 
    }

    for (int i = 1; i <= (int) v.size(); i++) {
        prefix[i] = prefix[i - 1] + v[i - 1];
    }
}
int main(){
    cin >> n;
    setting();
    // print();
    int l = 0;
    int r = 1;
    // print();
    while (r < (int) v.size() + 1 && l < r) {
        int sum = prefix[r] - prefix[l];
        // cout << sum << '\n';
        if (sum < n) {
            r++;
        } else if (sum > n) {
            l++;
        } else {
            ret++;
            r++;
        }
    }

    cout << ret << '\n';
    return 0;
}