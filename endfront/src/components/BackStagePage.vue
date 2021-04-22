<template>
  <el-container>
    <el-header>{{ Titles.All }} </el-header>
    <!-- 左部商品栏 -->
    <el-container direction="horizontal">
      <el-main>
        <el-header> {{ Titles.goodsArea }} </el-header>
        <goods-table
          v-on:changeSelection="changeSelection($event)"
          v-on:hoverSelection="handleHoverSelection($event)"
          :BlockStates="BlockStates"
          :goodsArray="goodsArray"
          :Messages="Messages"
          :GoodsColumnNames="GoodsColumnNames"
          :ButtonTexts="ButtonTexts"
          :ShowStates="ShowStates"
          v-on:changeGoodsMoney="changeGoodsMoney"
        />
      </el-main>
      <!-- 右部表单栏 -->
      <el-main>
        <el-header>
          {{ Titles.orderArea }}
        </el-header>
        <order-table
          :orderArray="orderArray"
          :ShowStates="ShowStates"
          :ButtonTexts="ButtonTexts"
          :OrderColumnNames="OrderColumnNames"
          v-on:changeOrderMoney="changeOrderMoney"
        />
      </el-main>
    </el-container>
    <!-- 底部功能栏 -->
    <Functionality
      :BlockStates="BlockStates"
      :ButtonTexts="ButtonTexts"
      :ShowStates="ShowStates"
      :goodsSelectionArray="goodsSelectionArray"
      :OrderColumnNames="OrderColumnNames"
      :orderSelectionArray="orderSelectionArray"
      :GoodsColumnNames="GoodsColumnNames"
      :Texts="Texts"
      :selectGoods="selectGoods"
      v-on:search="handleSearch"
      v-on:addGoodsArray="handleAddArray"
      v-on:conditionDelete="handleConditionDelete"
      v-on:editNew="handleEdit"
      v-on:editUpload="handleEdit"
      v-on:addOrder="handleAddOrder"
      v-on:deleteGoods="handleDeleteGoods"
      v-on:upload="handleEdit"
    />
  </el-container>
