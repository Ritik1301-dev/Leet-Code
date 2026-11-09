
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // {finishTime, serverIndex}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );

        int[] count = new int[k];
        int max = 0;

        for (int i = 0; i < arrival.length; i++) {

            int time = arrival[i];

            // Release servers whose tasks are completed
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            // Find the smallest available server >= i % k
            int preferred = i % k;

            Integer server = available.ceiling(preferred);

            // Wrap around if no such server exists
            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            count[server]++;
            max = Math.max(max, count[server]);

            busy.offer(new int[]{
                time + load[i], server
            });
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
