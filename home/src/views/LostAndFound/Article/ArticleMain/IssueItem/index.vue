<!-- IssueItem.vue -->
<template>
  <div class="issue-item">
    <div class="issue-user">
      <p>{{ issue.user }}</p>
    </div>
    <div class="issue-comment">
      <p>{{ issue.comment }}</p>
      <p>{{ issue.date }}</p>
    </div>
    <div class="issue-detail">
      <p>{{ issue.detail }}</p>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  issue: {
    type: Object,
    required: true,
  },
});
</script>

<style lang="less" scoped>
.issue-item {
  border: 1px solid #dee2e6;
  padding: 20px;
  border-radius: 10px;
}

.issue-user {
  font-weight: bold;
}

.issue-user p,
.issue-comment p,
.issue-detail p {
  text-align: left;
}

.issue-comment {
  font-size: 14px;
  color: #6c757d;
}

.issue-detail {
  font-size: 16px;
  color: #343a40;
}
</style>
