#include<bits/stdc++.h>
using namespace std;
string input;
int cnt[26];
int main() {
    cin >> input;
    // 앞뒤가 똑같은 문장으로 바꿔야하는데
    // 홀수가 단 하나고 나머지가 짝수거나 모두 짝수여야만 팰린드로믈 만들수 있음
    // 글자가 빠른 순으로 만들어야함
    for (char c : input) {
        cnt[c -'A']++;
    }
    int oc = 0;
    int idx = -1;
    for (int i = 0; i < 26; i++) {
        if (cnt[i] % 2 == 1) {
            oc++; 
            idx = i;
        }
    }

    if (oc >= 2) {
        cout << "I'm Sorry Hansoo";
        exit(0);
    }

    string result;
    for (int i = 0; i < 26; i++) {
        if (cnt[i] >=  2) {
            for (int j = 0; j < cnt[i] / 2; j++) {
                result+='A' + i;
            }
        }
    }
    string temp = result;
    if (idx != -1) {
        result+='A'+idx;
    }
    
    reverse(temp.begin(), temp.end());
    result+=temp;

    cout << result << "\n";

    return 0;
}