<template>
  <el-form label-width="80px">
    <!-- 商品 -->
    <el-form-item :label="GoodsColumnNames.goodsName" required>
      <el-col :span="12">
        <el-input v-model="goods.goodsName"></el-input>
      </el-col>
    </el-form-item>
    <!-- 发货地 -->
    <el-form-item :label="GoodsColumnNames.country" required>
      <el-col :span="12">
        <el-select v-model="goods.country" filterable>
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
    <!-- 商品描述 -->
    <el-form-item :label="GoodsColumnNames.about" required>
      <el-col :span="12">
        <el-input type="textarea" :rows="2" v-model="goods.about"></el-input>
      </el-col>
    </el-form-item>
    <!-- 商品图片 -->
    <el-form-item :label="Texts.addPhoto" required>
      <el-col :span="12">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-change="changeFile"
          multiple
          :limit="3"
          :file-list="goods.photoList"
          :auto-upload="false"
        >
          <el-button size="small" type="primary">{{ Texts.submit }}</el-button>
          <div slot="tip" class="el-upload__tip">
            {{ Texts.photoRestriction }}
          </div>
        </el-upload>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="12">
        <el-button @click="submit">
          {{ Texts.submit }}
        </el-button>
      </el-col>
    </el-form-item>
  </el-form>
</template>
<script>
import connection from "../../../connection/connection.js";
export default {
  name: "AddGoodsForm",
  props: {
    GoodsColumnNames: Object,
    Texts: Object,
    ButtonTexts: Object,
  },
  data() {
    return {
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
      goods: {
        goodsID: null,
        goodsName: null,
        price: null,
        about: null,
        uploaded: null,
        country: null,
        inStock: null,
        sellCountries: null,
        photoList: [],
      },
      imageBase64: null,
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
    async submit() {
      if (this.goods.goodsName && this.goods.about && this.goods.country) {
        console.log("你选择的商品");
        const fd = new FormData();
        this.goods.uploaded = false;
        this.goods.goodsState = this.ButtonTexts.new;
        fd.append("userID", sessionStorage.getItem("UserID"));
        fd.append("goodsName", this.goods.goodsName);
        fd.append("about", this.goods.about);
        fd.append("country", this.goods.country);
        fd.append("images", null);
        let res = await connection.postMultipart("goods/add", fd);
        // 添加文件成功
        if (res.data.code == 0) {
          // let res = await connection.postMultipart("goods/add", fd);
          // 给AWS服务器传，图片组
          for (let x in this.goods.photoList) {
            console.log(this.goods.photoList[x.toString()].raw);
            await connection.postImgAWS(
              sessionStorage.getItem("UserID/") +
                this.goods.goodsID +
                "/" +
                this.goods.photoList[x.toString()].name,
              this.goods.photoList[x.toString()].raw
            );
          }
          this.goods.goodsID = res.data.goodsID;
          this.$emit("submit", JSON.stringify(this.goods));
        } else {
          alert(res.data.info);
        }
      } else {
        alert(this.Texts.requiredText);
      }
    },
    changeFile(file, fileLoad) {
      this.goods.photoList = fileLoad;
    },
  },
};
</script>