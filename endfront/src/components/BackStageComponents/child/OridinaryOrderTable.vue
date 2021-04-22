<template>
  <el-table :data="orderArray" style="width: 100%" stripe>
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
export default {
  name: "OrdinaryOrderTable",
  props: {
    OrderColumnNames: Object,
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
    changeMoneyUnit() {
      console.log("改变金额");
    },
  },
};
</script>