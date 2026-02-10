#include <bits/stdc++.h>
using namespace std;
string A;
string B;
string ret;
int main(){
    cin >> A >> B;
    reverse(A.begin(), A.end());
    reverse(B.begin(), B.end());
    int a = 0;
    int b = 0;
    int carry = 0;
    while (a < (int)A.size() || b < (int)B.size()) {
        int first = 0;
        if (a <= (int) A.size() - 1) {
            first =A[a] - '0';
        }
        int second = 0;
        if (b <= (int) B.size() - 1) {
            second =B[b] - '0';
        }
        // cout << "f : " << first << " " << "s : " << second << '\n';
        int sum = first + second + carry;
        if (sum >= 10) {
            carry = 1;
            sum-=10;
        } else {
            carry = 0;
        }
        ret+=to_string(sum);
        a++;
        b++;
    }
    if (carry == 1) {
        ret+='1';
    }
    reverse(ret.begin(), ret.end());

    cout << ret << '\n';
    return 0;
}