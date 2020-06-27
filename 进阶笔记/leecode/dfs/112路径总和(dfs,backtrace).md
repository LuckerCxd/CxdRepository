> 112.路径总和
> 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
> 
> 说明: 叶子节点是指没有子节点的节点。
> 
> 示例: 
> 给定如下二叉树，以及目标和 sum = 22，
    
                  5
                 / \
                4   8
               /   / \
              11  13  4
             /  \      \
            7    2      1

> 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。

题解：
    
dfs + 回溯
dfs先序遍历二叉树框架：
    
    dfs(root){
        if(root == null)
            return;
        dfs(root.left);
        dfs(root.right);
    }

回溯法框架：
    
    backtrace(可选项){
        if(到达终点了，无可选状态){
            作记录，处理结果
        }
        for(选项 ： 可选择){
            作出选择；
            backtrace（作出选择后的可选项）
            撤销选择；
        }
    }

先序 + 回溯结合：（本题无可选项是找出叶子的时候）
    
    backtrace(root){
        if(root == null)
            return;
        //记录当前路径
        if(root.left == null && root.right==null){
            
        }
    }

answer:
    
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        int pathSum = 0;
        int target = 0;
        boolean flag = false;
        public void backtrace(TreeNode root){
            // 提前结束，当发现有满足的了，直接跑路，一顿return溜走
            if(root == null || flag == true) {
                return;
            }
            
            //作出选择
            pathSum += root.val;
            
            // 回溯的无可选项，终点叶子条件
            if(root.right == null && root.left==null){
                if(target == pathSum)
                    flag = true;
                    
            }

            // 对应for循环，但这是二叉树，只有左右子树
            backtrace(root.left);
            backtrace(root.right);

            //撤销选择
            pathSum -= root.val;
        }
        public boolean hasPathSum(TreeNode root, int sum) {
            target = sum;
            backtrace(root);
            return flag;
        }
    }