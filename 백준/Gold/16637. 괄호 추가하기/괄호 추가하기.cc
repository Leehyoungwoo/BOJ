#include <bits/stdc++.h>
using namespace std;
int n;
string s;
vector<int> num;
vector<char> oper_str;
int ret = INT_MIN;
// 연산자는 모두 동일한 우선 순위래 -> 왼쪽부터 계산
// 괄호를 추가할수 있음 -> 중첩 괄호는 사용 불가
// 괄호 안에는 연산자 반드시 하나
// 결과 최댓값은? 괄호 개수 제한은 없고 추가하지 않아도 됨
// n <= 19 => 숫자는 최대 10개, 연산자 최대 9개 => 괄호는 최대 4개
// 예를 들어 숫자가 3 5 6 9 면 괄호는 5, 6, 9 3개 넣을 수 있음 => 하나도 안넣기 or 5, 6, 9에 하나 넣기 or 6, 3두개 넣기 
int oper(char a, int b, int c) {
    if(a == '+') return b + c;
    if(a == '-') return b - c;
    if(a == '*') return b * c;
}
// 3 8 7 9 2
void go(int here, int _num) {
    if (here == num.size() - 1) {
        ret = max(ret, _num);
        return;
    }
    // (현재, 다음)
    go(here + 1, oper(oper_str[here], _num, num[here + 1]));
    // 오버플로우 체크
    if (here + 2 <= num.size() - 1) {
        // (현재 + 1, 현재 + 2)
        int temp = oper(oper_str[here + 1], num[here + 1], num[here + 2]);
        go(here + 2, oper(oper_str[here], _num, temp));
    }

    return;
}
int main(){
    cin >> n;
    cin >> s;
    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) num.push_back(s[i] - '0');
        else oper_str.push_back(s[i]);
    }
    go(0, num[0]);

    cout << ret << "\n";

    return 0;
}