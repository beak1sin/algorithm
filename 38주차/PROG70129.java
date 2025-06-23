class Solution {
    public int[] solution(String s) {
        int[] answer = new int [2];
        int answer1 = 0, answer2 = 0;
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                c++;
            }
        }
        answer1++;
        answer2 += s.length() - c;
        String b = Integer.toBinaryString(c);
        while (!b.equals("1")) {
            c = 0;
            for (int i = 0; i < b.length(); i++) {
                if (b.charAt(i) == '1') {
                    c++;
                }
            }
            answer1++;
            answer2 += b.length() - c;
            b = Integer.toBinaryString(c);
        }
        answer[0] = answer1;
        answer[1] = answer2;
        return answer;
    }
}
