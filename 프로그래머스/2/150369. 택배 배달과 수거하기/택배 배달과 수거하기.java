import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Integer> deliveryStack = new Stack<>();
        Stack<Integer> pickupStack = new Stack<>();

        // 스택에 집의 배달과 수거 정보를 추가
        for (int i = 0; i < n; i++) {
            deliveryStack.push(deliveries[i]);
            pickupStack.push(pickups[i]);
        }

        long move = 0;

        // 모든 배달과 수거가 완료될 때까지 반복
        while (!deliveryStack.isEmpty() || !pickupStack.isEmpty()) {
            int roundCap = cap; // 각 회전에서의 용적
            int currentDistance = 0;

            // 배달 처리
            int furthestDelivery = -1; // 가장 먼 배달 집
            while (!deliveryStack.isEmpty() && roundCap > 0) {
                int curDelivery = deliveryStack.pop();
                if (curDelivery > 0) {
                    furthestDelivery = Math.max(furthestDelivery, deliveryStack.size() + 1);
                    if (curDelivery <= roundCap) {
                        roundCap -= curDelivery; // 배달 가능
                    } else {
                        deliveryStack.push(curDelivery - roundCap); // 남은 배달량 다시 스택에 추가
                        roundCap = 0; // 트럭이 꽉 찼음
                    }
                }
            }

            // 수거 처리
            roundCap = cap; // 수거를 위해 로드 초기화
            int furthestPickup = -1; // 가장 먼 수거 집
            while (!pickupStack.isEmpty() && roundCap > 0) {
                int curPickup = pickupStack.pop();
                if (curPickup > 0) {
                    furthestPickup = Math.max(furthestPickup, pickupStack.size() + 1);
                    if (curPickup <= roundCap) {
                        roundCap -= curPickup; // 수거 가능
                    } else {
                        pickupStack.push(curPickup - roundCap); // 남은 수거량 다시 스택에 추가
                        roundCap = 0; // 트럭이 꽉 찼음
                    }
                }
            }

            // 가장 먼 집까지의 거리 이동 (왕복 거리)
            int furthestHouse = Math.max(furthestDelivery, furthestPickup);
            if (furthestHouse > 0) {
                move += furthestHouse * 2; // 왕복 거리 추가
            }
        }

        return move;
    }
}
