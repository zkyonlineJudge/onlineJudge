# onlineJudge

这是一个很厉害的课程设计。

## 主要功能

*`游客`：浏览题目，查看评论区，查看公告，注册，查看排行，登陆<br />
*`普通用户`：练习，天梯，评论，查看他人主页<br />
*`管理员`：管理用户，管理习题，管理公告，管理评论，管理天梯<br />

## 使用方式

将项目`clone`到本地，将`jslib.zip`压缩文件解压至本目录下即可<br />
在`eclipse`中import已存在的maven文件<br / >
在`配置的xml`中修改数据库的账号密码<br />
运行`tomcat`即可<br />

## 项目难点

*  angularJS: 由于前端人员是个新手，也是第一次使用angularjs做项目，故前端代码并不成熟。<br />
*  分页：数据量并不多，故由前端分页。
*  在线代码编辑器：这里引用的是[codeMirror](http://codemirror.net/)插件,具体配置查看官方文档即可。<br />
*  高亮：对现有的4种语言高亮显示，这里四种语言分别为`python` `c` `c++` `java`，这里使用的是通过js操作改变`codeMirror`的`type`来实现<br />
*  运行：后端获取`用户输入的代码`并做编译运行处理。这里是将环境部署在`linux`上.以java为例，将用户的代码以`.java`文件保存在`linux`上，然后用`后台语言，这里是JAVA`操作`cmd命令`，对`.java文件`进行编译，运行，获得结果后，返回数据给前端
*  显示结果：由于请求有时间，故需要不断的`ajax请求`，直到完成请求。由于ajax请求是异步请求，故需要解决线程混乱的问题。

## 项目结构

* 前端：使用到了`jquery`+`bootstrap`+`jquery.cookie`+`angularjs`+`codeMirror`+`switch`
	* [jquery](http://jquery.com/)
	* [bootstrap](http://v3.bootcss.com/)
	* [jquery.cookie](http://plugins.jquery.com/cookie/)
	* [angularjs](https://docs.angularjs.org/)
	* [codeMirror](http://codemirror.net/)
	* switch为不知名大神开发
* 后台：未知。
