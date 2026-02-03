#include<bits/stdc++.h>
using namespace std;
string input;
int is_palindrome(int left, int right) {
    // cout << left << " " << right << "\n";
    // cout << input[left] << input[right] << '\n';
    while (left >= 0 && right < (int) input.size()) {
        // cout << input[left] << input[right] << '\n';
        if (input[left] == input[right]) {
            left--;
            right++;
        } else {
            return 0;
        }
    }
    
    return 1;
}
int main() {
    cin >> input;
    int n = input.size();
    if (n % 2 == 0) {
        cout << is_palindrome(n / 2 - 1, n / 2) << "\n";
    } else {
        cout << is_palindrome(n / 2, n / 2) << "\n";
    }

    return 0;
}
