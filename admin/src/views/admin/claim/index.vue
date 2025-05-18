<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="被认领物品ID" prop="itemId">
        <el-input
          v-model="queryParams.itemId"
          placeholder="请输入被认领物品ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="认领人ID" prop="claimantId">
        <el-input
          v-model="queryParams.claimantId"
          placeholder="请输入认领人ID"
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
          v-hasPermi="['admin:claim:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:claim:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:claim:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:claim:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="claimList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="认领ID" align="center" prop="id" />
      <el-table-column label="被认领物品ID" align="center" prop="itemId" />
      <el-table-column label="认领人ID" align="center" prop="claimantId" />
      <el-table-column label="认领凭证" align="center" prop="proof" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:claim:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:claim:remove']">删除</el-button>
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

    <!-- 添加或修改认领记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="claimRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="被认领物品ID" prop="itemId">
          <el-input v-model="form.itemId" placeholder="请输入被认领物品ID" />
        </el-form-item>
        <el-form-item label="认领人ID" prop="claimantId">
          <el-input v-model="form.claimantId" placeholder="请输入认领人ID" />
        </el-form-item>
        <el-form-item label="认领凭证" prop="proof">
          <el-input v-model="form.proof" type="textarea" placeholder="请输入内容" />
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

<script setup name="Claim">
import { listClaim, getClaim, delClaim, addClaim, updateClaim } from "@/api/admin/claim";

const { proxy } = getCurrentInstance();

const claimList = ref([]);
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
    itemId: null,
    claimantId: null,
  },
  rules: {
    itemId: [
      { required: true, message: "被认领物品ID不能为空", trigger: "blur" }
    ],
    claimantId: [
      { required: true, message: "认领人ID不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询认领记录列表 */
function getList() {
  loading.value = true;
  listClaim(queryParams.value).then(response => {
    claimList.value = response.rows;
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
    itemId: null,
    claimantId: null,
    proof: null,
    createTime: null,
    updateTime: null,
    isDeleted: null
  };
  proxy.resetForm("claimRef");
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
  title.value = "添加认领记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getClaim(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改认领记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["claimRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateClaim(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addClaim(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除认领记录编号为"' + _ids + '"的数据项？').then(function() {
    return delClaim(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/claim/export', {
    ...queryParams.value
  }, `claim_${new Date().getTime()}.xlsx`)
}

getList();
</script>
