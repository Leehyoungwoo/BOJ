#include <bits/stdc++.h>
using namespace std;
int n;
int mx = INT_MIN;
int a[100001];
stack<int> st;
int main(){
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    // 누적합 구해서 i - j로 모든 구간의 범위를 구할 수 잇음 -> max
    // 하지만 범위가 10만 => 100억 -> 불가능
    // prev로 풀 수 있지 않을까?
    // 순회하면서 sum에 더하고, 
    // 플러스면 무지성 더해도 됨.
    // 마이너스인 경우 갈리는데
    // 지금까지 누적합이 마이너스인데 다음 값도 마이너스면 더하면 안됨. prev는 새로운 마이너스인 cur
    // prev가 플러스였는데 마이너스가 한번 나온거다? 마이너스 오른쪽에 따라 다름
    // 얘기하다보니까 stack 느낌 나는데
    for (int i = 0; i < n; i++) {
        if (st.size() && st.top() > 0 && a[i] > 0) {
            int b = st.top();
            st.pop();
            st.push(a[i] + b);
            continue;
        } 
        st.push(a[i]);
    }
    int sum = st.top(); st.pop();
    mx = max(sum, mx);
    while(st.size()) {
        if (sum < 0) {
            sum = 0;
            continue;
        }
        int i = st.top();
        st.pop();
        sum+=i;
        mx = max(sum, mx);
    }

    cout << mx << '\n';

    return 0;
}   