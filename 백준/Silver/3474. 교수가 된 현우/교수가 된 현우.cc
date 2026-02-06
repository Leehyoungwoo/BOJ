#include <bits/stdc++.h>
using namespace std;
int t, n;
typedef long long ll;
int count() {
    // 5의 배수 세보기
    int cnt = 0;
    ll ret = 5;
    while (ret <= n) {
        cnt += n / ret;
        ret *= 5;

    }

    return cnt;
}
int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> t;
    for (int i = 0; i < t; i++){
        cin >> n;
        // 팩토리얼에 2가 몇개인지가 중요하네
        // 5 10 15 20 25 30 35 40 45 50 55 60=> 12개
        // 25 50 => 4개
        // 
        // 25 50 => 2개 
        // 100이라 치면 => 20개
        // 25 => 4개 => 5 8개 => 중복 4개빼면 24개
        // 10의 개수 + 5의 개수(25, 125 등 5의 배수 고려)
        // 125
        // 5 10 15 20 25 30 ... 125 ==> 25개 => 25 50 75를 한번씩만 125를 한번씩만
        // 25 50 75 100 125  => 5개 => 10개 => 총합 + 5개 => 20 50 75 나머지 한번 채우고 125를 한번만
        // 125 => 1개 => 3개 => 1개 125 나머지 1번
        cout << count() << "\n";
    }

    return 0;
}