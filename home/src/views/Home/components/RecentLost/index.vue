<script setup>
import './index.less';
import {ref, watch, onMounted, onUnmounted} from 'vue';
import RouteUtils from "@/utils/RouteUtils";

defineOptions({
  name: 'RecentLost'
});

const {tableData} = defineProps({
  tableData: {
    type: Array,
    default: () => []
  }
});

const reportTable = ref(null);
const timer = ref(null);
const scrolling = ref(true);

onMounted(() => {
  startScrolling();
});

onUnmounted(() => {
  stopScrolling();
});

watch(tableData, () => {
  // 当数据变化时，重新开始滚动
  stopScrolling();
  startScrolling();
});

function startScrolling() {
  const demo = reportTable.value?.$refs.bodyWrapper?.querySelector('.el-scrollbar__wrap');
  if (!demo) return;

  demo.addEventListener('mouseover', () => {
    scrolling.value = false;
  });
  demo.addEventListener('mouseout', () => {
    scrolling.value = true;
  });

  timer.value = setInterval(() => {
    if (scrolling.value) {
      demo.scrollTop += 1;
      if (demo.clientHeight + demo.scrollTop >= demo.scrollHeight) {
        demo.scrollTop = 0; // Reset scroll position
      }
    }
  }, 10); // 每10毫秒滚动一次，可以根据需要调整
}

function stopScrolling() {
  clearInterval(timer.value);
  const demo = reportTable.value?.$refs.bodyWrapper?.querySelector('.el-scrollbar__wrap');
  demo?.removeEventListener('mouseover', () => {
  });
  demo?.removeEventListener('mouseout', () => {
  });
}
</script>

<template>
  <el-table
      :ref="reportTable"
      :data="tableData"
      style="width: 100%"
      size="small"
      :header-cell-style="{ background: 'rgb(0,53,82)', color: '#a5d2fd' }"
      :row-style="{ color: '#2e423c' }"
  >
    <el-table-column
        prop="itemName"
        label="物品"
        align="center"
    >
      <template #default="scope">
        <div class="item-name">
          <div class="item-name-text">
            <el-link type="default" :underline="false" @click="RouteUtils.goToItemDetailPage(scope.row.id)"
                     style="font-size: small">{{ scope.row.itemName }}
            </el-link>
          </div>
        </div>
      </template>
    </el-table-column>
    <el-table-column
        prop="itemDescription"
        label="描述"
        align="center"
    >
      <template #default="scope">
        <div class="item-description">
          <div class="item-description-text">
            <el-link type="default" :underline="false" @click="RouteUtils.goToItemDetailPage(scope.row.id)"
                     style="font-size: small">{{ scope.row.itemDescription }}
            </el-link>
          </div>
        </div>
      </template>
    </el-table-column>
    <el-table-column
        prop="clickNumber"
        label="点击量"
        align="center"
    />
    <el-table-column prop="messageNumber" align="center" label="留言数">
      <template #default="scope"> {{ scope.row.messageNumber }}</template>
    </el-table-column>
  </el-table>
</template>

<style scoped lang="less">

</style>
