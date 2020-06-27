    257.二叉树的所有路径
    给定一个二叉树，返回所有从根节点到叶子节点的路径。
    
    说明: 叶子节点是指没有子节点的节点。
    
    示例:
    
    输入:
    
       1
     /   \
    2     3
     \
      5
    
    输出: ["1->2->5", "1->3"]
    
    解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

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
        public void backtrace(TreeNode root){
            if(root == null ) {
                return;
            }
            path.add(root.val);
            if(root.right == null && root.left==null){
                paths.add(new ArrayList<>(path));
            }
            backtrace(root.left);
            backtrace(root.right);
            path.remove(path.size()-1);
            
        }
        public List<String> binaryTreePaths(TreeNode root) {
            backtrace(root);
            List<String> target = new ArrayList<>();
            StringBuilder tmp = new StringBuilder();
            int pathsSize = paths.size();
            int pathSize = 0;
            for(int i=0;i<pathsSize;i++){
                path = paths.get(i);
                pathSize = path.size();
                for(int j=0;j<pathSize;j++){
                    tmp.append(path.get(j));
                    tmp.append("->");
                }
                tmp.delete(tmp.length()-2,tmp.length());
                target.add(tmp.toString());
                tmp = new StringBuilder();
            }
            return target;
        }
    }