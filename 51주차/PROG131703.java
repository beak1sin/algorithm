class Solution {
    int rows;
    int cols;
    int[][] beginning;
    int[][] target;
    int minFlips = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        this.rows = beginning.length;
        this.cols = beginning[0].length;
        this.beginning = beginning;
        this.target = target;

        dfs(0, 0);

        return minFlips == Integer.MAX_VALUE ? -1 : minFlips;
    }

    private void dfs(int rowIdx, int rowFlipCount) {
        // [종료 조건] 모든 행에 대한 결정을 완료했을 때
        if (rowIdx == rows) {
            calculateAndCheck(rowFlipCount);
            return;
        }

        // 현재 행을 뒤집지 않고 다음 행으로 진행
        dfs(rowIdx + 1, rowFlipCount);

        // 현재 행을 뒤집고 다음 행으로 진행
        flipRow(rowIdx); 
        dfs(rowIdx + 1, rowFlipCount + 1);
        flipRow(rowIdx);
    }
    
    private void calculateAndCheck(int rowFlipCount) {
        int colFlipCount = 0;

        // 각 열을 순회하며 목표 상태와 비교
        for (int c = 0; c < cols; c++) {
            int diffCount = 0; // 현재 열과 목표 열이 다른 동전의 개수
            for (int r = 0; r < rows; r++) {
                if (beginning[r][c] != target[r][c]) {
                    diffCount++;
                }
            }
            
            if (diffCount == 0) {
                // 열이 이미 일치하면 추가 뒤집기 불필요
            } else if (diffCount == rows) {
                // 열이 완전히 반대이면 1회 뒤집어서 맞춤
                colFlipCount++;
            } else {
                // 일부만 다르면 현재 행 조합으로는 목표 달성 불가
                return;
            }
        }
        
        // 현재 조합의 총 뒤집기 횟수로 최솟값을 갱신
        minFlips = Math.min(minFlips, rowFlipCount + colFlipCount);
    }

   
    // 지정된 행의 모든 동전을 뒤집음
    private void flipRow(int row) {
        for (int i = 0; i < cols; i++) {
            beginning[row][i] = 1 - beginning[row][i];
        }
    }
}
