<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物品标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入物品标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入详细描述"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="丢失地点" prop="lostLocation">
        <el-input
          v-model="queryParams.lostLocation"
          placeholder="请输入丢失地点"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="丢失时间" prop="lostTime">
        <el-date-picker clearable
          v-model="queryParams.lostTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择丢失时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发布者的id" prop="publisherId">
        <el-input
          v-model="queryParams.publisherId"
          placeholder="请输入发布者的id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="contactInfo">
        <el-input
          v-model="queryParams.contactInfo"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['admin:item:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:item:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:item:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="丢失物品的id" align="center" prop="id" />
      <el-table-column label="物品标题" align="center" prop="title" />
      <el-table-column label="详细描述" align="center" prop="description" />
      <el-table-column label="所属分类ID" align="center" prop="categoryId" />
      <el-table-column label="丢失地点" align="center" prop="lostLocation" />
      <el-table-column label="丢失时间" align="center" prop="lostTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.lostTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="认领状态" align="center" prop="status" />
      <el-table-column label="发布者的id" align="center" prop="publisherId" />
      <el-table-column label="图片URL" align="center" prop="imageUrl" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.imageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="contactInfo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:item:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:item:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改丢失物品对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="itemRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物品标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入物品标题" />
        </el-form-item>
        <el-form-item label="详细描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入详细描述" />
        </el-form-item>
        <el-form-item label="丢失地点" prop="lostLocation">
          <el-input v-model="form.lostLocation" placeholder="请输入丢失地点" />
        </el-form-item>
        <el-form-item label="丢失时间" prop="lostTime">
          <el-date-picker clearable
            v-model="form.lostTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择丢失时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发布者的id" prop="publisherId">
          <el-input v-model="form.publisherId" placeholder="请输入发布者的id" />
        </el-form-item>
        <el-form-item label="图片URL" prop="imageUrl">
          <image-upload v-model="form.imageUrl"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactInfo">
          <el-input v-model="form.contactInfo" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="是否已删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入是否已删除" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Item">
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/admin/item";

const { proxy } = getCurrentInstance();

const itemList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: null,
    description: null,
    categoryId: null,
    lostLocation: null,
    lostTime: null,
    status: null,
    publisherId: null,
    imageUrl: null,
    contactInfo: null,
  },
  rules: {
    title: [
      { required: true, message: "物品标题不能为空", trigger: "blur" }
    ],
    description: [
      { required: true, message: "详细描述不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "认领状态不能为空", trigger: "change" }
    ],
    publisherId: [
      { required: true, message: "发布者的id不能为空", trigger: "blur" }
    ],
    imageUrl: [
      { required: true, message: "图片URL不能为空", trigger: "blur" }
    ],
    contactInfo: [
      { required: true, message: "联系方式不能为空", trigger: "blur" }
    ],
    isDeleted: [
      { required: true, message: "是否已删除不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询丢失物品列表 */
function getList() {
  loading.value = true;
  listItem(queryParams.value).then(response => {
    itemList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    title: null,
    description: null,
    categoryId: null,
    lostLocation: null,
    lostTime: null,
    status: null,
    publisherId: null,
    imageUrl: null,
    contactInfo: null,
    createTime: null,
    updateTime: null,
    isDeleted: null
  };
  proxy.resetForm("itemRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加丢失物品";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getItem(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改丢失物品";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["itemRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateItem(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItem(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除丢失物品编号为"' + _ids + '"的数据项？').then(function() {
    return delItem(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/item/export', {
    ...queryParams.value
  }, `item_${new Date().getTime()}.xlsx`)
}

getList();
</script>
