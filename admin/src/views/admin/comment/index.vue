<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="评论者ID" prop="userId">
        <el-input
            v-model="queryParams.userId"
            placeholder="请输入评论者ID"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联的失物ID" prop="itemId">
        <el-input
            v-model="queryParams.itemId"
            placeholder="请输入关联的失物ID"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联的文章ID" prop="articleId">
        <el-input
            v-model="queryParams.articleId"
            placeholder="请输入关联的文章ID"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父评论ID" prop="parentCommentId">
        <el-input
            v-model="queryParams.parentCommentId"
            placeholder="请输入父评论ID"
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
            v-hasPermi="['admin:comment:add']"
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
            v-hasPermi="['admin:comment:edit']"
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
            v-hasPermi="['admin:comment:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['admin:comment:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="评论ID" align="center" prop="id"/>
      <el-table-column label="评论者ID" align="center" prop="userId"/>
      <el-table-column label="评论内容" align="center" prop="content"/>
      <el-table-column label="关联的失物ID" align="center" prop="itemId"/>
      <el-table-column label="关联的文章ID" align="center" prop="articleId"/>
      <el-table-column label="父评论ID" align="center" prop="parentCommentId"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['admin:comment:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['admin:comment:remove']">删除
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

    <!-- 添加或修改评论对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="commentRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="评论者ID" prop="userId">
          <el-input-number v-model="form.userId" placeholder="请输入评论者ID"/>
        </el-form-item>
        <el-form-item label="评论内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="关联的失物ID" prop="itemId">
          <el-input-number v-model="form.itemId" placeholder="请输入关联的失物ID"/>
        </el-form-item>
        <el-form-item label="关联的文章ID" prop="articleId">
          <el-input-number v-model="form.articleId" placeholder="请输入关联的文章ID"/>
        </el-form-item>
        <el-form-item label="父评论ID" prop="parentCommentId">
          <el-input-number v-model="form.parentCommentId" placeholder="请输入父评论ID"/>
        </el-form-item>
        <el-form-item label="是否已删除" prop="isDeleted">
          <el-switch v-model="form.isDeleted" active-text="是" inactive-text="否"></el-switch>
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

<script setup name="Comment">
import {listComment, getComment, delComment, addComment, updateComment} from "@/api/admin/comment";

const {proxy} = getCurrentInstance();

const commentList = ref([]);
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
    userId: null,
    content: null,
    itemId: null,
    articleId: null,
    parentCommentId: null,
  },
  rules: {}
});

const {queryParams, form, rules} = toRefs(data);

/** 查询评论列表 */
function getList() {
  loading.value = true;
  listComment(queryParams.value).then(response => {
    commentList.value = response.rows;
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
    userId: null,
    content: null,
    itemId: null,
    articleId: null,
    parentCommentId: null,
    createTime: null,
    updateTime: null,
    isDeleted: null
  };
  proxy.resetForm("commentRef");
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
  title.value = "添加评论";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getComment(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改评论";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["commentRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateComment(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addComment(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除评论编号为"' + _ids + '"的数据项？').then(function () {
    return delComment(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/comment/export', {
    ...queryParams.value
  }, `comment_${new Date().getTime()}.xlsx`)
}

getList();
</script>
