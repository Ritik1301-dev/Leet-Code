
class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // If already inside a primitive
                if (balance > 0) {
                    result.append(ch);
                }

                balance++;
            } 
            else {
                balance--;

                // If still inside a primitive
                if (balance > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}