class Solution {
    public String countAndSay(int n) {
        String result = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();

            int count = 1;
            char prev = result.charAt(0);

            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == prev) {
                    count++;
                } else {
                    next.append(count).append(prev);
                    prev = result.charAt(j);
                    count = 1;
                }
            }

            // Append the last group
            next.append(count).append(prev);

            result = next.toString();
        }

        return result;
    }
}