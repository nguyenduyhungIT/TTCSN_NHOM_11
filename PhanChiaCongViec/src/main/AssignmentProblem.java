/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import algorithms.*;
import java.util.Scanner;

/**
 *
 * @author HUNG NGUYEN
 */
public class AssignmentProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //System.out.print("Nhap so cong nhan: ");
        int workers = scanner.nextInt(); // Nhap so luong cong nhan

        //System.out.print("Nhap so cong viec: ");
        int jobs = scanner.nextInt(); // Nhap so luong cong viec

        if (workers != jobs) {
            System.out.println("So luong cong nhan va cong viec phai bang nhau.");
            return;
        }

        int[][] costMatrix = new int[workers][jobs];
       // System.out.println("Nhap cac phan tu ma tran chi phi (Theo tung hang):");
        for (int i = 0; i < workers; i++) {
            for (int j = 0; j < jobs; j++) {
                costMatrix[i][j] = scanner.nextInt(); // Nhap cac gia tri cua ma tran chi phi
            }
        }

        Solver[] solvers = {
            new DynamicProgrammingSolver(), // Khoi tao solver Dynamic Programming
            new HungarianSolver() // Khoi tao solver Hungarian
        };

        String[] solverNames = {"Dynamic Programming", "Hungarian"}; // Ten cac solver

        for (int i = 0; i < solvers.length; i++) {
            int[] assignment = new int[workers]; // Mang assignment de luu ket qua phan cong
            int result = solvers[i].solve(costMatrix, assignment); // Giai bai toan phan cong
            System.out.println("\n" + solverNames[i] + ":");
            System.out.println("Chi phi toi thieu: " + result); // In ra chi phi toi thieu
            for (int j = 0; j < assignment.length; j++) {
                System.out.println("Cong nhan " + (j + 1) + " lam cong viec " + (assignment[j] + 1)); // In ra ket qua phan cong
            }
        }

        scanner.close(); // Dong Scanner de tranh ro ri tai nguyen
    }
}
