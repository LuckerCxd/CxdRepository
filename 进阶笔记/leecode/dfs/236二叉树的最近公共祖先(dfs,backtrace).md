> 236.二叉树的最近公共祖先
> 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
> 
> 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
> 
> 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
> 


 ![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png)


> 示例 1:
> 
> 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
> 输出: 3
> 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
> 示例 2:
> 
> 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
> 输出: 5
> 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
>  
> 
> 说明:
> 
> 所有节点的值都是唯一的。
> p、q 为不同节点且均存在于给定的二叉树中。


题解：
    先序 + 回溯法，找出一条从根到p，和从根到q的路径，再找这两条路中最近的相同结点，就是公共父节点了
    
回溯法结构：
    
    dfs(root){
        if(root == null)
            return;
        dfs(root.left);
        dfs(root.right);
    }

    backtrace(){
        if(到达终点，无可选项)
            记录，处理结果
        for(选择：可选项)
            作出选择
            backtarce（剩余选项）
            撤销选择
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
        TreeNode tarNode = null;
        int p;
        int q;
        boolean flagp = false;
        boolean flagq = false;
        ArrayList<TreeNode> tmpPathP = new ArrayList<>();
        ArrayList<TreeNode> tmpPathQ = new ArrayList<>();
    
        public void backtrace(TreeNode root) {
            if (root == null)
                return;
            
            // 加入路径p，当已经有结点p时，不再加入，对应撤销也是如此
            if(!flagp)
                tmpPathP.add(root);
            if(root.val == p)
                flagp = true;
            
            // 加入路径q，当已经有结点q时，不再加入，对应撤销也是如此
            if(!flagq)
                tmpPathQ.add(root);
            if(root.val == q)
                flagq = true;
            
            // 可选项
            backtrace(root.left);
            backtrace(root.right);
            
            // 撤销加入，当没有对应p，q结点时，才进行撤销
            if(!flagq)
                tmpPathQ.remove(tmpPathQ.size()-1);
            if(!flagp)
                tmpPathP.remove(tmpPathP.size()-1);
        }
    
    
    
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            this.p = p.val;
            this.q = q.val;
            backtrace(root);
            
            // 倒着找相同结点 ， 就可以了
            for(int pp=tmpPathP.size()-1;pp>=0;pp--){
                for(int qq=tmpPathQ.size()-1;qq>=0;qq--){
                    if(tmpPathP.get(pp).val == tmpPathQ.get(qq).val){
                        return tmpPathQ.get(pp);
                    }
                }
            }
    
            return null;
        }
    }