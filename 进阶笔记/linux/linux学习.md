linux:

1.创建新用户 
	useradd -m username -s /bin/bash
	passwd username
	visudo
	username ALL=(ALL) ALL
	
	
	/ 
	
	useradd username
	passwd username
	
2.SBIT粘着位作用
	针对普通用户,在有粘着位的文件夹下,每个用户只能创建删除自己的文件,
		不能删除其他用户的文件
	粘着位目前只对目录有效
	普通用户对该用户有用w和x权限,即普通用户可以在此目录拥有写入全新啊
	如果没有粘着位,因为普通用户拥有w权限,则可以删除此目录下所有文件,包括其他用户建立的文件.
	一旦赋予了粘着位,
	除了root可以删除所有文件,普通用户就算拥有w权限,
	也只能删除自己建立的文件,但是不能删除其他用户建立的文件.
	
	设置和取消粘着位
	设置粘着位
	chmod 1755 目录名
	chmod o+t 目录名
	取消粘着位
	chmod 777 目录名
	chmod o-t 目录名
3.创建文件：
	touch 
	cat >     ：覆盖
	cat >> 	  ：追加
	
4. ls [adiklRtS] 		:a隐藏 d目录 i索引结点 l详细 t时间排序 S大小排序
5. cat [nbs] filename	:n行号 b空行无号 s连续空行当一行
6. more [-10 / +10 / -s] filename ：-10显示10行 +10从第10行 -s连续空行当一行
7. less [ns] filename 	:n去掉行号不显示 s连续空行当一行
8. head [cnqv] filename :c显示前多少字节 n可以直接数字前N行 q不显示文件名 v显示文件名
	head -n 3 -v  ddddd.txt 
	head -3v ddddd.txt
9. tail [cnqvf] filename :c显示后多少字节 n可以直接数字后N行 q不显示文件名 v显示文件名
							f 动态显示文件末尾n行
10. mkdir [pm] directoryName :p多重可空文件夹 m设置权限
	mkdir -m 555 ./abc 
11. rmdir [p]	：作用 删除空文件夹，p多重可空文件夹 
12. touch [amcrdt] filename :a改变读取时间记录 m改变修改时间记录 
								r使用参考档的时间记录
								d设定时间和日期
								t设定档案时间记录
								c如果目的档案不存在 不会创建新的档案
	touch -cr atddReference  ddddd.txt 
13. rm [ifrv] file/directoryName :i逐一询问 f强制删除 r递归删 v显示执行过程
14. cd [~.-..]
15. pwd ：作用显示当前用户的工作目录
16. cp [adfilprsu] srcFileDire tarFileDire ：d若为链接则复制链接
								p文件的属性也复制 r递归复制
								a相当于pdr选项
								f强制复制
								i询问是否覆盖
								l若为硬链接，复制硬链接
								s复制成快捷方式，符号链接
								u若目标文件比源文件旧才复制过去
17. mv [bfi] srcFileDire tarFileDire :b若覆盖文件,则先备份 i询问是否覆盖 f强制覆盖
18. find 起始目录 查找条件 操作 
19. locate [选项] 字符串 
	updatedb 
	locate host.conf
20. ln [fdinsvb] src tar :作用创建一个链接 f先将同名tar删除 
							s进行软链接，可填目录，源路径填绝对路径
							i删除同名tar先询问 n进行软链接时将tar视为一般文件
							v在链接前显示文件名 b将删除的文件备份（~）
							d允许系统管理者硬链接自己的目录
21. chmod [cfhvR] mode file/directoryName : c所有者已更改才显示更改
							f无法更改也不显示错误
							h只对链接进行变更
							v显示所有者变更的详细资料
							R递归修改
	mode : [ugoa][+-=][rwxX] / n1n2n3
22. chown [cfhvR] 用户名[:组名] file/directoryName :c所有者已更改才显示更改
							f无法更改也不显示错误
							h只对链接进行变更
							v显示所有者变更的详细资料
							R递归修改
23. chgrp [fhR] 组名 file/directoryName : f取消大部分错误信息
							h只对链接进行变更
							R递归修改
24. cut [-cdf] fileName : 作用指定文件中提取特定内容并显示
							c指定提取的字符串个数 d指定分割符号
							f指定特定列的内容
	cut  -c2-5 ../ddddd.txt 
