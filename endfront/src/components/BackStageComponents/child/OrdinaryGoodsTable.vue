<template>
  <el-table :data="dataArray" style="width: 100%" stripe>
    <!-- 扩展栏 -->
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left">
          <el-form-item :label="ColumnNames.about">
            <span>{{ props.row.about }}</span>
          </el-form-item>
          <el-form-item :label="ColumnNames.country">
            <span>{{ props.row.country }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column :label="ColumnNames.goodsID" prop="goodsID" />
    <el-table-column :label="ColumnNames.goodsName" prop="goodsName" />
    <el-table-column :label="ColumnNames.amount" prop="amount" />
    <el-table-column :label="ColumnNames.inStock" prop="inStock" />
    <el-table-column :label="ColumnNames.orderState" prop="orderState" />
    <!-- 选择列 -->
    <el-table-column v-if="showSelection" type="selection" fixed />
  </el-table>
</template>
<script>
export default {
  name: "OrdinaryGoodsTable",
  props: {
    dataArray: Array,
    ColumnNames: Object,
    showSelection: {
      type: Boolean,
      default: false,
    },
  },
};
</script>
