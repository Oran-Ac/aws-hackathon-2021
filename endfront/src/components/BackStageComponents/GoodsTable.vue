<template>
  <el-table
    :data="goodsArray"
    style="width: 100%"
    stripe
    v-loading="ShowStates.loadingGoods"
    @selection-change="handleSelectionDelete"
    @cell-click="handleClick"
  >
    <!-- 扩展栏 -->
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left">
          <el-form-item :label="GoodsColumnNames.about">
            <span>{{ props.row.about }}</span>
          </el-form-item>
          <el-form-item :label="GoodsColumnNames.country">
            <span>{{ props.row.country }}</span>
          </el-form-item>
          <el-form-item :label="GoodsColumnNames.sellCountries">
            <span>{{ props.row.sellCountries }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column :label="GoodsColumnNames.goodsID" prop="goodsID" />
    <el-table-column :label="GoodsColumnNames.goodsName" prop="goodsName" />
    <el-table-column :label="GoodsColumnNames.price" prop="price" />
    <el-table-column :label="GoodsColumnNames.inStock" prop="inStock" />
    <el-table-column :label="GoodsColumnNames.goodsState" prop="goodsState" />
    <!-- 选择删除 -->
    <el-table-column
      v-if="ShowStates.showSelectionDelete"
      type="selection"
      fixed
    />
  </el-table>
</template>
<script>
export default {
  name: "GoodsTable",
  props: {
    ButtonTexts: Object,
    ShowStates: Object,
    BlockStates: Object,
    GoodsColumnNames: Object,
    Messages: Object,
    goodsArray: Array,
  },
  data() {
    return {
      multipleSelections: [],
    };
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleSelectionDelete(selection, row) {
      // 选择数组序列化后,回传信号给根组件
      console.log("未传输", selection);
      this.$emit("changeSelection", JSON.stringify(selection));
    },
    handleClick(row, column, cell, event) {
      console.log(row, column, cell, event);
      this.$emit("hoverSelection", JSON.stringify(row));
    },
  },
};
</script>