25. wc [cmlw] filename : 作用统计文件中的字，字节，行，字串
						c字节大小 m字串数目 l行数 w字数
26. sort [urnftk] fileName : 作用文本排序 r反向排序
								n数字排序 f不区分大小写 
27. tr [-cds] srcchar[] replaceChar[]： 作用对输入的字符转换,一一对应
							d:删除字符串1的所有输入字符
							s:删除所有重复字符序列，仅保留1个 
28. tar [crtuxfvwz] tar src:作用打包
		-c：创建打包文件。
		-r：追加文件到打包文件。
		-t：列出打包文件的内容。
		-u：更新打包文件内的文件。若更新的文件在没有在打包文件中，则追加。
		-x：解除打包文件的内容。
		-f：使用打包文件或设备，必选。
		-v：详细报告tar指令处理的文件信息。
		-w：每一步要询问
		-z：用gzip来压缩/解压缩文件
		-P: 使用绝对路径
	tar -czvf tar src
	tar -xzvf src -C tarDire
	
	
29. gzip [cdrtv] fileName 
		-c：将输出写到屏幕上，并保留原有文件。+cat管道
		-d：解压文件
		-r：递归查找并压缩
		-t：测试是否完整
		-v：显示压缩比
	gzip -crv
	gzip -dv
	
30. unzip [xctd] fileName
		-x：解压文件。
		-v：看压缩文件内容。
		-t：测试是否完整
		-d：将压缩文件解压到指定目录下。
		
31. echo [-ne] 字符串
		-n 换行
		-e 不换行
32. cal [-ym] 月 年 ：作用显示日历
		-m 周一为第一天
		-y 今年 
33. date 
34. clear 清屏
35. rpm RedHat Package Manager 
	rpm [icUqV] file1.rpm file2.rpm 
	i 安装 c删除 U升级 q查询 V校验
36. grep test d*
显示所有以d开头的文件中包含 test的行。
37. date +"%Y-%m-%d %H:%M:%S"                2020-03-24 23:12:19
38. echo "xxxxx" >> fgsk.txt
39. sort -nr ：按数值排序，r降序reverse ，重定向可找文件最大的结合 head,tail
40. 截取一行： head -1
41. 实时观看 tail -f xxx
42. useradd xxx  passwd xxx
43  userdel -rf xxx
44. 提取指定分隔符的指定字段：例如冒号分隔符的1,3,4字段
		cut -f1,3,4 -d":" /etc/passwd 
45. 统计行号：rpm   –qa  | wc  -l
46. at 5pm+3days /bin/ls
	crontab -e 编辑格式 f1 f2 f3 f4 f5 program f1分钟 f2小时 f3一个月第几天 f4月份 f5一个星期第几天
	crontab -l 显示格式
						f1 : a-b/2 a到b分钟每隔2分钟
47. pstree [选项] [Pid|User]  以树状显示进程。 
 -a：显示该进程的完整指令及数。 
 -p：显示时将PID号一起显示。 
 Pid：指定显示进程信息。 
 User：指定显示用户信息。 
48. kill [] pid
	1restart   9强杀   15结束
	kill -l : 显示可用的信号，用于选项中如同1，9,15
49.chkconfig 命令用于检查和设置的系统的各种服务。:重启后仍生效
	添加指定的服务，命令为：chkconfig —add 服务名 
	删除指定的服务，命令为：chkconfig —del 服务名 
	显示所有指定的服务，命令为：chkconfig —list [服务名] 
	检查指定服务的状态，命令为：chkconfig 服务名 
	改变务的运行级别，命令为：chkconfig [—level 3/4/5] 服务名 [状态on/off/reset]
50.systemctl restart/stop/start xxx.service
51.netstat -tnl | grep 23 :查看23端口
52.fdfs的配置文件部分端口设置 
/etc/fdfs/tracker.conf：
	# the tracker server port
	port=22122

	# the base path to store data and log files
	base_path=/home/caibh/fdfs

	# HTTP port on this tracker server
	http.server_port=9270
	
	
/etc/fdfs/storage.conf：
	group_name=group1
	port=23000
	base_path=/home/fdfs
	store_path0=/home/fdfs_storage
	tracker_server=191.8.1.77:22122
	http.server_port=8888
	
