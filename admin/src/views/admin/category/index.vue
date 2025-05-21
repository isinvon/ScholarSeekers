<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类别id" prop="id">
        <el-input
            v-model="queryParams.id"
            placeholder="请输入类别id"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类别名称" prop="name">
        <el-input
            v-model="queryParams.name"
            placeholder="请输入类别名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父级分类" prop="parentId">
        <el-cascader
            v-model="queryParams.parentId"
            :options="categoryTree"
            :props="{ checkStrictly: true, emitPath: false, value: 'id', label: 'name' }"
            placeholder="请选择父级分类"
            clearable
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
            v-hasPermi="['admin:category:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['admin:category:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['admin:category:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['admin:category:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="类别id" align="center" prop="id"/>
      <el-table-column label="类别名称" align="center" prop="name"/>
      <el-table-column label="父级分类" align="center" prop="parentName">
        <template #default="scope">
          <span v-if="scope.row.parentName">{{ scope.row.parentName }}</span>
          <span v-else>无</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['admin:category:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['admin:category:remove']">删除
          </el-button>
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

    <!-- 添加或修改物品类别对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="categoryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类别名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入类别名称"/>
        </el-form-item>
        <el-form-item label="父级分类" prop="parentId">
          <el-cascader
              v-model="form.parentId"
              :options="categoryTree"
              :props="{ checkStrictly: true, emitPath: false, value: 'id', label: 'name' }"
              placeholder="请选择父级分类（留空为顶级）"
              clearable
          />
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

<script setup name="Category">
import {
  addCategory,
  delCategory,
  getCategory,
  getCategoryTree,
  listCategory,
  updateCategory
} from "@/api/admin/category"; // 新增引入

const {proxy} = getCurrentInstance();

const categoryList = ref([]);
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
    id: null,
    name: null,
    parentId: null,
  },
  rules: {
    name: [
      {required: true, message: "类别名称不能为空", trigger: "blur"}
    ],
  }
});

const {queryParams, form, rules} = toRefs(data);

/** 查询物品类别列表 */
function getList() {
  loading.value = true;
  listCategory(queryParams.value).then(response => {
    categoryList.value = response.rows;
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
    name: null,
    parentId: null,
    createTime: null,
    updateTime: null,
    isDeleted: null
  };
  proxy.resetForm("categoryRef");
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
  title.value = "添加物品类别";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCategory(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改物品类别";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["categoryRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCategory(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCategory(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除物品类别编号为"' + _ids + '"的数据项？').then(function () {
    return delCategory(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/category/export', {
    ...queryParams.value
  }, `category_${new Date().getTime()}.xlsx`)
}


// 新增分类树数据
const categoryTree = ref([]);

/** 初始化分类树 */
const initCategoryTree = async () => {
  try {
    const res = await getCategoryTree();
    categoryTree.value = res.data;
  } catch (error) {
    console.error('获取分类树失败:', error);
  }
};

/** 处理列表数据 */
const processListData = (list) => {
  const map = new Map();
  categoryTree.value.forEach(item => {
    map.set(item.id, item.name);
    if (item.children) {
      item.children.forEach(child => map.set(child.id, child.name));
    }
  });

  return list.map(item => ({
    ...item,
    parentName: item.parentId ? map.get(item.parentId) || '已删除父级' : '顶级分类'
  }));
};


// 新增表单验证规则
const validateParent = (rule, value, callback) => {
  if (value === form.value.id) {
    callback(new Error('不能选择自己作为父级'));
  } else {
    callback();
  }
};

// 生命周期钩子
onMounted(() => {
  initCategoryTree();
});

getList();
</script>
