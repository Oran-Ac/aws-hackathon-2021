
#
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/head.png)
<h4 align="center">售全球(So,global)：基于亚马逊云服务的后台管理系统</h4>


<p align="center">
    <a href="https://github.com/Oran-Ac/aws-hackathon-2021/commits/master">
    <img src="https://img.shields.io/github/last-commit/Oran-Ac/aws-hackathon-2021.svg?style=flat-square&logo=github&logoColor=white"
         alt="GitHub last commit">

</p>
<p align="center">
    <a href="\README_EN.md">ENGLISH Readme.md</a> •
     <a href="#computer-%E4%BD%BF%E7%94%A8">碎碎念</a> •
  <a href="#computer-%E4%BD%BF%E7%94%A8">使用</a> •
  <a href="#rocket-界面介绍">界面介绍</a> •
  <a href="#page_with_curl-使用技术">使用技术</a> •
  <a href="#%EF%B8%8F-架构设计">架构设计</a> •
  <a href="#-部署指南">部署指南</a> •
  <a href="#-功能特性">功能特性</a> •
  <a href="#贡献名单">贡献名单</a> 
</p>

## 碎碎念
项目进入了决赛并没有获奖。不过还是很不甘心呀，为自己也为别的队伍。<br>
在一场以技术为主要核心的比赛里，得奖的理由可以是“站在用户视角的前端界面”.<br>
其实所谓的前端界面，是element组件的固定框架格式。<br>
原来<br>
比赛的重点不在于全球，不在于卖家用中文发布描述，如何让日本卖家看懂，不在于货币结算单位不一的问题，在于“好看的后台管理系统”。<br>
原来<br>
比赛的重点不在于使用亚马逊云服务，不在于新技术的学习，你用local host演示或者你播放视频也能完成得奖。<br>
所以,以后比赛的人们啊，有好看的界面，宣传用户友好就行<br>
这样看，也许是感觉是因为我们没有得奖，而产生的小气心理。<br>
但我们想推荐一个我们全组公认的[无冕之王项目](https://github.com/siqi-ontheway/aws-hackathon-2021)<br>
我们甚至觉得在他们的项目里，感觉到了架构和真正企业级应用的体现。<br>
但他们甚至没有得奖!<br>
最后，人生不如意十有八九，山高水长，我们顶峰相见。

## :computer: 使用
想要一次发布全球可售？想要精准向不同语言者传递商品信息？想要一键更改货币单位，实现汇率实时可查？，“售全球(So,global)”后台管理器满足你的所有需求。点击：[“售全球”](http://soglobal-env.eba-gyvuqgty.us-east-1.elasticbeanstalk.com/)快速体验。






## :rocket: 界面介绍
“售全球(So,global)”界面简洁，以用户友好的方式，提供优质的服务，最大程度降低用户学习成本。
### 注册/登录界面
- “售全球(So,global)”注册与登录都采用邮箱验证方式。
- 用户可以在右上角选择语言，以调整系统语言。
- 在注册时，要求用户选择国家，以方便后续订单管理系统的语言设置。

![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/login.gif)

### 后台管理界面
在“售全球(So,global)”登录后，会出现后台管理界面。
- 左侧为商品信息，右侧为商品的订单信息。下方为功能组件。
- 用户点击左侧商品后，右侧会出现的相应订单，可进行相应的操作。点击查看<a href="#-功能特性">操作指南</a>。

*B站会发布完整演示视频，等通过后会附上链接！*


## :page_with_curl: 使用技术
- AWS Elastic Beanstalk：创建Java环境，一键部署jar包后端服务。
- AWS Lambda：
  - 进行实时汇率计算。
  - 与Amazone Translate配合提供高质量翻译结果。  
- AWS S3：
  - 作为图床存取用户商品信息。
  - 存放Elastic Beanstalk需要使用的配置文件和历史版本。
  - 存放页面框架翻译。
- AWS IAM：资源访问权限管理。
- AWS EC2：存放Web前端服务。
- AWS Amplify API：使得JavaScript能调用AWS Lambda函数。
- AWS Cognito：管理身份池，赋予前端权限。    


## 🏗️ 架构设计
•<a href="#%EF%B8%8F-云服务架构">云服务架构</a> •
  <a href="#%EF%B8%8F-后端架构">后端架构</a> •
  <a href="#-数据库架构">数据库架构</a> •
### ☁️ 云服务架构
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/structure.png)
### 🛎️ 后端架构
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/Back-end%20frame.png)
### 💿 数据库架构
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/db.png)

