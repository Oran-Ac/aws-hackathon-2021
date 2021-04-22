<template>
  <el-container>
    <!-- 功能头 -->
    <el-header>
      <el-row>
        <!-- 设置按钮 -->
        <el-tooltip :content="ButtonTexts.setting" placement="top">
          <el-button
            :disabled="BlockStates.blockSetting"
            icon="el-icon-setting"
            circle
            :title="ButtonTexts.setting"
            @click="
              () => {
                ShowStates.showFunction = !ShowStates.showFunction;
              }
            "
          >
          </el-button>
        </el-tooltip>
        <!-- 搜索按钮 -->
        <el-tooltip
          :content="ButtonTexts.search"
          placement="top"
          v-show="ShowStates.showFunction"
        >
          <el-button
            :disabled="BlockStates.blockSearch"
            icon="el-icon-search"
            type="warning"
            circle
            @click="
              () => {
                hideAllMenus();
                hideAllFunction();
                ShowStates.showSearchMenu = true;
              }
            "
          ></el-button>
        </el-tooltip>
        <!-- 添加按钮 -->
        <el-tooltip
          :content="ButtonTexts.add"
          placement="top"
          v-show="ShowStates.showFunction"
        >
          <el-button
            :disabled="BlockStates.blockAdd"
            type="primary"
            icon="el-icon-circle-plus-outline"
            circle
            @click="
              () => {
                // 只显示添加菜单
                hideAllMenus();
                hideAllFunction();
                ShowStates.showAddMenu = !ShowStates.showAddMenu;
              }
            "
          ></el-button>
        </el-tooltip>
        <!-- 修改按钮 -->
        <el-tooltip
          :content="ButtonTexts.edit"
          placement="top"
          v-show="ShowStates.showFunction"
        >
          <el-button
            :disabled="BlockStates.blockEdit"
            type="success"
            icon="el-icon-edit"
            circle
            @click="
              () => {
                hideAllMenus();
                hideAllFunction();
                ShowStates.showEditMenu = true;
              }
            "
          ></el-button>
        </el-tooltip>
        <!-- 上架按钮 -->
        <el-tooltip
          :content="ButtonTexts.upload"
          placement="top"
          v-show="ShowStates.showFunction"
        >
          <el-button
            :disabled="BlockStates.upload"
            type="info"
            icon="el-icon-upload"
            circle
            :title="ButtonTexts.upload"
            @click="
              () => {
                hideAllMenus();
                hideAllFunction();
                ShowStates.showUploadMenu = true;
              }
            "
          >
          </el-button>
        </el-tooltip>
        <!-- 删除按钮 -->
        <el-tooltip
          :content="ButtonTexts.delete"
          placement="top"
          v-show="ShowStates.showFunction"
        >
          <el-button
            :disabled="BlockStates.blockDelete"
            type="danger"
            icon="el-icon-delete"
            circle
            @click="
              () => {
                hideAllMenus();
                hideAllFunction();
                ShowStates.showDeleteMenu = true;
              }
            "
          >
          </el-button>
        </el-tooltip>
      </el-row>
    </el-header>
    <el-container
      v-if="
        ShowStates.showFunction &&
        (ShowStates.showAddMenu ||
          ShowStates.showDeleteMenu ||
          ShowStates.showEditMenu ||
          ShowStates.showSearchMenu ||
          ShowStates.showUploadMenu)
      "
    >
      <!-- 左部菜单栏 -->
      <el-aside width="40%">
        <!-- 删除的菜单 -->
        <el-col v-if="ShowStates.showDeleteMenu">
          <el-menu
            @select="handleDelete"
            default-active="deleteSelection"
            class="el-menu-vertical-demo"
          >
            <el-menu-item index="deleteSelection">
              <i class="el-icon-delete"></i>
              <span>{{ ButtonTexts.deleteSelection }}</span>
            </el-menu-item>
            <el-menu-item index="deleteCondition">
              <i class="el-icon-delete"></i>
              <span>{{ ButtonTexts.deleteCondition }}</span>
            </el-menu-item>
          </el-menu>
        </el-col>
        <!-- 添加的菜单 -->
        <el-col v-if="ShowStates.showAddMenu">
          <el-menu
            @select="handleAdd"
            default-active="new"
            class="el-menu-vertical-demo"
            ><el-menu-item index="addNew">
              <i class="el-icon-circle-plus"></i>
              {{ ButtonTexts.addNewItem }}
            </el-menu-item>
            <el-menu-item index="addOrder">
              <i class="el-icon-circle-plus"></i>
              {{ ButtonTexts.addOrder }}
            </el-menu-item>
          </el-menu>
        </el-col>
        <!-- 修改的菜单 -->
        <el-col v-if="ShowStates.showEditMenu">
          <el-menu
            @select="handleEdit"
            default-active="editNew"
            class="el-menu-vertical-demo"
          >
            <el-menu-item index="editNew">
              <i class="el-icon-edit"></i>
              <span>{{ ButtonTexts.editNew }}</span>
            </el-menu-item>
            <el-menu-item index="editUploaded">
              <i class="el-icon-edit"></i>
              <span>{{ ButtonTexts.editUploaded }}</span>
            </el-menu-item>
          </el-menu>
        </el-col>
        <!-- 搜索商品/订单的菜单 -->
        <el-col v-if="ShowStates.showSearchMenu">
          <el-menu
            @select="handleSearch"
            default-active="goods"
            class="el-menu-vertical-demo"
          >
            <el-menu-item index="goods">
              <i class="el-icon-search"></i>
              <span>{{ ButtonTexts.goods }}</span>
            </el-menu-item>
            <el-menu-item index="order">
              <i class="el-icon-search"></i>
              <span>{{ ButtonTexts.order }}</span>
            </el-menu-item>
            <el-menu-item index="top3Country">
              <i class="el-icon-search"></i>
              <span>{{ Texts.top3Country }}</span>
            </el-menu-item>
          </el-menu>
        </el-col>
        <!-- 上传商品的具体实现 -->
        <el-col v-if="ShowStates.showUploadMenu">
          <el-menu
            @select="handleUpload"
            default-active="choose"
            class="el-menu-vertical-demo"
          >
            <el-menu-item index="choose">
              <i class="el-icon-upload"></i>
              <span>{{ Texts.choose }}</span>
            </el-menu-item>
            <el-menu-item index="submit">
              <i class="el-icon-upload"></i>
              <span>{{ Texts.submit }}</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-aside>
      <!-- 右部具体实现 -->
      <el-main>
        <!-- 选择删除商品具体实现 -->
        <el-container v-if="ShowStates.showSelectionDelete" dir="vertical">
          <el-header>
            {{ Texts.selectItem }}
          </el-header>
          <oridinary-goods-table
            :ColumnNames="GoodsColumnNames"
            :dataArray="goodsSelectionArray"
          />
          <el-footer>
            <el-button type="mini" @click="confirmSelectionDelete">
              {{ Texts.confirmDelete }}
            </el-button>
          </el-footer>
        </el-container>
        <!-- 条件删除的具体实现-->
        <el-container v-if="ShowStates.showConditionDelete" dir="vertical">
          <el-header>
            <el-row>
              <el-col :span="6">
                <el-select
                  v-model="searchAttr"
                  :placeholder="Texts.chooseColumName"
                  style="width: 110px"
                >
                  <el-option
                    key="sellCountries"
                    :label="GoodsColumnNames.sellCountries"
                    value="sellCountries"
                  />
                  <el-option
                    key="goodsID"
                    :label="GoodsColumnNames.goodsID"
                    value="goodsID"
                  />
                  <el-option
                    key="shield"
                    :label="ButtonTexts.shield"
                    value="shield"
                  />
                </el-select>
              </el-col>
              <el-col :span="12">
                <el-input
                  type="text"
                  v-model="searchVal"
                  :placeholder="Texts.chooseValue"
                ></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click="searchGoods" size="mini">
                  {{ Texts.search }}
                </el-button>
              </el-col>
            </el-row>
          </el-header>
          <el-main v-if="goodsSelectionArray">
            <el-table
              @selection-change="loadConditionSelection"
              :data="goodsSelectionArray"
              style="width: 100%"
              stripe
            >
              <!-- 扩展栏 -->
              <el-table-column type="expand">
                <template slot-scope="props">
                  <el-form label-position="left">
                    <el-form-item required :label="GoodsColumnNames.about">
                      <span>{{ props.row.about }}</span>
                    </el-form-item>
                    <el-form-item required :label="GoodsColumnNames.country">
                      <span>{{ props.row.country }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column
                :label="GoodsColumnNames.goodsID"
                prop="goodsID"
              />
              <el-table-column
                :label="GoodsColumnNames.goodsName"
                prop="goodsName"
              />
              <el-table-column :label="GoodsColumnNames.price" prop="price" />
              <el-table-column
                :label="GoodsColumnNames.inStock"
                prop="inStock"
              />
              <el-table-column
                :label="GoodsColumnNames.goodsState"
                prop="goodsState"
              />
              <el-table-column type="selection" fixed />
            </el-table>
          </el-main>
          <el-footer>
            <el-button type="mini" @click="confirmConditionDelete">
              {{ Texts.confirmDelete }}
            </el-button>
          </el-footer>
        </el-container>
        <!-- 商品搜索的具体实现 -->
        <el-container v-if="ShowStates.showGoodsSearch">
          <el-header>
            <el-row>
              <el-col :span="6">
                <el-select
                  v-model="searchAttr"
                  :placeholder="Texts.chooseColumName"
                  style="width: 110px"
                >
                  <el-option
                    v-for="(val, key) in GoodsColumnNames"
                    :key="key"
                    :label="val"
                    :value="key"
                  />
                  <el-option
                    key="minPrice"
                    :label="Texts.minPrice"
                    value="minPrice"
                  />
                </el-select>
              </el-col>
              <el-col :span="12">
                <el-input
                  type="text"
                  v-model="searchVal"
                  :placeholder="Texts.chooseValue"
                ></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click="searchGoods" size="mini">
                  {{ Texts.search }}
                </el-button>
              </el-col>
            </el-row>
          </el-header>
          <el-main v-if="searchAttr != null">
            <oridinary-goods-table
              :ColumnNames="GoodsColumnNames"
              :dataArray="goodsSelectionArray"
            />
          </el-main>
        </el-container>
        <!-- 订单搜索的具体实现 -->
        <el-container v-if="ShowStates.showOrderSearch" dir="vertical">
          <el-header>
            <el-row>
              <el-col :span="6">
                <el-select
                  v-model="searchAttr"
                  :placeholder="Texts.chooseColumName"
                  style="width: 110px"
                >
                  <el-option
                    :label="OrderColumnNames.goodsID"
                    value="goodsID"
                  />
                  <el-option
                    :label="OrderColumnNames.customerID"
                    value="customerID"
                  />
                  <el-option :label="OrderColumnNames.amount" value="amount" />
                </el-select>
              </el-col>
              <el-col :span="12">
                <el-input
                  type="text"
                  v-model="searchVal"
                  :placeholder="Texts.chooseValue"
                ></el-input>
              </el-col>
              <el-col :span="6">
                <el-button @click="searchOrder" size="mini">
                  {{ Texts.search }}
                </el-button>
              </el-col>
            </el-row>
          </el-header>
          <el-main v-if="searchAttr != null">
            <oridinary-order-table
              :orderArray="orderSelectionArray"
              :OrderColumnNames="OrderColumnNames"
            />
          </el-main>
        </el-container>
        <!-- 最畅销3国的具体实现 -->
        <el-container v-if="ShowStates.showTop3Country" dir="vertical">
          <el-header>
            <el-col :span="12">
              <el-date-picker
                v-model="searchVal"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd HH:mm:ss"
              />
            </el-col>
            <el-col :span="6">
              <el-button @click="handleSearchTop3Country">{{
                Texts.submit
              }}</el-button>
            </el-col>
          </el-header>
          <el-main v-if="this.ShowStates.showTop3CountryRes">
            <el-table :data="top3Country" style="width: 100%" stripe>
              <el-table-column :label="Texts.country" prop="key" />
              <el-table-column :label="Texts.sellVal" prop="value" />
            </el-table>
          </el-main>
        </el-container>
        <!-- 上架的具体实现 -->
        <el-container v-if="ShowStates.showUploadFunc">
          <el-main v-if="selectGoods && selectGoods.uploaded == false">
            <el-form label-width="80px">
              <el-form-item required :label="GoodsColumnNames.goodsID">
                <el-col :span="12">
                  {{ selectGoods.goodsID }}
                </el-col>
              </el-form-item>
              <!-- 商品 -->
              <el-form-item required :label="GoodsColumnNames.goodsName">
                <el-col :span="12">
                  {{ selectGoods.goodsName }}
                </el-col>
              </el-form-item>
              <!-- 价格 -->
              <el-form-item :label="GoodsColumnNames.price" required>
                <el-col :span="6">
                  <el-input
                    v-model="selectGoods.price"
                    type="number"
                  ></el-input>
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
              <!-- 可售卖的国家 -->
              <el-form-item required :label="GoodsColumnNames.sellCountries">
                <el-col :span="6">
                  <el-select
                    v-model="selectGoods.sellCountries"
                    filterable
                    multiple
                  >
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
              <!-- 发货地 -->
              <el-form-item required :label="GoodsColumnNames.country">
                <el-col :span="12">
                  {{ selectGoods.country }}
                </el-col>
              </el-form-item>
              <!-- 库存 -->
              <el-form-item :label="GoodsColumnNames.inStock" required>
                <el-col :span="6">
                  <el-input
                    v-model="selectGoods.inStock"
                    type="number"
                  ></el-input>
                </el-col>
              </el-form-item>
            </el-form>
          </el-main>
        </el-container>
        <!-- 添加新商品的具体实现 -->
        <el-container v-if="ShowStates.showAddNewGoods" dir="vertical">
          <el-main>
            <add-goods-form
              :GoodsColumnNames="GoodsColumnNames"
              :Texts="Texts"
              :ButtonTexts="ButtonTexts"
              v-on:submit="submitNewGoods"
            />
          </el-main>
        </el-container>
        <!-- 添加订单的具体实现 -->
        <el-container v-if="ShowStates.showAddOrder" dir="vertical">
          <el-main>
            <orders-form
              :OrderColumnNames="OrderColumnNames"
              :ButtonTexts="ButtonTexts"
              v-on:addOrder="handleAddOrder"
            />
          </el-main>
        </el-container>
        <!-- 修改已/未发布商品 -->
        <el-container
          v-if="ShowStates.showEditNew || ShowStates.showEditUpload"
          dir="vertical"
        >
          <!-- 必须有选择的商品而且选择商品的状态为未发布 -->
          <el-main
            v-if="
              selectGoods &&
              selectGoods.uploaded == false &&
              ShowStates.showEditNew
            "
          >
            <el-form label-width="80px">
              <el-form-item required :label="GoodsColumnNames.goodsID">
                <el-col :span="12">
                  {{ selectGoods.goodsID }}
                </el-col>
              </el-form-item>
              <!-- 商品 -->
              <el-form-item required :label="GoodsColumnNames.goodsName">
                <el-col :span="12">
                  <el-input v-model="selectGoods.goodsName"></el-input>
                </el-col>
              </el-form-item>
              <!-- 价格 -->
              <el-form-item required :label="GoodsColumnNames.price">
                <el-col :span="12">
                  <el-input
                    v-model="selectGoods.price"
                    type="number"
                  ></el-input>
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
              <!-- 发货国家 -->
              <el-form-item required :label="GoodsColumnNames.country">
                <el-col :span="12">
                  <el-select
                    v-model="selectGoods.country"
                    multiple
                    filterable
                    reserve-keyword
                  >
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
              <!-- 可售卖的国家 -->
              <el-form-item required :label="GoodsColumnNames.sellCountries">
                <el-col :span="12">
                  <el-select
                    v-model="selectGoods.sellCountries"
                    multiple
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
                </el-col>
              </el-form-item>
              <!-- 商品图片 -->
              <el-form-item required :label="Texts.addPhoto">
                <el-col :span="12">
                  <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    multiple
                    :limit="3"
                    :file-list="selectGoods.photoList"
                    :auto-upload="false"
                  >
                    <el-button size="small" type="primary">{{
                      Texts.submit
                    }}</el-button>
                    <div slot="tip" class="el-upload__tip">
                      {{ Texts.photoRestriction }}
                    </div>
                  </el-upload>
                </el-col>
              </el-form-item>
              <el-col :span="12">
                <el-form-item required>
                  <el-button @click="handleEditNew">
                    {{ Texts.submit }}
                  </el-button>
                </el-form-item>
              </el-col>
            </el-form>
          </el-main>
          <!-- 必须有选择的商品而且选择商品的状态为已发布 -->
          <el-main
            v-if="
              selectGoods.uploaded == true &&
              selectGoods &&
              ShowStates.showEditUpload
            "
          >
            <el-form label-width="80px">
              <!-- 商品 -->
              <el-form-item required :label="GoodsColumnNames.goodsName">
                <el-col :span="12">
                  {{ selectGoods.goodsName }}
                </el-col>
              </el-form-item>
              <!-- 价格 -->
              <el-form-item required :label="GoodsColumnNames.price">
                <el-col :span="12">
                  {{ selectGoods.price }}
                </el-col>
              </el-form-item>
              <!-- 发货国家 -->
              <el-form-item required :label="GoodsColumnNames.country">
                <el-col :span="12">
                  {{ selectGoods.country }}
                </el-col>
              </el-form-item>
              <!-- 可售卖的国家 -->
              <el-form-item required :label="GoodsColumnNames.sellCountries">
                <el-col :span="12">
                  <el-select
                    v-model="selectGoods.sellCountries"
                    multiple
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
                </el-col>
              </el-form-item>
              <el-col :span="12">
                <el-form-item>
                  <el-col>
                    <el-button @click="handleEditUpload">
                      {{ Texts.submit }}
                    </el-button>
                  </el-col>
                </el-form-item>
              </el-col>
            </el-form>
          </el-main>
        </el-container>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import OridinaryGoodsTable from "./child/OrdinaryGoodsTable.vue";
import OridinaryOrderTable from "./child/OridinaryOrderTable.vue";
import AddGoodsForm from "./child/AddGoodsForm.vue";
import OrdersForm from "./child/OrdersForm.vue";
import connection from "../../connection/connection.js";
const AWS = require("aws-sdk");
export default {
  components: {
    AddGoodsForm,
    OridinaryGoodsTable,
    OridinaryOrderTable,
    OrdersForm,
  },
  name: "Functionality",
  props: {
    ButtonTexts: Object,
    ShowStates: Object,
    BlockStates: Object,
    goodsSelectionArray: Array,
    orderSelectionArray: Array,
    OrderColumnNames: Object,
    GoodsColumnNames: Object,
    selectGoods: Object, // 选中的已发布的商品
    Texts: Object,
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
      options: ["CN", "US"],
      //搜索框选择的属性
      searchAttr: null,
      // 搜索栏选择的值
      searchVal: null,
      //选中的值
      goods: null,
      // 最畅销3国
      top3Country: [],
      // 选择删除的商品
      selectedDeleteGoods: [],
      country: null,
    };
  },
  methods: {
    // 禁止任何按钮的使用
    blockAllButton() {
      const AllBlockStates = {
        blockSetting: true,
        blockSearch: true,
        blockAdd: true,
        blockEdit: true,
        blockUpload: true,
        blockDelete: true,
        blockDeleteSelection: true,
        blockDeleteCondition: true,
      };
      this.BlockStates = AllBlockStates;
    },
    // 开放任何按钮的使用
    freeAllButton() {
      const UnBlockStates = {
        blockSetting: false,
        blockSearch: false,
        blockAdd: false,
        blockEdit: false,
        blockUpload: false,
        blockDelete: false,
        blockDeleteSelection: false,
        blockDeleteCondition: false,
      };
      this.BlockStates = UnBlockStates;
    },
    // 隐藏所有菜单栏
    hideAllMenus() {
      this.ShowStates.showAddMenu = false;
      this.ShowStates.showDeleteMenu = false;
      this.ShowStates.showEditMenu = false;
      this.ShowStates.showUploadMenu = false;
      this.ShowStates.showSearchMenu = false;
      this.ShowStates.showSheld = false;
    },
    // 隐藏所有功能栏
    hideAllFunction() {
      this.ShowStates.showSelectionDelete = false;
      this.ShowStates.showConditionDelete = false;
      this.ShowStates.showGoodsSearch = false;
      this.ShowStates.showAddNewGoods = false;
      this.ShowStates.showOrderSearch = false;
      this.ShowStates.showAddCountry = false;
      this.ShowStates.showAddOrder = false;
      this.ShowStates.showEditNew = false;
      this.ShowStates.showEditUpload = false;
      this.ShowStates.showUploadFunc = false;
      this.ShowStates.showTop3Country = false;
      this.ShowStates.showTop3CountryRes = false;
    },
    handleDelete(index) {
      this.hideAllFunction();
      switch (index) {
        case "deleteSelection":
          {
            this.ShowStates.showSelectionDelete = true;
          }
          break;
        case "deleteCondition":
          {
            console.log("条件删除");
            this.ShowStates.showConditionDelete = true;
          }
          break;
      }
    },
    handleAdd(index) {
      this.hideAllFunction();
      switch (index) {
        case "addNew":
          {
            this.ShowStates.showAddNewGoods = true;
          }
          break;
        case "addCountry":
          {
            this.ShowStates.showAddCountry = true;
          }
          break;
        case "addOrder":
          {
            this.ShowStates.showAddOrder = true;
          }
          break;
      }
    },
    handleEdit(index) {
      this.hideAllFunction();
      switch (index) {
        case "editNew":
          {
            this.ShowStates.showEditNew = true;
          }
          break;
        case "editUploaded":
          {
            this.ShowStates.showEditUpload = true;
          }
          break;
      }
    },
    handleSearch(index) {
      this.hideAllFunction();
      switch (index) {
        case "goods":
          {
            this.ShowStates.showGoodsSearch = true;
          }
          break;
        case "order":
          {
            this.ShowStates.showOrderSearch = true;
          }
          break;
        case "top3Country":
          {
            // 远程显示最畅销3个国家
            this.ShowStates.showTop3Country = true;
            console.log("显示最畅销的3个国家");
          }
          break;
      }
    },
    // 上架商品
    async handleUpload(index) {
      this.hideAllFunction();
      switch (index) {
        case "choose":
          {
            this.ShowStates.showUploadFunc = true;
          }
          break;
        case "submit":
          {
            if (this.selectGoods.uploaded == false) {
              console.log("提交成功");
              let response = await connection.postJson("goods/upload", {
                userID: sessionStorage.getItem("UserID"),
                goodsID: this.selectGoods.goodsID,
                price: this.selectGoods.price,
                load: this.selectGoods.inStock,
                countryList: this.selectGoods.sellCountries,
              });
              if (response.data.code == 0) {
                console.log(this.selectGoods);
                this.selectGoods.goodsState = this.ButtonTexts.uploaded;
                this.$emit("upload", JSON.stringify(this.selectGoods));
              }
              alert(response.data.info);
            } else alert(this.Texts.chooseNewItem);
          }
          break;
      }
    },
    // 确认选择删除
    async confirmSelectionDelete() {
      console.log(console.log(this.goodsSelectionArray));
      let response = await connection.deleteJson("goods/delete/multiple", {
        userID: sessionStorage.getItem("UserID"),
        goodsIDList: this.goodsSelectionArray.map((item) => item.goodsID),
      });
      if (response.data.code == 0) {
        this.$emit("deleteGoods", JSON.stringify(this.goodsSelectionArray));
      }
      alert(response.data.info);
    },
    // 确认条件删除
    async confirmConditionDelete() {
      console.log("确认条件删除");
      console.log(this.selectedDeleteGoods);
      let response = null;
      // 若选择屏蔽国家
      if (this.searchAttr == this.ButtonTexts.shield) {
        response = await connection.putJson("country/delete", {
          userID: sessionStorage.getItem("UserID"),
          goodsIDList: this.selectedDeleteGoods.map((item) => item.goodsID),
          country: this.searchVal,
        });
        // 本质上是更新选择upload事件激活
        if (response.data.code == 0) {
          this.$emit("upload", JSON.stringify(this.selectedDeleteGoods));
        }
      }
      // 普通条件删除
      else {
        response = await connection.deleteJson("goods/delete/multiple", {
          userID: sessionStorage.getItem("UserID"),
          goodsIDList: this.selectedDeleteGoods.map((item) => item.goodsID),
        });
        if (response.data.code == 0) {
          this.$emit("deleteGoods", JSON.stringify(this.selectedDeleteGoods));
        }
      }
      alert(response.data.info);
    },
    // 搜索商品
    searchGoods() {
      this.$emit(
        "search",
        JSON.stringify({
          kind: "goods",
          searchAttr: this.searchAttr,
          searchVal: this.searchVal,
        })
      );
    },
    // 搜索订单
    searchOrder() {
      this.$emit(
        "search",
        JSON.stringify({
          kind: "order",
          searchAttr: this.searchAttr,
          searchVal: this.searchVal,
        })
      );
    },
    // 添加新商品
    async submitNewGoods(event) {
      this.$emit("addGoodsArray", event);
    },
    // 改变当前金额单元
    changeMoneyUnit(val) {},
    // 选择最畅销三个国家
    async handleSearchTop3Country() {
      console.log(this.searchVal);
      if (this.selectGoods) {
        let message = await connection.getJson("top3country/search", {
          goodsID: this.selectGoods.goodsID,
          beginTime: this.searchVal[0],
          endTime: this.searchVal[1],
        });
        console.log(message);
        if (message) {
          this.ShowStates.showTop3CountryRes = true;
          this.top3Country = message.data;
        } else {
          console.log(message);
        }
      } else {
        alert(this.Texts.chooseItem);
      } // 清空搜索信息
      this.searchVal = null;
    },
    // 加载选择删除的界面
    loadConditionSelection(selection) {
      this.selectedDeleteGoods = selection;
      console.log(this.selectedDeleteGoods);
    },
    // 修改未发布商品
    async handleEditNew() {
      // 图片置空
      if (this.selectGoods) {
        let response = await connection.putJson("goods/update", {
          userID: sessionStorage.getItem("UserID"),
          goodsID: this.selectGoods.goodsID,
          goodsName: this.selectGoods.goodsName,
          about: this.selectGoods.about,
          country: this.selectGoods.country,
          images: null,
        });
        // 给s3传图片
        await connection.postImgAWS(
          sessionStorage.getItem("UserID"),
          this.selectGoods.images
        );
        if (response.data.code == 0) {
          this.$emit("editNew", JSON.stringify(this.selectGoods));
        }
        alert(response.data.info);
      } else {
        alert(this.Texts.chooseItem);
      }
    },
    // 修改已发布商品的国家
    async handleEditUpload() {
      console.log("修改已发布商品的国家");
      let response = await connection.putJson("goods/country/delete", {
        userID: sessionStorage.getItem("UserID"),
        goodsID: this.selectGoods.goodsID,
        countryList: this.selectGoods.sellCountries,
      });
      if (response.data.code == 0) {
        this.$emit("editUpload", JSON.stringify(this.selectGoods));
      }
    },
    // 添加订单
    async handleAddOrder(event) {
      this.$emit("addOrder", event);
    },
  },
};
</script>
<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: white;
  text-align: center;
  line-height: 60px;
}
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}
</style>