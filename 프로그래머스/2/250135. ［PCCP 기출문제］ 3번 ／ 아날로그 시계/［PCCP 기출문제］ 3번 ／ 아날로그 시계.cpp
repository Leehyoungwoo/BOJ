#include <bits/stdc++.h>
using namespace std;

int toSecond(int h,int m,int s){ return h*3600 + m*60 + s; }

int solution(int h1,int m1,int s1,int h2,int m2,int s2){
    int start = toSecond(h1,m1,s1);
    int end   = toSecond(h2,m2,s2);

    int A_m = (end * 59) / 3600 + 1;
    int A_h = (end * 719) / 43200 + 1;

    int A_triple = 1;             
    if (end >= 43200) A_triple += 1;

    int A = A_m + A_h - A_triple;

    int B = 0;
    if (start > 0) {
        int B_m = ((start * 59 - 1) / 3600) + 1;
        int B_h = ((start * 719 - 1) / 43200) + 1;

        int B_triple = 1;           
        if (start > 43200) B_triple += 1; 

        B = B_m + B_h - B_triple;
    }

    return A - B;
}