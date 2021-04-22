<template>
  <div>
    <!-- 测试链接 -->
    <!-- <div class="testConnection">
      <input type="file" placeholder="选择文件" @change="test" />
      <img :src="imgUrl" />
    </div> -->
    <!-- 选择语言 -->
    <div class="chooseCountry">
      <el-select
        size="medium"
        :placeholder="Texts.ChooseCountryName"
        style="width: 110px"
        v-model="Language"
        @change="chooseLanguage"
        filterable
      >
        <el-option
          v-for="item in options"
          :key="item"
          :label="item"
          :value="item"
        >
        </el-option>
      </el-select>
    </div>
    <!-- 登录界面 -->
    <div class="container" v-show="this.IsLogged == 'false'">
      <h3>{{ Texts.LoginTitle }}</h3>
      <!-- 登陆输入框 -->
      <div class="inputContainer">
        <el-input
          type="text"
          :placeholder="Texts.UserName"
          v-model="LoginUserName"
        />
      </div>
      <div class="inputContainer">
        <el-input
          type="Password"
          :placeholder="Texts.Password"
          v-model="LoginPassword"
          show-password
        />
      </div>
      <!-- 登录按钮 -->
      <div class="buttonContainer">
        <el-button @click="LogIn">{{ Texts.LoginButtonName }}</el-button>
        <el-link herf="">{{ Texts.ForgetPassword }}</el-link>
      </div>
    </div>
    <!-- 注册界面 -->
    <div class="container" v-show="this.IsLogged == 'false'">
      <h3>{{ Texts.RegisterTitle }}</h3>
      <!-- 注册输入框 -->
      <div class="inputContainer">
        <el-input
          type="text"
          :placeholder="Texts.UserName"
          v-model="RegisterUserName"
        />
      </div>
      <div class="inputContainer">
        <el-input
          type="Password"
          :placeholder="Texts.Password"
          v-model="RegisterPassword"
          show-password
        />
      </div>
      <div class="inputContainer">
        <el-input
          type="Password"
          v-model="ReregisterPassword"
          :placeholder="Texts.RePassword"
          show-password
        />
      </div>
      <!-- 选择国家器 -->
      <div class="inputContainer">
        <vue-country-code
          @onSelect="chooseCountry"
          countryName="true"
          :preferredCountries="['cn', 'us', 'gb']"
          aria-autocomplete="true"
        ></vue-country-code>
      </div>
      <!-- 注册按钮 -->
      <div class="buttonContainer">
        <el-button @click="Register">
          {{ Texts.RegisterButtonName }}
        </el-button>
      </div>
    </div>
  </div>