</template>
<script>
import GoodsTable from "./BackStageComponents/GoodsTable.vue";
import OrderTable from "./BackStageComponents/OrderTable.vue";
import Functionality from "./BackStageComponents/Functionality.vue";
import connection from "../connection/connection.js";
export default {
  name: "BackStagePage",
  components: {
    GoodsTable,
    OrderTable,
    Functionality,
  },
  async beforeCreate() {
    // 翻译不变字符串
    let language = await connection.getLanguage("web/web_");
    language = language.data;
    this.Titles = language.Titles;
    this.GoodsColumnNames = language.GoodsColumnNames;
    this.OrderColumnNames = language.OrderColumnNames;
    this.Messages = language.Messages;
    this.Texts = language.Texts;
    this.ButtonTexts = language.ButtonTexts;
    sessionStorage.setItem("Language", "zh");
    await this.getGoodsInfo();
    await this.getOrderInfo();
    console.log("完成翻译" + JSON.stringify(this.goodsArray));
    this.ShowStates.loadingGoods = false;
    this.ShowStates.loadingOrder = false;
    this.$forceUpdate();
  },
  data() {
    return {
      Titles: {
        All: "商品信息管理系统",
        goodsArea: "商品信息",
        orderArea: "订单信息",
        functionArea: "功能区",
      },
      ButtonTexts: {
        setting: "功能",
        search: "搜索",
        add: "添加",
        edit: "修改",
        upload: "上架",
        delete: "删除",
        deleteSelection: "选择删除",
        deleteCondition: "条件删除",
        addNewItem: "添加商品",
        addCountryToUploadedItem: "添加商品国家",
        new: "未发布",
        uploaded: "已发布",
        editNew: "修改未发布",
        editUploaded: "修改已发布",
        addOrder: "添加订单",
        grounding: "上架",
        undercarrige: "下架",
        goods: "商品",
        order: "订单",
        shield: "屏蔽国家",
        finish: "完成",
        unfinish: "未完成",
      },
      GoodsColumnNames: {
        goodsID: "商品ID",
        goodsName: "商品名称",
        price: "商品价格",
        about: "商品描述",
        goodsState: "商品状态",
        country: "发货地",
        inStock: "库存",
        goodsImg: "商品图片",
        sellCountries: "可卖的国家",
      },
      OrderColumnNames: {
        orderID: "订单ID",
        customerID: "顾客ID",
        goodsID: "商品ID",
        count: "商品件数",
        date: "下单时间",
        amount: "金额",
        taxes: "税费",
        orderState: "订单状态",
        customerCountry: "顾客国家",
      },
      Messages: {
        delete: "是否确认删除",
        confirm: "确认",
        cancel: "取消",
        cancelFinish: "已取消删除",
        deleteFinish: "删除成功",
      },
      Texts: {
        chooseOrigin: "请选择发货地",
        photoRestriction: "只能上传jpg/png文件",
        addPhoto: "上传图片",
        submit: "提交",
        requiredText: "请填写必填字段",
        confirmDelete: "删除",
        search: "搜索",
        selectItem: "你选择的是",
        chooseColumName: "选择属性",
        chooseValue: "输入值",
        newGoods: "未上架商品",
        choose: "选择",
        new: "未上架",
        upload: "点击上传",
        top3Country: "最畅销",
        minPrice: "最低价",
        chooseNewItem: "选择未发布商品",
        chooseItem: "请选择商品",
        country: "国家",
        sellVal: "销售额",
      },
      goodsArray: [],
      orderArray: [],
      orderSelectionArray: [],
      // 商品栏多选数组。
      goodsSelectionArray: [],
      // 状态
      ShowStates: {
        loadingGoods: false,
        loadingOrder: false,
        showFunction: false,
        // 删除状态
        showDeleteMenu: false,
        showSelectionDelete: false,
        showConditionDelete: false,
        showShield: false,
        // 添加状态
        showAddMenu: false,
        showAddNewGoods: false,
        showAddCountry: false,
        showAddOrder: false,
        // 修改状态
        showEditMenu: false,
        showEditNew: false,
        showEditUpload: false,
        // 上架状态
        showUploadMenu: false,
        showUploadFunc: false,
        // 搜索状态
        showSearchMenu: false,
        showGoodsSearch: false,
        showOrderSearch: false,
        showTop3Country: false,
        showTop3CountryRes: false,
      },
      BlockStates: {
        blockSetting: false,
        blockSearch: false,
        blockAdd: false,
        blockEdit: false,
        blockUpload: false,
        blockDelete: false,
        blockDeleteSelection: false,
        blockDeleteCondition: false,
      },
      search: "",
      selectGoods: null,
    };
  },

  methods: {
    async translateGoods() {
      // 翻译商品信息
      console.log("翻译商品");
      for (let x in this.goodsArray) {
        connection.getLanguageDyncmic(this.goodsArray[x], "about");
        connection.getLanguageDyncmic(this.goodsArray[x], "goodsName");
      }
    },
    async translateOrder() {
      // 翻译订单信息
      console.log("翻译订单");
      for (let x in this.orderArray) {
        connection.getLanguageDyncmic(this.orderArray[x], "orderState");
        console.log(this.orderArray[x]);
      }
    },
    async getGoodsInfo() {
      try {
        console.log(sessionStorage.getItem("UserID"));
        let response = await connection.getJson("goods", {
          userID: sessionStorage.getItem("UserID"),
        });
        this.goodsArray = response.data;
        this.goodsArray.map((item) => {
          item.goodsState =
            item.uploaded == true
              ? this.ButtonTexts.uploaded
              : this.ButtonTexts.new;
          return item;
        });
        await this.translateGoods();
        return true;
      } catch (e) {
        console.log("数据传输错误" + e);
      }
    },
    async getOrderInfo() {
      try {
        let response = await connection.getJson("order", {
          userID: sessionStorage.getItem("UserID"),
        });
        this.orderArray = response.data;
        this.orderArray.map((item) => {
          item.orderState =
            item.done == true
              ? this.ButtonTexts.finish
              : this.ButtonTexts.unfinish;
        });
        return true;
      } catch (e) {
        console.log("数据传输错误" + e);
      }
    },
    // 监听商品表中的选择组件改变
    changeSelection(event) {
      console.log("传参成功");
      // 从本地会话中获取参数
      this.goodsSelectionArray = JSON.parse(event);
      console.log(this.goodsSelectionArray);
    },
    // 监听搜索功能
    handleSearch(event) {
      let search = JSON.parse(event);
      console.log(search);
      if (search.kind == "goods") {
        console.log("查询商品");
        switch (search.searchAttr) {
          case "top3Country":
            break;
          case "minPrice":
            this.goodsSelectionArray = this.goodsArray.filter(
              (item) => item.price >= search.searchVal
            );
            break;
          case "shield":
            this.goodsSelectionArray = this.goodsArray.filter(
              (item) => item["sellCountries"] == search.searchVal
            );
            break;
          default:
            this.goodsSelectionArray = this.goodsArray.filter(
              (item) => item[search.searchAttr] == search.searchVal
            );
            break;
        }
      } else if (search.kind == "order") {
        console.log("查询订单");
        this.orderSelectionArray = this.orderArray.filter(
          (item) => item[search.searchAttr] == search.searchVal
        );
      }
    },
    // 商品栏中某项被点击时的解决函数
    handleHoverSelection(event) {
      console.log("点击商品");
      this.selectGoods = JSON.parse(event);
    },
    // 添加商品
    handleAddArray(event) {
      console.log("添加商品");
      this.goodsArray.push(JSON.parse(event));
    },
    handleConditionDelete(event) {
      console.log("条件删除商品");
      let selectGoods = JSON.parse(event);
      console.log(selectGoods);
    },
    // 更新商品
    handleEdit(event) {
      let updatedGoods = JSON.parse(event);
      console.log("更新商品");
      console.log(updatedGoods);
      // 替换
      this.goodsArray = this.goodsArray.map((item) => {
        if (item.goodsID == updatedGoods.goodsID) return updatedGoods;
        return item;
      });
    },
    // 添加订单
    handleAddOrder(event) {
      let newOrder = JSON.parse(event);
      this.orderArray.push(newOrder);
    },
    // 删除商品
    handleDeleteGoods(event) {
      console.log("删除商品");
      let deleteGoods = JSON.parse(event);
      console.log(deleteGoods);
      for (let x in deleteGoods) {
        let good = deleteGoods[x];
        this.goodsArray = this.goodsArray.map((item) => {
          if (item.goodsID == good.goodsID) {
            if (good.uploaded) {
              good.uploaded = false;
              return good;
            } else return null;
          }
          return item;
        });
      }
    },
    // 改变订单金额
    changeOrderMoney(event) {
      console.log("响应成功");
      let pair = JSON.parse(event);
      console.log(pair);
      let x = JSON.parse(pair.key);
      let v = pair.val;
      this.orderArray = this.orderArray.map((item) => {
        if (item.orderID == x.orderID) {
          item.amount = (parseFloat(item.amount) * v).toString();
          console.log(item.amount);
        }
        return item;
      });
      console.log(this.orderArray);
    },
    // 恢复金额
    recoverOrderMoney(event) {
      let x = JSON.parse(event);
      this.orderArray = this.orderArray.map((item) => {
        if (item.goodsID == x.goodsID)
          item.amount = sessionStorage.getItem("price");
      });
    },
    // 改变商品
    changeGoodsMoney(event) {
      console.log("响应成功");
      let pair = JSON.parse(event);
      console.log(pair);
      let x = pair.key;
      let v = pair.val;
      let s = pair.str;
      this.goodsArray = this.goodsArray.map((item) => {
        if (item[s] == x[s]) {
          item.price = (
            parseFloat(item.price) * parseFloat(x.price)
          ).toString();
        }
        return item;
      });
    },
  },
};
</script>
<style scoped>
.el-footer {
  background-color: #b3c0d1;
  color: white;
  text-align: center;
  line-height: 60px;
}
</style>