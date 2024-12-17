/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

/**
 *
 * @author HUNG NGUYEN
 */
import java.util.Arrays;

public class DynamicProgrammingSolver implements Solver {

    @Override
    public int solve(int[][] costMatrix, int[] assignment) {
        int n = costMatrix.length; // Số lượng công việc và công nhân
        int[] dp = new int[1 << n]; // Khởi tạo mảng dp với 2^n trạng thái
        int[] parent = new int[1 << n]; // Lưu trạng thái công việc tối ưu
        Arrays.fill(dp, -1);
        Arrays.fill(parent, -1);
        Arrays.fill(assignment, -1);

        // Gọi hàm tìm chi phí tối thiểu
        int minCost = findMinCost(costMatrix, dp, parent, 0, 0, n);

        // Truy vết ngược để tìm mảng assignment
        int mask = 0;
        for (int person = 0; person < n; person++) {
            int job = parent[mask]; // Lấy công việc gán từ trạng thái hiện tại
            assignment[person] = job; // Gán công việc cho nhân viên
            mask |= (1 << job); // Cập nhật trạng thái
        }

        return minCost; // Trả về chi phí tối thiểu
    }

    private int findMinCost(int[][] costMatrix, int[] dp, int[] parent, int mask, int person, int n) {
        if (person == n) { // Nếu tất cả các công việc đã được giao
            return 0; // Không còn chi phí nào thêm
        }
        if (dp[mask] != -1) { // Nếu trạng thái này đã được tính toán
            return dp[mask]; // Trả về giá trị đã lưu
        }
        int minCost = Integer.MAX_VALUE; // Khởi tạo chi phí tối thiểu
        int bestJob = -1; // Khởi tạo công việc tối ưu
        for (int job = 0; job < n; job++) {
            if ((mask & (1 << job)) == 0) { // Nếu công việc chưa được giao
                int cost = costMatrix[person][job]
                        + findMinCost(costMatrix, dp, parent, mask | (1 << job), person + 1, n); // Tính toán chi phí
                if (cost < minCost) { // Nếu chi phí tính toán ít hơn chi phí tối thiểu hiện tại
                    minCost = cost; // Cập nhật chi phí tối thiểu
                    bestJob = job; // Cập nhật công việc tối ưu
                }
            }
        }
        dp[mask] = minCost; // Lưu kết quả vào mảng dp
        parent[mask] = bestJob; // Lưu công việc tối ưu vào mảng parent
        return minCost; // Trả về chi phí tối thiểu
    }

}