</template>
<script>
import connection from "../connection/connection.js";
export default {
  name: "LoginPage",
  data() {
    return {
      // 文本
      Texts: {
        LoginTitle: "登录",
        RegisterTitle: "没有账号?注册",
        LoginButtonName: "登录",
        RegisterButtonName: "注册",
        UserName: "邮箱",
        Password: "密码",
        RePassword: "重新输入密码",
        ChooseCountryName: "选择语言",
        ForgetPassword: "忘记密码",
      },
      options: [
        "af",
        "sq",
        "am",
        "ar",
        "hy",
        "az",
        "bn",
        "bs",
        "bg",
        "ca",
        "zh",
        "zh-TW",
        "hr",
        "cs",
        "da",
        "fa-AF",
        "nl",
        "en",
        "et",
        "fa",
        "fi",
        "fr",
        "fr-CA",
        "ka",
        "de",
        "el",
        "gu",
        "ht",
        "ha",
        "he",
        "hi",
        "hu",
        "is",
        "id",
        "it",
        "ja",
        "kn",
        "kk",
        "ko",
        "lv",
        "lt",
        "mk",
        "ms",
        "ml",
        "mt",
        "mn",
        "no",
        "ps",
        "pl",
        "pt",
        "ro",
        "ru",
        "sr",
        "si",
        "sk",
        "sl",
        "so",
        "es",
        "es-MX",
        "sw",
        "sv",
        "tl",
        "ta",
        "te",
        "th",
        "tr",
        "uk",
        "ur",
        "uz",
        "vi",
        "cy",
      ],
      // 数据
      LoginUserName: null,
      RegisterUserName: null,
      LoginPassword: null,
      RegisterPassword: null,
      ReregisterPassword: null,
      Country: null,
      Language: null,
      imgUrl: null,
      // 状态:已登录,未登录
      IsLogged: "false",
      IsRegistered: "false",
      usr: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
      psw: /^[A-Za-z]\w{7,14}$/,
    };
  },
  // 一切开始之前先设置基础网址和登录状态.
  created() {
    sessionStorage.setItem("IsLogged", this.IsLogged);
  },
  methods: {
    // 商品表单信息文件接口
    async test(event) {
      const fd = new FormData();
      fd.append("file", event.target.files[0]);
      console.log(fd);
    },
    chooseCountry(country) {
      console.log(country.iso2);
      this.Country = country.iso2;
    },
    //选择语言
    async chooseLanguage() {
      console.log(this.Language);
      sessionStorage.setItem("Language", this.Language);
      // 与服务器通信加载翻译文件

      let jsonFile = await connection.getLanguage("client_");
      this.Texts = jsonFile.data;
    },
    // 因为要通信所以是异步的
    async LogIn() {
      console.log("登录用户名:" + this.LoginUserName);
      console.log("登录密码:" + this.LoginPassword);
      if (
        this.usr.test(this.LoginUserName) &&
        this.psw.test(this.LoginPassword)
      ) {
        console.log("合法用户名:" + this.LoginUserName);
        console.log("合法密码:" + this.LoginPassword);
        // 发给服务并等待应答.
        try {
          let message = await connection.postJson("login", {
            userID: this.LoginUserName,
            passWord: this.LoginPassword,
          });
          console.log(message.data);
          if (message.data.code == 0) {
            // 状态存储本地
            this.IsLogged = "true";
            // 用户名存储到本地,已备back-stage调用
            sessionStorage.setItem("IsLogged", this.IsLogged);
            sessionStorage.setItem("UserID", this.LoginUserName);
            // 此时可以跳转成功
            this.$router.push({ path: "/back-stage" });
          } else {
            this.LoginPassword = null;
            alert(message.data.info);
          }
        } catch (e) {
          console.log("登录错误:" + e);
          return;
        }
      } else {
        alert(this.Texts.RePassword);
        this.LoginUserName = null;
        this.LoginPassword = null;
      }
    },
    // 因为要通信所以是异步的
    async Register() {
      console.log("注册用户名:" + this.RegisterUserName);
      console.log("注册密码:" + this.RegisterPassword);
      // 正则表达式匹配检测用户名
      if (
        this.RegisterPassword == this.ReregisterPassword &&
        this.usr.test(this.RegisterUserName) &&
        this.psw.test(this.RegisterPassword)
      ) {
        console.log("合法用户名:" + this.RegisterUserName);
        console.log("合法密码:" + this.RegisterPassword);
        console.log("国家:" + this.Country);
        // 发给服务并等待应答.
        try {
          let message = await connection.postJson("register", {
            userID: this.RegisterUserName,
            passWord: this.RegisterPassword,
            country: this.Country,
          });
          console.log(message.data);
          if (message.data.code == 0) {
            // 填入登录用户名
            this.IsRegistered = "true";
            this.LoginUserName = this.RegisterUserName;
          }
          alert(message.data.info);
          if (this.IsRegistered == "true") {
            this.LoginUserName = this.RegisterUserName;
          }
        } catch (e) {
          console.log("登录错误:" + e);
          return;
        }
      } else {
        alert(this.Texts.RePassword);
      }
      this.RegisterPassword = null;
      this.RegisterUserName = null;
      this.ReregisterPassword = null;
    },
  },
};
</script>
<style scoped>
</style>