    class Solution {

        final int[] discountRate = {10, 20, 30, 40};
        int[] pricepolicy;
        int[] discountPolicy;
        int[][] globalUser;
        int[] globalEmoticons;
        int maxUser = Integer.MIN_VALUE;
        int maxPrice = Integer.MIN_VALUE;

        public int[] solution(int[][] users, int[] emoticons) {
            int[] answer = {};
            init(users, emoticons);
            answer = findAnswer();
            return answer;
        }

        private void init(int[][] users, int[] emoticons) {
            globalUser = users;
            globalEmoticons = emoticons;
        }

        private int[] findAnswer() {
            int[] temp = new int[2];
            findUsersMaximumPolicy();
            int[] per = new int[globalEmoticons.length];
            findMaxMemberAndMaxMoney(0, per);
            temp = new int[]{maxUser, maxPrice};
            return temp;
        }

        private void findUsersMaximumPolicy() {
            pricepolicy = new int[globalUser.length];
            discountPolicy = new int[globalUser.length];
            for (int i = 0; i < globalUser.length; i++) {
                int discount = globalUser[i][0];
                int maxPolicy = globalUser[i][1];
                discountPolicy[i] = discount;
                pricepolicy[i] = maxPolicy;
            }
//            printArr(pricepolicy);
//            printArr(discountPolicy);
        }

        private void findMaxMemberAndMaxMoney(int idx, int[] per) {
            if (idx == per.length) {
                int joinMember = 0;
                int sell = 0;
//                printArr(per);
                // 각 이모티콘 별 중복 순열 생성됨
                for (int i = 0; i < discountPolicy.length; i++) {
                    int sum = 0;
                    for (int j = 0; j < globalEmoticons.length; j++) {
                        if (discountPolicy[i] <= discountRate[per[j]]) {
                            sum += globalEmoticons[j] * (100 - discountRate[per[j]]) / 100;
                        }
                        // 기준액을 넘어버리면 바로 환불하고 가입
                        if (sum >= pricepolicy[i]) {
                            sum = 0;
                            joinMember++;
                            break;
                        }
                    }
                    sell += sum;
                }
                if (joinMember > maxUser) {
                    maxUser = joinMember;
                    maxPrice = sell;
                }

                if (joinMember == maxUser) {
                    maxPrice = Math.max(sell, maxPrice);
                }

                return;
            }

            for (int i = 0; i < discountRate.length; i++) {
                per[idx] = i;
                findMaxMemberAndMaxMoney(idx + 1, per);
            }
        }

        private void printArr(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }