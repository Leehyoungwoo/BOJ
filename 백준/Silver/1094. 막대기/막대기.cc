#include <bits/stdc++.h>
using namespace std;
int x;
int cnt;
// 64미만의 값이 들어오는데
// 그럼 1부터 1000000까지 들어옴
// 64짜리를 반씩 잘라서 막대기 만들기? -> 비트1인거 개수 아닌가
int main(){
    cin >> x;
    while (x > 0) {
        cnt+= x & 1;
        x >>=1;
    }
    cout << cnt << '\n';

    return 0;
}