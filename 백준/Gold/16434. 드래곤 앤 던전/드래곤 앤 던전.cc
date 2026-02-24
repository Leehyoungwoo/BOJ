#include <bits/stdc++.h>
using namespace std;
int n;
int initatp;
int ret;
typedef long long ll;
struct room {
    int t, a, h;
};
vector<room> v;
// 용사의 최대 생명력은 1이상이여야 하고 들어간 이후로 변하지 않음
// 현재 용사의 생명력은 최대 생명력 HMaxHP, 값은 HMaxHP보다 커질 수 없음
// 공격 포인드
// 방은 순회 -> n번째 방 도달하면 승리
// 용사의 공격력 HATK만큼 몬스터의 생명력을 깎음
// 몬스터 생명력 0 이하면 전투 종료
// 용사 생명력 0이하면 전투 종료, 사망
// 포션이 있으면 현재 hp 회복, 공격력 증가
// t == 1 몬스터, t == 2는 포션
// 방은 10만개정도
// 이분탐색이네
bool possible(ll mhp) {
    ll catp = initatp;
    ll chp = mhp;
    bool win = true;
    for (int i = 0; i < n; i++) {
        int type = v[i].t;
        ll atp = v[i].a;
        ll hp = v[i].h;
        // 몬스터
        if (type == 1) {
            // 용사가 몬스터 때리고 몬스터가 용사를 때림.. 음 .... 용사 피 / 몬스터 공격 vs 몬스터 피 / 용사 공격
            // 용사 선공
            // 체력이 남으면 안되니까 올림
            ll herohit = hp / catp;
            if (hp % catp != 0) herohit++;
            if (chp - (herohit - 1) * atp <= 0) return false;
            chp-=(herohit - 1) * atp;
        } else {
            // 포션
            chp = min(chp + hp, mhp);
            catp+=atp;
        }
    }

    return win;
}
int main(){
    cin >> n >> initatp;
    for (int i = 0; i < n; i++) {
        int t, a, h;
        cin >> t >> a >> h;
        v.push_back({t, a, h});
    }
    ll l = 0;
    ll r = LLONG_MAX;
    while (l < r) {
        ll mid = l + (r - l) / 2;
        if (!possible(mid)) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    cout << l << '\n';
    
    return 0;
}