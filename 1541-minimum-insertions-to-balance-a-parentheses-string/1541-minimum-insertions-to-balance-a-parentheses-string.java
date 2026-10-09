
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // If the next character is also ')',
                // they form a pair of closing brackets.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    if (open > 0) {
                        open--;
                    } else {
                        insertions++;
                    }
                    i++; // Skip the second ')'
                } else {
                    // Only one ')' is available, so insert another ')'
                    insertions++;

                    if (open > 0) {
                        open--;
                    } else {
                        insertions++;
                    }
                }
            }
        }

        // Each remaining '(' needs two ')'
        insertions += open * 2;

        return insertions;
    }
}