/etc/fdfs/client.conf：
	base_path=/home/fdfs
	tracker_server=191.8.1.77:22122
	http.tracker_server_port=9270
	
	
/etc/fdfs/mod_fastdfs.conf：
	base_path=/tmp
	tracker_server=191.8.1.77:22122
	storage_server_port=23000
	group_name=group1
	store_path0=/home/fdfs_storage
53.启动fdfs和nginx
	# fastdfs start
	/usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf restart
	/usr/bin/fdfs_storaged /etc/fdfs/storage.conf restart

	# nginx start
	/usr/local/nginx/sbin/nginx
	
查看本机的nginx的状态：
 ps -ef | grep nginx
 
 杀所有的nginx:
 pkill   -9   nginx 
 
 重启：
 ./nginx -s reload
 
 启动：
 ./nginx
执行上传：
	fdfs_test /etc/fdfs/client.conf upload 1FZ9131229-4.jpg 
随后访问：
	http://192.168.10.135/group1/M00/00/00/wKgKh16S652AdY3QAAbRrXD2_vI458_big.jpg
	
54.启动docker并设置为开机自启动
systemctl start docker
systemctl enable docker

    docker常用命令
    docker ps -a   # 查看正在运行的容器
    docker ps  # 查看容器
    docker stop mynginx  # 停止正在运行的容器
    docker start mynginx # 启动一个已经停止的容器
    docker restart mynginx # 重启一个容器
    docker rm mynginx# 删除容器
    
55.出现swp文件
使用vim -r xxx.源文件
随后删掉这个隐藏的.swp文件即可




56.ssh 知识点：


demo:

    #!/bin/bash  
    function welcome() {
    echo -n "Input your name,please: "
    read name 
    echo “Welcome $name” } 
    a=“Red Hat Enterprise Linux” 
    bc=5 
    echo RHEL5 is ${a}${bc}
    echo "Programme starts here. " welcome 
    echo "Programme ends. "
    
    执行：
    chmod u+x first.sh
    /bin/bash first.sh

引号：
双引号：相当于是没加“$a”，主要是sh名称有空白字符时要加上
单引号：相当于是/$a '$a',就相当于要字符串的$a，那非a的值
倒引号：a = `pwd` 那么a=/root 
    tar -zcvf  ~/`date +%Y-%m-%d`.tar.gz /var/ftp

参数获取：
./showpara.sh one two 
$*：这个变量括数的表。 
$@：这个变量括数的表。 
$#：这个变量括数的个数。 ->2
$0:脚本名称/位置  -> showpara.sh,不算是参数
$1:第一个参数 	-> one
$2:第二个参数  	-> two

数值计算：仅是expr / let / $((xx))才计算否则认为是字符串连接
.sh文本中：

expr 操作(数字和操作符之间要有空格)：

    int=3
    echo $int
    expr $int + 2
    expr $int \* 2
    expr $int + 2 / 2
    expr $int % 2
    expr `expr 5 + 7` / 3
    
    expr `expr 5 + 7` =  `expr 5 + 7` 
    	# =等于，!=不等于，>=大于等于，正确时输出1，不正确无输出，

let 操作(=两边不要有空格)：
    
    let a=(5+1)/2
    let a=$a+2
    echo $a
    


$((xx))操作(之后需要对结果进行echo或是赋值才行)：

let a=$(((3+1)/2))
echo $a



if-else:([ condition ]两端有空格 ,if与[]也有空格，使用``的话需要外包“”)

    #! /bin/bash
    echo -n “Which OS do you like , Linux or Windows? Please answer Linux or Windows? “
    read answer
    if [ "$answer" = "Linux" ]; then
        echo “Linux is good. “
    elif [ "$answer" = "Windows" ]; then
        echo “Windows is not bad. “
    else
        echo “sorry,$answer not recognized. Enter Linux or Windows” 
    exit 1
    fi
    exit 0

for循环：

    for num in 1 2 3 4 5 six
    do
            echo "num=$num"
    done
    
    for(( num=1; num<7; num++))
    do
            echo "num=$num"
    done

