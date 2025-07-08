import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int dangerCount = 0;
        
        // 1단계: 각 로봇의 전체 이동 경로를 미리 계산
        List<List<int[]>> robotPaths = new ArrayList<>();
        
        for (int[] route : routes) {
            List<int[]> path = calculateFullPath(points, route);
            robotPaths.add(path);
        }
        
        // 2단계: 가장 긴 경로의 길이 구하기 (시뮬레이션 종료 시점 결정)
        int maxPathLength = 0;
        for (List<int[]> path : robotPaths) {
            maxPathLength = Math.max(maxPathLength, path.size());
        }
        
        // 3단계: 각 시간별로 충돌 검사
        for (int time = 0; time < maxPathLength; time++) {
            Map<String, Integer> positionCount = new HashMap<>();
            
            // 각 로봇의 현재 위치 계산
            for (int robot = 0; robot < robotPaths.size(); robot++) {
                List<int[]> path = robotPaths.get(robot);
                
                // 해당 시간에 로봇이 아직 운송 중인지 확인
                if (time < path.size()) {
                    int[] pos = path.get(time);
                    String posKey = pos[0] + "," + pos[1];
                    positionCount.put(posKey, positionCount.getOrDefault(posKey, 0) + 1);
                }
            }
            
            // 2대 이상 모인 좌표의 개수 계산
            for (int count : positionCount.values()) {
                if (count >= 2) {
                    dangerCount++;
                }
            }
        }
        
        return dangerCount;
    }
    
    // 로봇의 전체 이동 경로 계산 (최단거리, r좌표 우선)
    private List<int[]> calculateFullPath(int[][] points, int[] route) {
        List<int[]> path = new ArrayList<>();
        
        for (int i = 0; i < route.length; i++) {
            int pointIndex = route[i] - 1; // 포인트 번호는 1부터 시작하므로 인덱스는 -1
            int[] targetPoint = points[pointIndex];
            
            if (i == 0) {
                // 첫 번째 포인트는 시작점 (0초에 위치)
                path.add(new int[]{targetPoint[0], targetPoint[1]});
            } else {
                // 이전 포인트에서 현재 포인트로 이동하는 모든 중간 좌표들을 계산
                int[] currentPos = path.get(path.size() - 1).clone(); // 마지막 위치 복사
                
                // 최단 경로로 이동 (r좌표 우선)
                while (currentPos[0] != targetPoint[0] || currentPos[1] != targetPoint[1]) {
                    // r좌표를 먼저 맞춤
                    if (currentPos[0] < targetPoint[0]) {
                        currentPos[0]++;
                    } else if (currentPos[0] > targetPoint[0]) {
                        currentPos[0]--;
                    }
                    // r좌표가 같으면 c좌표 조정
                    else if (currentPos[1] < targetPoint[1]) {
                        currentPos[1]++;
                    } else if (currentPos[1] > targetPoint[1]) {
                        currentPos[1]--;
                    }
                    
                    path.add(new int[]{currentPos[0], currentPos[1]});
                }
            }
        }
        
        return path;
    }
}
