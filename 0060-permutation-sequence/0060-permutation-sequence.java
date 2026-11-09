
class Solution {
    public String getPermutation(int n, int k) {

        StringBuilder ans = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        int fact = 1;

        for (int i = 1; i < n; i++) {
            fact *= i;
        }

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        k--;

        while (true) {
            ans.append(list.get(k / fact));
            list.remove(k / fact);

            if (list.size() == 0) {
                break;
            }

            k = k % fact;
            fact = fact / list.size();
        }

        return ans.toString();
    }
}