while循环：lt代表小于

    
    #! /bin/bash
    i=7
    j=10
    while true
    
    do
            if [ $i -eq 9 ];then
                    break
            fi
            echo "num1 = $i,num2=$j"
            ((i++))
    done


![](https://img-blog.csdn.net/20170729215102743?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZGFpZ3VhbHU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

函数(返回字符串)

    areyouok(){
            if [ -n "$1" ]; then
                    echo "hello,$1"
            else
                    echo "you are sb"
            fi
    }
    echo "$(areyouok)"
    areyouok
    areyouok limi
    echo "$(areyouok youi)"

case

    #! /bin/bash
    while true
    do
    echo -e "\nUse one of the following options:"
    echo "P:To display current directory"
    echo "S:To display the name of running file"
    echo "D:To display today's date and present time(如：2017-04-26 05:45:12)"
    echo "L:To see the list of files in your present working directory"
    echo "W:To see who is logged in"
    echo "I:To see the ip address of this local machine"
    echo "Q:To quit this program"
    echo -ne "\nEnter your option and hit:"
    read choice
    echo ""
    case $choice in 
    	p | P)
    	echo "current directory is : " `pwd`
    	;;
    	s | S)
    	echo "the running file is : " $0
    	;;
    	d | D)
    	echo "present time is : " `date +"%Y-%m-%d %H:%M:%S"`
    	;;
    	l | L)
    	echo "files in working directory: "
    	for str in `ls -a .`
    	do
    		echo $str
    	done
    	;;
    	w | W)
    	echo "logged user is : " `whoami`
    	;;
    	i | I)
    	echo "ip information : " `ifconfig ens33 | grep "inet "`
    	;;
    	q | Q)
    	echo "goodbye!"
    	break
    	;;
    	*)
    	echo "Not Right,you have Tip!"
    esac
    done



产生序号，for循环：
#! /bin/bash
echo -ne  "please,input a number:"
read number
for i in `seq  $number -1 1`
do
	for j in `seq $i -1 1` 
	do
		echo -ne $j" "
	done
	echo " "
done



SHELL=/bin/bash
PATH=/sbin:/bin:/usr/sbin:/usr/bin
MAILTO=root

# For details see man 4 crontabs

# Example of job definition:
# .---------------- minute (0 - 59)
# |  .------------- hour (0 - 23)
# |  |  .---------- day of month (1 - 31)
# |  |  |  .------- month (1 - 12) OR jan,feb,mar,apr ...
# |  |  |  |  .---- day of week (0 - 6) (Sunday=0 or 7) OR sun,mon,tue,wed,thu,fri,sat
# |  |  |  |  |
# *  *  *  *  * user-name  command to be executed
0 30 8 * * ? root ./start_ftp.sh
0 30 23 * * ? root ./stop_ftp.sh
0 30 8/1 * * ? root ./pingBaidu.sh
0 50 23 * * ? root ./backupFtp.sh

start_ftp.sh
#! /bin/bash
systemctl start vsftpd.service
if [ -n "`ps -ef | grep vsftpd`" ]; then
	systemctl status vsftpd.service > /var/ftp/`date +%Y-%m-%d.log`
else
	echo "start ftp error" | sendmail -s "start ftp error" root@Master
fi	

stop_ftp.sh
#! /bin/bash
systemctl stop vsftpd.service	

pingBaidu.sh
#! /bin/bash
ping -c 4 www.baidu.com >> /var/ftp/`date +%Y-%m-%d.log`

backupFtp.sh
#! /bin/bash
tar -zcvPf  ~/`date +%Y-%m-%d`.tar.gz /var/ftp
chown -R root:root 2020-05-01.tar.gz
chmod 400 2020-05-01.tar.gz
rm -rf /var/ftp
mkdir /var/ftp


编译c
gcc main.c ./functions/mytool1.c ./functions/mytool2.c -o main -I ./functions


$ cat makefile  
main.exe2: main.o mytool1.o mytool2.o
	gcc main.o mytool1.o mytool2.o -o main.exe2
main.o: main.c 
	gcc -c main.c -I /root/functions  
mytool1.o: /root/functions/mytool1.c /root/functions/mytool1.h
	gcc -c /root/functions/mytool1.c   
mytool2.o: /root/functions/mytool2.c /root/functions/mytool2.h
	gcc -c /root/functions/mytool2.c 
