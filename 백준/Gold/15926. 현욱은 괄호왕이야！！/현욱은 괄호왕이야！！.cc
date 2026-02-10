#include <bits/stdc++.h>
using namespace std;
int n;
string ip;
// 내가 아는 올바른 괄호열을 구하는데
// 우선 넣어줘
// 그다음에 pop하는 순간에 계산해줘야하나
// 시작점 잡아주고 쭉 넣어주면서 0 1 2 하다가 2에서 pop하면 0을 유지 
int mx = INT_MIN;
int main(){
    cin >> n;
    cin >> ip;
    if (n == 1) {
        cout << 0 << '\n';
        return 0;
    }
    stack<int> st;
    st.push(-1);
    // 길이는 오른쪽 끝에서 왼쪽 경계선
    // (), -1 0 1 
    // 2 1pop 3 0pop
    // )면 일단 팝해주고 만약 비었으면 넣어주고 i - st.top()이 0
    // )가 들은 상태에서 쌓이고 옳은 문자열이 완성되면 왼쪽 경계인 ), st.top을 빼주면 됨
    for (int i = 0; i < n; i++){
        char c = ip[i];
        if(c == '(') st.push(i);
        else{
            st.pop();
            if (st.empty()) st.push(i);
            mx = max(mx, i - st.top());
        }
    }

    cout << (mx == INT_MIN ? 0 : mx) << '\n';
    return 0;
}