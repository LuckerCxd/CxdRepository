> 113.路径总和 II
> 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
> 
> 说明: 叶子节点是指没有子节点的节点。
> 
> 示例:
> 给定如下二叉树，以及目标和 sum = 22，
> 
>               5
>              / \
>             4   8
>            /   / \
>           11  13  4
>          /  \    / \
>         7    2  5   1
> 返回:
> 
> [
>    [5,4,11,2],
>    [5,8,4,5]
> ]


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
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> paths = new ArrayList<>();
        int target = 0;
        int pathSum = 0;
        public void backtrace(TreeNode root){
            if(root == null ) {
                return;
            }
            
            //作出选择
            pathSum += root.val;
            path.add(root.val);
            
            // 回溯的无可选项，终点叶子条件
            if(root.right == null && root.left==null){
                if(target == pathSum)
                    paths.add(new ArrayList<>(path));
            }
            
            // 对应for循环，但这是二叉树，只有左右子树
            backtrace(root.left);
            backtrace(root.right);
            
            //撤销选择
            pathSum -= root.val;
            path.remove(path.size()-1);
        }
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            target = sum;
            backtrace(root);
            return paths;
        }
    }