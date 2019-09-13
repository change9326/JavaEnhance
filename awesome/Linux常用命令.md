# 1.实用指令
## 1.1 文件目录类
### mkdir 创建目录
- 基本语法: mkdir [选项] 要创建的目录
- 常用选项: -p :创建多级目录
```bash
# 在home 目录下创建dog 目录
mkdir /home/dog
# 一次性创建多级目录
mkdir -p /home/dog/dog1
```
### cat 
`cat 查看文件内容，是以只读的方式打开。`
#### 显示行号分页浏览
```
cat -n fileName |more
```
#### 清空文件内容
```
cat /dev/null >filename
```
### more
`全屏幕的方式按页显示文本文件的内容`
```bash
more fileName
# more 模式下快捷键
space :向下翻一页
Enter :向下翻一行
Ctrl+F:向下滚动一屏
Ctrl+B:返回上一屏
=     :当前行行号
```
### less
`分屏查看文件内容`
```bash
less fileName
# less 模式下快捷键
space :向下翻一页
pageDown:向下翻一页
pageUp:向上翻一页
q     : 退出
/字串  :向下搜索，n 向下;N 向上
?字串  :向上搜索，n 向上;N 向下
```
###  重定向&追加

`>` 输出重定向 : 会将原来的文件的内容`覆盖`
`>>` 追加: 不会覆盖原来文件的内容，而是追加到文件的尾部

```bash
1. ls -l >文件 (功能描述:列表的内容写入文件 a.txt 中(覆盖写))
2. ls -al >>文件 (功能描述:列表的内容追加到文件 aa.txt 的末尾)
3. cat 文件 1 > 文件 2 (功能描述:将文件 1 的内容覆盖到文件 2)
4  echo "内容" >> 文件
```
### echo
`echo 输出内容到控制台。`
```bash
# 使用 echo 指令输出环境变量,输出当前的环境路径。
echo $PATH
```

### head
`head 用于显示文件的开头部分内容，默认情况下 head 指令显示文件的前 10 行内容`
```
1. head文件 (功能描述:查看文件头10行内容)
2. head -n 5 文件 (功能描述:查看文件头 5 行内容，5 可以是任意行数)
```
### tail
`tail 用于输出文件中尾部的内容，默认情况下 tail 指令显示文件的后 10 行内容`
```
```
### history 指令
`查看已经执行过历史命令,也可以执行历史指令`
```
history :显示所有的历史命令
history 10:显示最近使用过的10个执行
```
### ln 指令
`软链接也叫符号链接，类似于 windows 里的快捷方式，主要存放了链接其他文件的路径`
#### 基本语法
- ln -s [原文件或目录] [软链接名] (功能描述:给原文件创建一个软链接)
#### 应用实例

## 1.2 搜索查找类
### find
`在指定目录下查找文件`
#### 基本用法
- find [搜索范围] [选项]
#### 选项说明
- -name<文件名>:按照文件名查找
- -user<用户名>:查找属于指定用户名的所有文件
- -size<文件大小>:按照指定文件大小查找文件
#### 应用实例
```bash
#1.按文件名:根据名称查找/home 目录下的 hello.txt 文件
find /home -name search.txt
```
### whereis [Detail](https://jaywcjlove.gitee.io/linux-command/c/whereis.html)
`查找二进制程序、代码等相关文件路径`
### which 
`查找并显示给定命令的绝对路径`

### grep 指令和 管道符号 |
`grep 过滤查找 ， 管道符，“|”，表示将前一个命令的处理结果输出传递给后面的命令处理。`
#### 统计次数
```
# 统计次数
grep -c 'keyword' filename
grep -o 'keyword' filename |wc -l
```
## 1.3 压缩&解压类
---
# 2.vim 编辑器
## 快捷键
```
1. 拷贝当前行 yy , 拷贝当前行向下的 5 行 5yy，并粘贴(p);
2. 删除当前行 dd , 删除当前行向下的 5 行 5dd;
3. 在文件中查找某个单词 [命令行下 /关键字 ， 回车 查找 , 输入 n 就是查找下一个 ]
4. 设置文件的行号，取消文件的行号.[命令行下 : set nu 和 :set nonu]
5. 正常模式下执行,到文档的最末行[G]和最首行[gg]
6. 在一个文件中输入 "hello" ,然后又撤销这个动作，再正常模式下输入 u
7. 删除文件中所有内容[:%d]
```
## 光标移到N行
```
第一步:显示行号 :set nu
第二步:输入 20 这个数
第三步: 输入 shift+g
```
## 行首、行尾添加字符
```
行首 : %s/^/'/
行尾 : %s/$/',/
多行变一行: :0,$s/\n/ /
```
---
# 3.用户管理
---
# 4.组管理、权限管理
---
# 5.网络
## netstat
```
netstat -ntlp   //查看当前所有tcp端口·

netstat -ntulp |grep 80   //查看所有80端口使用情况·

netstat -an | grep 3306   //查看所有3306端口使用情况·
```
## telnet-测试ip端口是否连通
```
telnet [ip][port]
```
---
# 6.磁盘或内存
## df
使用df命令可以快速查看各挂载路径磁盘占用情况。
```
 $ df -h
/dev/sda14      4.6G   10M  4.4G   1% /tmp
/dev/sda11      454M  366M   61M  86% /boot
/dev/sda15       55G   18G   35G  35% /home
/dev/sda1       256M   31M  226M  12% /boot/efi
tmpfs           786M   64K  786M   1% /run/user/1000

```
## free
通过free的结果，很容易看到当前总共内存多少，剩余可用内存多少等等。
```
$ free -h
              total        used        free      shared  buff/cache   available
Mem:           7.7G        3.5G        452M        345M        3.7G        3.5G
Swap:          7.6G          0B        7.6G

```

## 常见问题
### 端口占用
```
#查看端口占用进程
sudo lsof -i :8880
#杀掉进程(pid 列)
sudo kill -9 4580
```

