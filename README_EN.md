#
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/head.png)
<h4 align="center">So,global：Backend management system based on Amazon cloud service</h4>


<p align="center">
    <a href="https://github.com/Oran-Ac/aws-hackathon-2021/commits/master">
    <img src="https://img.shields.io/github/last-commit/Oran-Ac/aws-hackathon-2021.svg?style=flat-square&logo=github&logoColor=white"
         alt="GitHub last commit">

</p>
<p align="center">
    <a href="\README.md">Chinese Readme.md</a> •
  <a href="#computer-Usage">Usage</a> •
  <a href="#rocket-界面介绍">Dashboard Introduction</a> •
  <a href="#page_with_curl-使用技术">AWS Services</a> •
  <a href="#%EF%B8%8F-架构设计">Architecture design</a> •
  <a href="#-部署指南">Deployment Guide</a> •
  <a href="#-功能特性">Features</a> •
  <a href="#贡献名单">Contribution</a> 
</p>

## :computer: Usage
Do you want to publish once and sell globally? Do you want to  accurately deliver product information to customers in different languages? Do you want to change currency units with a single click and realize real-time exchange rates? The "So,global" backend manager can fulfill all your needs. Click: ["So,global"](http://soglobal-env.eba-gyvuqgty.us-east-1.elasticbeanstalk.com/) for a quick experience.

## :rocket: Dashboard Introduction
"So,global" has a simple interface, provides high-quality services in a user-friendly way, and minimizes user learning costs.
### Registration/Login interface
- "So, global" registration and login use email verification.
- Users can select the language in the upper right corner to adjust the system language.
- When registering, users are required to select a country to facilitate subsequent language settings for the order management system.

![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/login.gif)

### Backend management interface
After logging in to "So, global", the backend management interface will appear.
- The left side is the product information, and the right side is the order information of the product. Below are the functional components.
- After the user clicks on the product on the left, the corresponding order will appear on the right, and corresponding operations can be performed. Click to view the <a href="#-Features">Operation Guide</a>.


## :page_with_curl: AWS Services
- AWS Elastic Beanstalk: Create a Java environment and deploy jar package back-end services with one click.
- AWS Lambda:
   - Perform real-time exchange rate calculations.
   - Works with Amazon Translate to provide high-quality translation results.
- AWS S3:
   - Access user product information as a picture bed.
   - Store the configuration files and historical versions required by Elastic Beanstalk.
   - Store page frame translations.
- AWS IAM: Resource access management.
- AWS EC2: stores web front-end services.
- AWS Amplify API: enables JavaScript to call AWS Lambda functions.
- AWS Cognito: Manage identity pools and grant front-end permissions.

## 🏗️ Architecture design
•<a href="#%EF%B8%8F-Cloud Service Architecture">Cloud Service Architecture</a> •
   <a href="#%EF%B8%8F-Backend Architecture">Backend Architecture</a> •
   <a href="#-Database Schema">Database Schema</a> •
### ☁️ Cloud service architecture
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/structure.png)
### 🛎️ Backend architecture
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/Back-end%20frame.png)
### 💿 Database architecture
![image](https://github.com/Oran-Ac/aws-hackathon-2021/blob/master/db.png)

## 📦 Deployment Guide


### Cloud deployment
- Grant Lambda permission to operate the corresponding AWS S3 Bucket library.
- Grant Lambda permission to call AWS Translate.
- Compile the back-end code into a jar package
- Open AWS Elastic beanstalk, create a Java 8 environment, and upload the corresponding jar package. If the environment is detected as green, it means it is in good condition and can be used. <br>
```
❗ When using AWS Elastic beanstalk to create an environment configuration, you need to add the environment variable PORT and set it to 5000. The Spring Boot default port is 8080, while AWS automatically detects the port as 5000.
```

## 🔨 Features
### Implement features


<table border="0" cellpadding="0" cellspacing="0" width="591" style="border-collapse:
 collapse;table-layout:fixed;width:443pt" >
 <colgroup><col width="181" style="mso-width-source:userset;mso-width-alt:6371;width:136pt">
 <col class="xl637116" width="410" style="mso-width-source:userset;mso-width-alt:
 14449;width:307pt">
 </colgroup><tbody><tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" width="181" style="height:20.0pt;width:136pt" align="center">Objects</td>
  <td class="xl647116" width="410" style="border-left:none;width:307pt" align="center">Operations</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td rowspan="2" height="54" class="xl647116" style="height:40.0pt;border-top:none" align="center">Users</td>
  <td class="xl647116" style="border-top:none;border-left:none" align="center">Login</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none" align="center">Register</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td rowspan="7" height="189" class="xl647116" style="height:140.0pt;border-top:
  none" align="center">Products</td>
  <td class="xl647116" style="border-top:none;border-left:none" align="center">Add products information</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Update products information</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Delete products information</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Search products</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Release a product in multiple countries with one click</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Remove a product in multiple regions with one click</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Remove multiple products from a certain region with one click</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td rowspan="4" height="108" class="xl647116" style="height:80.0pt;border-top:none">Orders</td>
  <td class="xl647116" style="border-top:none;border-left:none">Retrieve all orders for a certain product</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Retrieve all orders of a certain customer</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Search based on order amount</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt" align="center">
  <td height="27" class="xl647116" style="height:20.0pt;border-top:none;border-left:
  none">Obtain the three countries with the best-selling products within a certain period of time</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" width="181" style="height:20.0pt;width:136pt" align="center">Languages</td>
  <td class="xl647116" width="410" style="border-left:none;width:307pt" align="center">Translation</td>
 </tr>
 <tr height="27" style="mso-height-source:userset;height:20.0pt">
  <td height="27" class="xl647116" width="181" style="height:20.0pt;width:136pt" align="center">Price</td>
  <td class="xl647116" width="410" style="border-left:none;width:307pt" align="center">Real-time exchange rate conversion</td>
 </tr>

</tbody></table>

### Highlights
🌟 Exchange rate crawling: When performing real-time exchange rate conversion, we found that websites that can dynamically convert exchange rates have anti-crawler mechanisms. In the end, we searched Baidu for responses each time and crawled the results returned based on the knowledge graph<br>
🌟 Asynchronous translation of more stable information (such as frame display) to improve response efficiency
### Improve
- Use AWS Gateway instead of Spring Boot to create a true Serverless server structure
- Translation of important information should also be handled asynchronously (Java cannot be granted the authority to write to S3, resulting in this feature not being implemented)

<details><summary><b>The difficulty is not realized</b></summary><p> <b>Tax calculation</b>:<br>After reading relevant information, I found that taxes and fees on various commodities in various countries They are all different. In addition to the exporting country and importing country, the tax query on the National Import and Export Center also needs to enter the complete product number</p><p><b>Postage calculation</b>:<br>Postage calculation requires postal code and other information. Initially I wanted to crawl a website that wanted to crawl FedEx and other cross-border express delivery services, but the operating logic of the website made it impossible to crawl</p></details>

## Contribution list
🤸‍♂️ Zhipeng Zhao ([Home](https://github.com/Oran-Ac)/<a href="mailto:oran_official@outlook.com">Email</a>)): Cloud service writing (translation , exchange rate), document writing, service cloud<br>
🦸‍♀️ Bin Wang (Homepage/<a href="mailto:1286746591@qq.com">Email</a>)): Java backend, front-end and back-end interface docking, database design<br>
🙋‍♂️ Yuzhou Zhang (homepage/<a href="mailto:89657899@qq.com">email</a>): Vue front-end development, front-end and back-end interface docking