## 📦 部署指南


### 云上部署
- 授予Lambda操作相应AWS S3 Bucket库的权限。
- 授予Lambda调用AWS Translate的权限。
- 将后端代码编译成jar包
- 打开AWS Elastic beanstalk，创建一个Java 8环境，并上传相应jar包，如果环境检测为绿色则表示状态良好，可以使用。<br>
```
❗ 在使用AWS Elastic beanstalk创建环境配置时，需要增加环境变量PORT并设置为5000。Spring Boot默认端口为8080，而AWS自动检测端口为5000。
```

## 🔨 功能特性
### 实现特性


<table border="0" cellpadding="0" cellspacing="0" width="591" style="border-collapse:
 collapse;table-layout:fixed;width:443pt" >
 <colgroup><col width="181" style="mso-width-source:userset;mso-width-alt:6371;width:136pt">
 <col class="xl637116" width="410" style="mso-width-source:userset;mso-width-alt:
 14449;width:307pt">
 </colgroup><tbody><tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" width="181" style="height:20.0pt;width:136pt" align="center">对象</td>
  <td class="xl647116" width="410" style="border-left:none;width:307pt" align="center">操作</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td rowspan="2" height="54" class="xl647116" style="height:40.0pt;border-top:none" align="center">用户</td>
  <td class="xl647116" style="border-top:none;border-left:none" align="center">登录</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none" align="center">注册</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td rowspan="7" height="189" class="xl647116" style="height:140.0pt;border-top:
  none" align="center">商品</td>
  <td class="xl647116" style="border-top:none;border-left:none" align="center">添加商品信息</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">更新商品信息</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">删除商品</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">检索商品</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">一键在多国发布某一商品</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">一键在多个地区下架某商品</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">一键下架某地区的多个商品</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td rowspan="4" height="108" class="xl647116" style="height:80.0pt;border-top:none">订单</td>
  <td class="xl647116" style="border-top:none;border-left:none">检索某一商品所有订单</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">检索某一顾客所有订单</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">以订单金额为条件检索</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">获得某时间段内商品最畅销的三个国家</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" width="181" style="height:20.0pt;width:136pt" align="center">语言</td>
  <td class="xl647116" width="410" style="border-left:none;width:307pt" align="center">一键翻译</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" width="181" style="height:20.0pt;width:136pt" align="center">金额</td>
  <td class="xl647116" width="410" style="border-left:none;width:307pt" align="center">实时汇率换算</td>
 </tr>

</tbody></table>

### 亮点
🌟 汇率爬取：在进行汇率实时转换时，我们发现能够动态转换汇率的网站都有反爬虫机制。最终我们采用每次向百度搜索应答，爬取其根据知识图谱返回的结果<br>
🌟 较稳定信息（如框架显示）异步翻译，以提高响应效率
### 改进
- 使用AWS Gateway代替Spring Boot，打造真正的Serverless服务器结构
- 重要信息的翻译也应该异步处理（无法授予Java写入S3的权力，导致该功能未实现）

<details><summary><b>难点未实现</b></summary><p> <b>税费计算</b>：<br>在阅读相关资料后，发现各国各类商品税费都不同，国家进出口中心上税务查询除了要输入出口国和进口国以外，还需要输入商品完整编号</p><p><b>邮费计算</b>：<br>邮费计算要求邮编等信息。在最初想要爬取联邦快递等跨国快递的网站给出指导价格，但是网站的操作逻辑导致无法爬取</p></details> 

## 贡献名单
🤸‍♂️ 赵志鹏（[主页](https://github.com/Oran-Ac)/<a href="mailto:oran_official@outlook.com">邮箱</a>)）：云上服务撰写（翻译、汇率）、文档撰写、服务上云<br>
🦸‍♀️ 王彬（主页/<a href="mailto:1286746591@qq.com">邮箱</a>)）：Java后端、前后端接口对接、数据库设计<br>
🙋‍♂️ 张宇洲（主页/<a href="mailto:89657899@qq.com">邮箱</a>）：Vue前端开发、前后端接口对接



