> 面试题34. 二叉树中和为某一值的路径
> 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
> 
>  
> 
> 示例:
> 给定如下二叉树，以及目标和 sum = 22
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

dfs（先序遍历 组合 回溯）



先序遍历结构：

    dfs(Node node){
        if(node == null)
            return;
        dfs(node.left);
        dfs(node.right);
    }

组合回溯法：
    
    dfs(Node node){
        if(node == null)
            return;
        do();
        
        dfs(node.left);
        dfs(node.right);
        undo();
    }

answer:
    
    class Solution {
        List<Integer> path = new ArrayList<Integer>();
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            dfs(root,sum);
            return paths;
        }
        public void dfs(TreeNode root,int sum) {
            if(root == null) return ;
            path.add(root.val);
            sum -= root.val;
            if(sum == 0 && root.left == null && root.right ==null)
                paths.add(new ArrayList<Integer>(path));
            dfs(root.left,sum);
            dfs(root.right,sum);
            path.remove(path.size()-1);
        }
    }