> 47.全排列 II
> 给定一个可包含重复数字的序列，返回所有不重复的全排列。
> 
> 示例:
> 
> 输入: [1,1,2]
> 输出:
> [
>   [1,1,2],
>   [1,2,1],
>   [2,1,1]
> ]


题解：
回溯法框架：

    backtrace(可选项){
        if(已到达终点,无可选项){
            record记录信息
            return;
        }   
        for(选项 ： 可选项){
            作出选择；
            backtrace(作出选择后的可选项);
            撤销选择;
        }
    }

answer:
    
    class Solution {
        List<Integer> path = new ArrayList();
        List<Integer> index = new ArrayList();
        List<List<Integer>> paths = new ArrayList();
    
        public void backtrace(int[] nums){
            if(path.size() == nums.length){
                //当前无可选项了，path已经有3个数了
                if(!paths.contains(path)){
                    paths.add(new ArrayList<>(path));
                }
                return;
            }
            for(int i=0;i<nums.length;i++){
                if(index.contains(i))
                    continue;
                //作一个indexList，来排除
                path.add(nums[i]);
                index.add(i);
                //作出选择，将nums[i]添加到path中
                backtrace(nums);
                path.remove(path.size()-1);
                index.remove(index.size()-1);
                //作出撤销，将nums[i]从path末尾移出来
            }
        }
        public List<List<Integer>> permuteUnique(int[] nums) {
            backtrace(nums);
            return paths;
        }
    }