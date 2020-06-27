> 面试题16.19. 水域大小
> 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。

> 示例：
> 
> 输入：
> [
>   [0,2,1,0],
>   [0,1,0,1],
>   [1,1,0,1],
>   [0,1,0,1]
> ]
> 输出： [1,2,4]
> 提示：
> 
> 0 < len(land) <= 1000
> 0 < len(land[i]) <= 1000

题解：
> 
> dfs套路
> dfs(): 边界和不可走的状况return 0 -> 设置走过的为不可走状态 -> 若干个方向执行dfs并处理结果
> main():双重循环，当前节点可以走时，就执行dfs()并处理结果 

dfs结构：
    
    dfs(int i,int j){
        if(No Permission status){
            return 0;
        }
        update statu();
        dfs(i+x1,j+y1);
        dfs(i+x2,j+y2);
        ....   
    }

answer:
    
    class Solution {
        public int[] pondSizes(int[][] land) {
            int rowLimit = land.length;
            int colomnLimit = land[0].length;
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<rowLimit;i++){
                for(int j=0;j<colomnLimit;j++){
                    if(land[i][j]==0){
                        list.add(dfs(i, j,rowLimit,colomnLimit,land));
                    }
                }
            }
            int[] a = new int[list.size()];
            Arrays.sort(a);
            int length = a.length;
            for(int i=0;i<length;i++){
                a[i] = list.get(i);
            }
            return a;
        }
        public static int dfs(int i, int j, int rowLimit, int colomnLimit,
                               int[][] land) {
    
            if (i < 0 || j < 0 || i >= rowLimit || j >= colomnLimit || land[i][j] != 0)
                return 0;
            land[i][j] = 1;
            int count = 1;
            count += dfs(i + 1, j, rowLimit, colomnLimit, land);
            count +=dfs(i - 1, j, rowLimit, colomnLimit, land);
            count +=dfs(i, j + 1, rowLimit, colomnLimit, land);
            count +=dfs(i, j - 1, rowLimit, colomnLimit, land);
            count +=dfs(i-1, j - 1, rowLimit, colomnLimit, land);
            count +=dfs(i+1, j -1, rowLimit, colomnLimit, land);
            count +=dfs(i+1, j + 1, rowLimit, colomnLimit, land);
            count +=dfs(i-1, j + 1, rowLimit, colomnLimit, land);
            return count;
        }
    }