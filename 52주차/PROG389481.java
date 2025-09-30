import java.util.*;

class Solution {

    public String solution(long n, String[] bans) {
        // 금지된 주문들을 숫자로 변환
        List<Long> banWords = new ArrayList<>();
        for (String ban : bans) {
            banWords.add(strToIndex(ban));
        }

        Collections.sort(banWords);

        long targetN = n;

        // 정렬된 금지 목록을 순회하며 targetN을 뒤로 미룸
        for (long banIndex : banWords) {
            // 목표를 한 칸 뒤로 미룸
            if (banIndex <= targetN) {
                targetN++;
            } else {
                // 그 뒤의 모든 금지 주문도 targetN보다 크므로 더 볼 필요가 없음
                break;
            }
        }

        // 다시 주문 문자열로 변환하여 반환
        return indexToStr(targetN);
    }

    private long strToIndex(String str) {
        long index = 0;
        for (int i = 0; i < str.length(); i++) {
            // 'a'를 1, 'b'를 2로 계산하여 26진법처럼 누적
            index = index * 26 + (str.charAt(i) - 'a' + 1);
        }
        return index;
    }

    private String indexToStr(long index) {
        StringBuilder sb = new StringBuilder();
        while (index > 0) {
            // 1-based 인덱스를 0-based로 변환하여 나머지를 구함
            long remainder = (index - 1) % 26;
            sb.insert(0, (char) ('a' + remainder)); // 문자를 앞에 추가
            // 다음 자릿수 계산을 위해 26으로 나눔
            index = (index - 1) / 26;
        }
        return sb.toString();
    }
}
