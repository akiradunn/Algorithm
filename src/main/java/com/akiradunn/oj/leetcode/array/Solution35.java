package com.akiradunn.oj.leetcode.array;
//35. 搜索插入位置
//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//
//你可以假设数组中无重复元素。
//
//示例 1:
//
//输入: [1,3,5,6], 5
//输出: 2
//示例 2:
//
//输入: [1,3,5,6], 2
//输出: 1
//示例 3:
//
//输入: [1,3,5,6], 7
//输出: 4
//示例 4:
//
//输入: [1,3,5,6], 0
//输出: 0
//通过次数392,665提交次数834,875
public class Solution35 {
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int l = 0;
            int r = nums.length-1;
            while(l <= r){
                int mid = (l + r) / 2;
                if(nums[mid] == target){
                    return mid;
                }else if(l == r){
                    return nums[mid] > target ? l : (l+1);
                }else if(nums[mid] > target){
                    r--;
                }else if(nums[mid] < target){
                    l++;
                }
            }
            return -1;
        }
    }
}
