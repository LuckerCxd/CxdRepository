    129.求根到叶子节点数字之和
    给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
    
    例如，从根到叶子节点路径 1->2->3 代表数字 123。
    
    计算从根到叶子节点生成的所有数字之和。
    
    说明: 叶子节点是指没有子节点的节点。
    
    示例 1:
    
    输入: [1,2,3]
        1
       / \
      2   3
    输出: 25
    解释:
    从根到叶子节点路径 1->2 代表数字 12.
    从根到叶子节点路径 1->3 代表数字 13.
    因此，数字总和 = 12 + 13 = 25.
    示例 2:
    
    输入: [4,9,0,5,1]
        4
       / \
      9   0
     / \
    5   1
    输出: 1026
    解释:
    从根到叶子节点路径 4->9->5 代表数字 495.
    从根到叶子节点路径 4->9->1 代表数字 491.
    从根到叶子节点路径 4->0 代表数字 40.
    因此，数字总和 = 495 + 491 + 40 = 1026.

题解：dfs + 回溯

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
        int resultSum = 0;
        int tmpPathSum = 0;
        public void backtrace(TreeNode root){
            if(root == null ) {
                return;
            }
            //作出选择，被提前了，因为先要使用root.val
            tmpPathSum *= 10;
            tmpPathSum += root.val;
            
             //回溯的无可选项，终点情况
            if(root.right == null && root.left==null){
                resultSum += tmpPathSum;
            }
            
            //对应for可选，因为是二叉树，所以只有左右子树
            backtrace(root.left);
            backtrace(root.right);

            //撤销选择
            tmpPathSum -= root.val;
            tmpPathSum /= 10;
        }
        public int sumNumbers(TreeNode root) {
            backtrace(root);
            return resultSum;
        }
    }