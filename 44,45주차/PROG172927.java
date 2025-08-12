import java.util.*;

public class Solution {
    public int solution(int[] picks, String[] minerals) {
        // 피로도 테이블
        int[][] fatigue = {
            {1, 1, 1},    // 다이아몬드 곡괭이
            {5, 1, 1},    // 철 곡괭이  
            {25, 5, 1}    // 돌 곡괭이
        };
        
        // 광물 타입을 숫자로 변환하는 함수
        Map<String, Integer> mineralMap = new HashMap<>();
        mineralMap.put("diamond", 0);
        mineralMap.put("iron", 1);
        mineralMap.put("stone", 2);
        
        // 전체 곡괭이 개수
        int totalPicks = picks[0] + picks[1] + picks[2];
        
        // 실제로 캘 수 있는 광물 개수 (곡괭이 * 5개씩)
        int maxMinerals = Math.min(minerals.length, totalPicks * 5);
        
        // 광물을 5개씩 그룹으로 나누고 각 그룹의 난이도 계산
        List<MineralGroup> groups = new ArrayList<>();
        
        for (int i = 0; i < maxMinerals; i += 5) {
            int endIdx = Math.min(i + 5, maxMinerals);
            MineralGroup group = new MineralGroup();
            
            // 현재 그룹의 광물들
            for (int j = i; j < endIdx; j++) {
                int mineralType = mineralMap.get(minerals[j]);
                group.minerals.add(mineralType);
            }
            
            // 이 그룹의 난이도 계산 (돌 곡괭이로 캤을 때의 피로도)
            group.difficulty = 0;
            for (int mineral : group.minerals) {
                group.difficulty += fatigue[2][mineral]; // 돌 곡괭이 기준
            }
            
            groups.add(group);
        }
        
        // 난이도가 높은 그룹부터 정렬 (내림차순)
        Collections.sort(groups, (a, b) -> b.difficulty - a.difficulty);
        
        // 좋은 곡괭이부터 사용해서 어려운 그룹 처리
        int answer = 0;
        int groupIdx = 0;
        
        // 다이아몬드 곡괭이 사용
        for (int i = 0; i < picks[0] && groupIdx < groups.size(); i++) {
            MineralGroup group = groups.get(groupIdx++);
            for (int mineral : group.minerals) {
                answer += fatigue[0][mineral];
            }
        }
        
        // 철 곡괭이 사용
        for (int i = 0; i < picks[1] && groupIdx < groups.size(); i++) {
            MineralGroup group = groups.get(groupIdx++);
            for (int mineral : group.minerals) {
                answer += fatigue[1][mineral];
            }
        }
        
        // 돌 곡괭이 사용
        for (int i = 0; i < picks[2] && groupIdx < groups.size(); i++) {
            MineralGroup group = groups.get(groupIdx++);
            for (int mineral : group.minerals) {
                answer += fatigue[2][mineral];
            }
        }
        
        return answer;
    }
    
    // 광물 그룹을 나타내는 클래스
    class MineralGroup {
        List<Integer> minerals = new ArrayList<>();
        int difficulty = 0;
    }
}
