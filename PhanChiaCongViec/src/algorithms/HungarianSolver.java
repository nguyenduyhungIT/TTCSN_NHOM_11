/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;
import java.util.Arrays;

public class HungarianSolver implements Solver {

    @Override
    public int solve(int[][] costMatrix, int[] assignment) {
        int n = costMatrix.length;

        // Các nhãn của công nhân và công việc
        int[] labelByWorker = new int[n];
        int[] labelByJob = new int[n];
        int[] minSlackWorker = new int[n];
        int[] minSlackValue = new int[n];

        // Các mảng lưu kết quả
        int[] matchWorkerByJob = new int[n];
        int[] matchJobByWorker = new int[n];
        Arrays.fill(matchWorkerByJob, -1);
        Arrays.fill(matchJobByWorker, -1);

        // Khởi tạo nhãn
        for (int i = 0; i < n; i++) {
            labelByWorker[i] = Arrays.stream(costMatrix[i]).min().orElse(0);
        }

        // Xử lý từng công nhân
        for (int worker = 0; worker < n; worker++) {
            Arrays.fill(minSlackValue, Integer.MAX_VALUE);
            boolean[] visitedWorkers = new boolean[n];
            boolean[] visitedJobs = new boolean[n];
            int[] parentJob = new int[n];
            Arrays.fill(parentJob, -1);

            int currentWorker = worker;

            while (true) {
                visitedWorkers[currentWorker] = true;

                int committedJob = -1;
                int delta = Integer.MAX_VALUE;

                // Tính toán slack
                for (int job = 0; job < n; job++) {
                    if (!visitedJobs[job]) {
                        int slack = costMatrix[currentWorker][job] - labelByWorker[currentWorker] - labelByJob[job];
                        if (slack < minSlackValue[job]) {
                            minSlackValue[job] = slack;
                            minSlackWorker[job] = currentWorker;
                        }
                        if (minSlackValue[job] < delta) {
                            delta = minSlackValue[job];
                            committedJob = job;
                        }
                    }
                }

                // Cập nhật nhãn
                for (int j = 0; j < n; j++) {
                    if (visitedJobs[j]) {
                        labelByWorker[matchWorkerByJob[j]] += delta;
                        labelByJob[j] -= delta;
                    } else {
                        minSlackValue[j] -= delta;
                    }
                }

                // Đánh dấu công việc
                visitedJobs[committedJob] = true;
                int matchedWorker = matchWorkerByJob[committedJob];

                if (matchedWorker == -1) {
                    // Truy vết ngược để cập nhật kết quả gán
                    while (committedJob != -1) {
                        int parentWorker = minSlackWorker[committedJob];
                        int temp = matchJobByWorker[parentWorker];
                        matchWorkerByJob[committedJob] = parentWorker;
                        matchJobByWorker[parentWorker] = committedJob;
                        committedJob = temp;
                    }
                    break;
                } 
                else {
                    currentWorker = matchedWorker;
                }
            }
        }

        // Tính tổng chi phí tối thiểu và cập nhật assignment
        int minCost = 0;
        for (int job = 0; job < n; job++) {
            if (matchWorkerByJob[job] != -1) {
                assignment[matchWorkerByJob[job]] = job;
                minCost += costMatrix[matchWorkerByJob[job]][job];
            }
        }

        return minCost;
    }

}



