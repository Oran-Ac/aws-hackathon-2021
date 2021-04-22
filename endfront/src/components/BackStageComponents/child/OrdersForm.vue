<template>
  <el-form label-width="80px">
    <!-- 顾客ID -->
    <el-form-item required :label="OrderColumnNames.customerID">
      <el-col :span="12">
        <el-input v-model="order.customerID"></el-input>
      </el-col>
    </el-form-item>
    <!-- 商品ID -->
    <el-form-item required :label="OrderColumnNames.goodsID">
      <el-col :span="12">
        <el-input v-model="order.goodsID"></el-input>
      </el-col>
    </el-form-item>
    <!-- 商品件数 -->
    <el-form-item required :label="OrderColumnNames.count">
      <el-col :span="12">
        <el-input v-model="order.count" type="number"></el-input>
      </el-col>
    </el-form-item>
    <!-- 金额 -->
    <el-form-item required :label="OrderColumnNames.amount">
      <el-col :span="12">
        <el-input v-model="order.amount" type="number"></el-input>
      </el-col>
      <el-col :span="4">
        <el-select
          v-model="curMoneyUnit"
          size="mini"
          width
          @change="changeMoneyUnit"
          style="width: 80px"
        >
          <el-option
            v-for="item in moneyUnits"
            :key="item.key"
            :label="item.val"
            :value="item.key"
          />
        </el-select>
      </el-col>
    </el-form-item>
    <!-- 税费 -->
    <el-form-item required :label="OrderColumnNames.taxes">
      <el-col :span="12">
        <el-input v-model="order.taxes" type="number"></el-input>
      </el-col>
      <el-col :span="4">
        <el-select
          v-model="curMoneyUnit"
          size="mini"
          width
          @change="changeMoneyUnit"
          style="width: 80px"
        >
          <el-option
            v-for="item in moneyUnits"
            :key="item.key"
            :label="item.val"
            :value="item.key"
          />
        </el-select>
      </el-col>
    </el-form-item>
    <!-- 客户国家 -->
    <el-form-item :label="OrderColumnNames.customerCountry" required>
      <el-col :span="12">
        <el-select v-model="order.customerCountry" filterable>
          <el-option
            v-for="item in options"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-col>
    </el-form-item>
    <el-form-item required>
      <el-col :span="12">
        <el-button @click="handleAddOrder">
          {{ Texts.submit }}
        </el-button>
      </el-col>
    </el-form-item>
  </el-form>
</template>
<script>
import connection from "../../../connection/connection.js";
// 配置格式+获取时间
Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    S: this.getMilliseconds(), //毫秒
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(
      RegExp.$1,
      (this.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  }
  for (var k in o) {
    if (new RegExp("(" + k + ")").test(fmt)) {
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)
      );
    }
  }
  return fmt;
};
export default {
  name: "OrderForm",
  props: {
    OrderColumnNames: Object,
    ButtonTexts: Object,
  },
  data() {
    return {
      Texts: {
        submit: "提交",
      },
      options: ["CN", "US"],
      moneyUnits: [
        {
          val: "USD",
          key: "USD",
        },
        {
          val: "CNY",
          key: "CNY",
        },
        {
          val: "THB",
          key: "THB",
        },
      ],
      curMoneyUnit: "CNY",
      order: {
        orderID: null,
        customerID: null,
        goodsID: null,
        count: null,
        date: null,
        amount: null,
        state: null,
        done: null,
        tax: null,
      },
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
    chooseCountry(country) {
      console.log(country.iso2);
      this.Country = country.iso2;
    },
    changeMoneyUnit() {},
    // 添加新订单
    async handleAddOrder() {
      if (
        this.order.customerID &&
        this.order.goodsID &&
        this.order.count &&
        this.order.amount &&
        this.order.taxes
      ) {
        const yourDate = new Date();
        this.order.orderState = this.ButtonTexts.unfinish;
        this.order.done = false;
        let response = await connection.postJson("order/add", {
          userID: sessionStorage.getItem("UserID"),
          customerID: this.order.customerID,
          customerCountry: this.order.customerCountry,
          goodsID: this.order.goodsID,
          count: this.order.count,
          date: new Date().format("yyyy-MM-dd hh:mm:ss"),
          price: this.order.amount,
          tax: this.order.taxes,
          done: false,
        });
        if (response.data.code == 0) {
          this.order.orderID = response.data.newOrderID;
          this.$emit("addOrder", JSON.stringify(this.order));
        }
        alert(response.data.info);
      } else alert(this.Texts.requiredText);
    },
  },
};
</script>