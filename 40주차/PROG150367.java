class Solution {
    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            result[i] = canRepresentAsBinaryTree(numbers[i]) ? 1 : 0;
        }
        
        return result;
    }
    
    private boolean canRepresentAsBinaryTree(long number) {
        // 수를 이진수 문자열로 변환
        String binary = Long.toBinaryString(number);
        
        // 포화 이진트리 길이로 맞추기 (1, 3, 7, 15, 31, ...)
        int len = binary.length();
        int fullTreeSize = 1;
        while (fullTreeSize < len) {
            fullTreeSize = fullTreeSize * 2 + 1;  // 1 -> 3 -> 7 -> 15 -> 31 ...
        }
        
        // 앞에 0을 붙여서 포화 이진트리 크기로 만들기
        String fullBinary = "0".repeat(fullTreeSize - len) + binary;
        
        // 유효한 이진트리인지 확인
        return isValidBinaryTree(fullBinary, 0, fullTreeSize - 1);
    }
    
    private boolean isValidBinaryTree(String binary, int start, int end) {
        if (start > end) return true;
        
        int mid = (start + end) / 2;
        char root = binary.charAt(mid);
        
        // 리프 노드인 경우
        if (start == end) {
            return true;
        }
        
        // 루트가 더미 노드(0)인 경우, 서브트리도 모두 더미여야 함
        if (root == '0') {
            return isAllZero(binary, start, end);
        }
        
        // 루트가 실제 노드(1)인 경우, 좌우 서브트리 재귀 확인
        return isValidBinaryTree(binary, start, mid - 1) && 
               isValidBinaryTree(binary, mid + 1, end);
    }
    
    private boolean isAllZero(String binary, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (binary.charAt(i) == '1') {
                return false;
            }
        }
        return true;
    }
}
