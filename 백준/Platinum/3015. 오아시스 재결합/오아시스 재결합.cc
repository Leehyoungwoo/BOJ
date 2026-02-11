#include <bits/stdc++.h>
using namespace std; 
typedef long long ll;
int n, m;
int temp;
ll ret;
stack<pair<int, int>> st;
// 증가 + 같음은 이 로직으로 올바르게 세지는거 같은데
// 감소는 어쩌지?
int main(){
    cin >> n;
    for (int i = 0; i < n; i++){
        cin >> temp;
        int cnt = 1;
        while (st.size() && st.top().first <= temp) {
            if (st.top().first == temp) {
                cnt = st.top().second + 1;
            }
            ret+=st.top().second;
            st.pop();
        }
        // 이렇게 저정하고 기본적으로 단조로 증가하는 형태면 top해서 second, 카운트를 더해주면 되지 않나?
        // 같으면 cnt 더해줘서 
        if (st.size()) {
            ret++;
        }
        st.push({temp, cnt});
    }

    cout << ret << '\n';
    return 0;
}