import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        Set<String> result = new HashSet<>();

        int leftRemove = 0;
        int rightRemove = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRemove++;
            } 
            else if (ch == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                } 
                else {
                    rightRemove++;
                }
            }
        }

        backtrack(s, 0, 0, leftRemove, rightRemove, "", result);

        return new ArrayList<>(result);
    }

    private void backtrack(
            String s,
            int index,
            int balance,
            int leftRemove,
            int rightRemove,
            String current,
            Set<String> result) {

        if (balance < 0) {
            return;
        }

        if (index == s.length()) {

            if (balance == 0 &&
                leftRemove == 0 &&
                rightRemove == 0) {

                result.add(current);
            }

            return;
        }

        char ch = s.charAt(index);

        // Remove '('
        if (ch == '(' && leftRemove > 0) {

            backtrack(
                s,
                index + 1,
                balance,
                leftRemove - 1,
                rightRemove,
                current,
                result
            );
        }

        // Remove ')'
        if (ch == ')' && rightRemove > 0) {

            backtrack(
                s,
                index + 1,
                balance,
                leftRemove,
                rightRemove - 1,
                current,
                result
            );
        }

        // Keep character
        if (ch == '(') {

            backtrack(
                s,
                index + 1,
                balance + 1,
                leftRemove,
                rightRemove,
                current + ch,
                result
            );

        } 
        else if (ch == ')') {

            if (balance > 0) {

                backtrack(
                    s,
                    index + 1,
                    balance - 1,
                    leftRemove,
                    rightRemove,
                    current + ch,
                    result
                );
            }

        } 
        else {

            // Letter
            backtrack(
                s,
                index + 1,
                balance,
                leftRemove,
                rightRemove,
                current + ch,
                result
            );
        }
    }
}