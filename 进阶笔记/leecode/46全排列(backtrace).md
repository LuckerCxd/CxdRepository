> 46.全排列
> 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
> 
> 示例:
> 
> 输入: [1,2,3]
> 输出:
> [
>   [1,2,3],
>   [1,3,2],
>   [2,1,3],
>   [2,3,1],
>   [3,1,2],
>   [3,2,1]
> ]

题解：
回溯法，框架：
    
    backtrace(可选项){
        if(当前已经无可选项了){
            recode记录答案；
            return;
        }
        for(选择：可选项){
            作出选择
            backtrace(作出选择后剩余的可选项)
            撤销选择
        }
        
    }

answer:
    
    class Solution {
        ArrayList<Integer> path = new ArrayList();
        List<List<Integer>> paths = new ArrayList();
        
        public void backtrace(int[] nums){
            if(path.size() == nums.length){ 
                //当前无可选项了，path已经有3个数了
                paths.add(new ArrayList<>(path));
                return;
            }
            for(int i=0;i<nums.length;i++){
                    if(path.contains(nums[i]))
                        continue;
                    //作一个排除，如果包含就舍弃，因为题目是不重复的，
                    //如果说有重复，那就再搞一个下标List记录来排除
                    path.add(nums[i]);
                    //作出选择，将nums[i]添加到path中
                    backtrace(nums);
                    path.remove(path.size()-1);
                    //作出撤销，将nums[i]从path末尾移出来
                }
        }
        public List<List<Integer>> permute(int[] nums) {
            backtrace(nums);
            return paths;
        }
    }
