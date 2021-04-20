# aws-hackathon-2021

# :computer: 使用
想要一次发布全球可售？跨越语言与货币的阻碍，“售全球”后台管理器满足你的所有需求。点击：[“售全球”](https://github.com/Oran-Ac/aws-hackathon-2021/edit/master/README.md)快速体验。

===这里做一个超连接
====实现特性

# :rocket: 界面介绍
“售全球”彰显简洁风，以用户友好的方式，提供优质的服务，最大程度降低学习成本。
## 注册/登录界面
“售全球”注册与登录都采用邮箱验证方式，用户可以在右上角选择语言，以调整系统语言。在注册时，要求用户选择国家，以方便后续订单管理时发货国的设置。

====动图

## 后台管理界面
在“售全球”登录后，会出现后台管理界面。左侧为商品信息，右侧为商品的订单信息。下方为功能组件。用户点击左侧商品后，右侧会出现的相应订单，xxxxxxx略

=====动图


# :page_with_curl: 使用技术说明
- AWS Elastic Beanstalk：创建Java环境，一键部署后端服务
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


# 🏗️ 架构设计
## 云服务架构
## 后端架构
## 数据库架构

# 部署指南


# 待开发
=====改进


====难点未实现：1.税费  2.运费

# 贡献名单


===亮点
===1.汇率爬取
