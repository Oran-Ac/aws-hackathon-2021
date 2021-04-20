
#
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/head.png)
<p align="center">
  <a href="#使用">使用</a> •
  <a href="#界面介绍">界面介绍</a> •
  <a href="#使用技术">使用技术</a> •
  <a href="#架构设计">架构设计</a> •
  <a href="#部署指南">部署指南</a> •
  <a href="#功能迭代">功能迭代</a> •
  <a href="#贡献名单">贡献名单</a> 
</p>



## :computer: 使用
想要一次发布全球可售？跨越语言与货币的阻碍，“售全球”后台管理器满足你的所有需求。点击：[“售全球”](https://github.com/Oran-Ac/aws-hackathon-2021/edit/master/README.md)快速体验。









## :rocket: 界面介绍
“售全球”彰显简洁风，以用户友好的方式，提供优质的服务，最大程度降低学习成本。
## 注册/登录界面
“售全球”注册与登录都采用邮箱验证方式，用户可以在右上角选择语言，以调整系统语言。在注册时，要求用户选择国家，以方便后续订单管理时发货国的设置。

====动图

### 后台管理界面
在“售全球”登录后，会出现后台管理界面。左侧为商品信息，右侧为商品的订单信息。下方为功能组件。用户点击左侧商品后，右侧会出现的相应订单，xxxxxxx略

=====动图


## :page_with_curl: 使用技术
- AWS Elastic Beanstalk：创建Java环境，一键部署jar包后端服务
- AWS Lambda：
  - 进行实时汇率计算
  - 与Amazone Translate配合提供高质量翻译结果  
- AWS S3：
  - 作为图床存取用户商品信息
  - 存放Elastic Beanstalk需要使用的配置文件和历史版本
  - 存放页面框架翻译
- AWS IAM：资源访问权限管理
- AWS Cognito：身份池管理
- AWS EC2：存放Web前端服务       


## 🏗️ 架构设计
### ☁️ 云服务架构
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/structure.png)
### 🛎️ 后端架构
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/Back-end%20frame.png)
### 💿 数据库架构
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/db.png)

## 📦 部署指南


## 🔨 功能迭代
=====改进(做成折叠的)
===亮点
===1.汇率爬取
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

====难点未实现：1.税费  2.运费 
## 贡献名单



