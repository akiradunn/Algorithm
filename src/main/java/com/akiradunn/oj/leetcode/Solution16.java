package com.akiradunn.oj.leetcode;
import java.util.Arrays;
//16. 最接近的三数之和
//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
//
//
//
//示例：
//
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//
//
//提示：
//
//3 <= nums.length <= 10^3
//-10^3 <= nums[i] <= 10^3
//-10^4 <= target <= 10^4
//通过次数201,782提交次数439,478
public class Solution16 {
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int res = Integer.MAX_VALUE;
            int rt = 0;
            //暴力解
            for(int i=0; i<nums.length-2; i++){
                for(int j=i+1; j<nums.length-1; j++){
                    for(int k=j+1; k<nums.length; k++){
                        int temp = nums[i]+nums[j]+nums[k];
                        if(res > Math.abs(temp - target)){
                            res = Math.abs(temp - target);
                            rt = temp;
                        }
                    }
                }
            }
            return rt;
        }
    }
}
