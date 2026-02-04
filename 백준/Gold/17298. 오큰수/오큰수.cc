#include<bits/stdc++.h>
using namespace std;
int n; int a[1000001];
int main() {
    cin >> n;
    stack<int> st;

    int result[n];
    fill(result, result + n, -1);

    for (int i = 0; i < n; i++) {
        int num;
        cin >> num;
        a[i] = num;
        while (!st.empty() && a[st.top()] < num) {
            result[st.top()] = num;
            st.pop();
        }  

        st.push(i);
    }

    for (int i = 0; i < n; i++) {
        cout << result[i] << " ";
    }

    return 0;
}