<template>
  <el-table
    :data="orderArray"
    style="width: 100%"
    stripe
    v-loading="ShowStates.loadingOrder"
    @expand-change="recoverMoney"
  >
    <!-- 扩展表单 -->
    <el-table-column type="expand">
      <template slot-scope="prop">
        <el-form>
          <el-form-item :label="OrderColumnNames.amount">
            {{ prop.row.amount }}
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
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <!-- 普通列 -->
    <el-table-column :label="OrderColumnNames.orderID" prop="orderID" />
    <el-table-column :label="OrderColumnNames.customerID" prop="customerID" />
    <el-table-column :label="OrderColumnNames.goodsID" prop="goodsID" />
    <el-table-column :label="OrderColumnNames.count" prop="count" />
    <el-table-column :label="OrderColumnNames.orderState" prop="orderState" />
  </el-table>
</template>
<script>
import connection from "../../connection/connection.js";
export default {
  name: "OrderTable",
  props: {
    ButtonTexts: Object,
    ShowStates: Object,
    BlockStates: Object,
    OrderColumnNames: Object,
    Messages: Object,
    orderArray: Array,
  },
  data() {
    return {
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
    };
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDeleteOne(index, row) {
      console.log(index, row);
      //确认删除
      this.$confirm(this.Messages.delete, this.Messages.confirm, {
        confirmButtonText: this.Messages.confirm,
        cancelButtonText: this.Messages.cancel,
        type: "warning",
      })
        .then(() => {
          // 删除该行
          this.goodsArray.splice(index, 1);
          // 该行按钮不显示
          this.ShowStates.showOneItemDelete = false;
          this.$message({
            type: "info",
            message: this.Messages.deleteFinish,
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: this.Messages.cancelFinish,
          });
        });
    },
    // 改变当前金额单元
    async changeMoneyUnit(val) {
      console.log("改变当前金额单元");
      let res = connection.getCurrency(this.curMoneyUnit);
      let currency = JSON.parse((await res).Payload);
      console.log(currency);
      this.$emit(
        "changeOrderMoney",
        JSON.stringify({
          key: sessionStorage.getItem("order"),
          val: currency[val],
          str: "orderID",
        })
      );
    },
    recoverMoney(row) {
      console.log(row);
      sessionStorage.setItem("order", JSON.stringify(row));
      sessionStorage.setItem("price", row.amount);
      this.$emit("recoverOrderMonry");
    },
  },
};
</script>