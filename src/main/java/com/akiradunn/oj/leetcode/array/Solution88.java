package com.akiradunn.oj.leetcode.array;

import java.util.Arrays;

//88. 合并两个有序数组
//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
//初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
//
//
//
//示例 1：
//
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
//示例 2：
//
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
//
//
//提示：
//
//nums1.length == m + n
//nums2.length == n
//0 <= m, n <= 200
//1 <= m + n <= 200
//-109 <= nums1[i], nums2[i] <= 109
//通过次数373,905提交次数733,406
public class Solution88 {

    public static void main(String[] args) {
        Solution solution88 = new Solution();
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        solution88.merge(nums1, 3, nums2, 3);
    }

    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] res = new int[m+n];
            int i = 0;
            int j = 0;
            int k = 0;
            while(i<m && j<n){
                if(nums1[i] <= nums2[j]){
                    res[k] = nums1[i];
                    i++;
                }else{
                    res[k] = nums2[j];
                    j++;
                }
                k++;
            }
            if(i == m){
                for(;j<n;){
                    res[k++] = nums2[j++];
                }
            }else{
                for(;i<m;){
                    res[k++] = nums1[i++];
                }
            }
            for(k=0; k<m+n; k++){
                nums1[k] = res[k];
            }
        }
    }
}
