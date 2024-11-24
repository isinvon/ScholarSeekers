<template>
  <div class="item-detail-page">
    <el-card>
      <!--卡片头部-->
      <template #header>
        <div class="card-header">
          <!--取消注释用于将内容限制在右侧-->
          <!--<div class="card-header-gradient"></div>-->
          <h3>{{ item.itemName }}</h3>
        </div>
      </template>
      <!--卡片中部-->
      <template #default>
        <div class="card-default">
          <el-row class="card-default-row" style="display: flex;">
            <!--第一列-->
            <el-col class="card-default-col" :span="12" style="padding-right: 10px">
              <!--第一列第一行-->
              <el-row>
                <el-col :span="24">
                  <!--图片-->
                  <el-image
                      class="image-slot"
                      v-if="existImage"
                      :src="item.imageUrl"
                      fit="fill"
                      :preview-src-list="[item.imageUrl]"
                      @error="handleImageError"
                  >
                  </el-image>
                  <!--加载时/加载失败时显示的图片-->
                  <ImageLoadingFailed class="image-slot" v-else/>
                </el-col>
              </el-row>
              <!--第一列第二行-->
              <el-row style="padding-top: 10px">
                <el-col :span="24">
                  <el-descriptions class="card-descriptions" :column="1" border>
                    <el-descriptions-item label="物品名称">{{ item.itemName }}</el-descriptions-item>
                    <el-descriptions-item label="丢失时间">{{ item.lostTime }}</el-descriptions-item>
                    <el-descriptions-item label="丢失地点">{{ item.lostPlace }}</el-descriptions-item>
                  </el-descriptions>
                </el-col>
              </el-row>
            </el-col>
            <!--第二列-->
            <el-col :span="12">
              <el-descriptions class="card-descriptions" :column="1" border>
                <el-descriptions-item label="物品描述">{{ item.itemDescription }}</el-descriptions-item>
                <el-descriptions-item label="认领人">{{ item.claimant }}</el-descriptions-item>
                <el-descriptions-item label="认领时间">{{ item.claimTime }}</el-descriptions-item>
                <el-descriptions-item label="认领地点">{{ item.claimPlace }}</el-descriptions-item>
                <!--认领状态样式: 未认领(黄色标签)、已认领(绿色标签)、其他字符(红色标签)-->
                <el-descriptions-item label="认领状态">
                  <el-tag
                      :type="item.claimStatus === '已认领' ? 'success' : item.claimStatus === '未认领' ? 'warning' : 'danger'"
                      effect="dark"
                      size="small"
                  >
                    {{ item.claimStatus }}
                  </el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="认领人联系方式">{{ item.claimantContact }}</el-descriptions-item>
                <el-descriptions-item label="认领人微信">{{ item.claimantWechat }}</el-descriptions-item>
                <el-descriptions-item label="认领人QQ">{{ item.claimantQQ }}</el-descriptions-item>
              </el-descriptions>
            </el-col>
          </el-row>
        </div>
      </template>
      <!--卡片底部-->
      <template #footer>
        <div class="card-footer">
          <MessageBlock :comment-data="commentData" />
        </div>
      </template>
    </el-card>
  </div>
</template>

<script setup>
;
import {onMounted, ref} from 'vue';
import {Picture as IconPicture} from '@element-plus/icons-vue'
import ImageLoadingFailed from "./components/ImageLoadingFailed";
import MessageBlock from "./components/MessageBlock";
import {commentData} from "../../../mock/comment";

defineOptions({
  name: "ItemDetailPage"
})
const item = ref({
  id: 1,
  itemName: '丢失物品',
  imageUrl: 'https://picsum.photos/100/100',
  itemDescription: '这是物品的描述',
  lostTime: '2023-01-01 12:00',
  lostPlace: '图书馆',
  claimant: '张三',
  claimTime: '2023-01-02 14:00',
  claimPlace: '宿舍楼A',
  claimStatus: '未认领', // 样式: 未认领(黄色标签)、已认领(绿色标签)、其他字符(红色标签)
  claimantContact: '12345678901',
  claimantWechat: 'wechat123',
  claimantQQ: '123456789'
});

const existImage = ref(false);

// 检查图片URL是否有效
const checkImageUrl = async (url) => {
  try {
    const response = await fetch(url);
    return response.ok;
  } catch (error) {
    console.error('Error checking image existence:', error);
    return false;
  }
};

// 检查id和imageUrl是否都存在且图片URL有效
const checkItemDetails = async () => {
  if (item.value.id && item.value.imageUrl) {
    existImage.value = await checkImageUrl(item.value.imageUrl);
  } else {
    existImage.value = false;
  }
};

const handleImageError = () => {
  console.warn('Image loading failed.');
};

onMounted(async () => {
  await checkItemDetails();
});
</script>

<style lang="less" scoped>
@import url('./index.less');

</style>
