#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
int n, m;
ll t[10000];
// 0분에 0 1 2 3 4 5 순으로 타고
// 7분에 6, 8분에 7, 9분에 8, 10분에 9
// N이 20억이니까 순회 대상은 절대 아닌데 logn으로 풀면 32쯤?
// 시간이 더 빨리 끝나면 더 빨리 굴림
// 놀이기구 모두 타는데 걸리는 최소 시간을 구하고 그 시간에 맞춰서 마지막에 타는애가 몇번을 타는지 구할까?
// target 초동안 태울 수 있는 인원 
ll serve(ll target) {
    // 0분에는 m명 탑승 가능 
    ll sum = m;
    for (int i = 0; i < m; i++) {
        sum+=target / t[i];
    }

    return sum;
}
int main() {
    cin >> n >> m;
    ll r = 0;

    for (int i = 0; i < m; i++) {
        cin >> t[i];
        r = max(r, t[i]);
    }
    if (n <= m) {
        cout << n << '\n';
        return 0;
    }
    ll l = 0;
    r = r * n; 
    while (l < r) {
        ll mid = l + (r - l) / 2;
        ll sum = serve(mid);
        // 태울 수 있는 인원이 n이상 -> 태우는 시간을 줄여보기
        if (sum >= n) {
            r = mid;
        } else {
            // N보다 작으면 태우는 총 시간 늘리기
            l = mid + 1;
        }
    }

    ll T = l;
    // T - 1초. n명을 다 태우는 로어바운드, 제일 작은 시간보다 1작으니까 n-1명이 타는중. 혹은 같은 시간이면 두명일수도 있음
    ll before = serve(T - 1);
    for (int i = 0; i < m; i++) {
        // 시간이랑 나누어떨어지면 적어도 이 인덱스에서 마지막 탑승 인원들이 탄것 -> serve는 시간에서 놀이기구 탑승 시간 나눈 것을 모두 더한 것이라 나누어떨어지면 
        // 마지막 라인임
        if (T % t[i] == 0) {
            // 7 8 9 7 8 처럼 복수일수도 있으니 ++해서 그 수가 n과 같은지 확인
            before++;
            if (before == n) {
                cout << i + 1 << '\n';
                return 0;
            }
        }
    }
    return 0;
}