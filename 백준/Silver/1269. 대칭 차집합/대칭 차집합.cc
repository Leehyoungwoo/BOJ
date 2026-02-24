#include <bits/stdc++.h>
using namespace std;
int a, b;
int A[200000];
int B[200000];
int ret;
int main(){
    cin >> a >> b;
    for (int i = 0; i < a; i++) {
        cin >> A[i];
    }

    for (int i = 0; i < b; i++) {
        cin >> B[i];
    }
    sort(A, A + a);
    sort(B, B + b);
    for (int i = 0; i < a; i++) {
        if (!binary_search(B, B + b, A[i]) ) {
            ret++;
        }
    }

    for (int i = 0; i < b; i++) {
        if (!binary_search(A, A + a, B[i]) ) {
            ret++;
        }
    }

    cout << ret << '\n';
    return 0;
}