// src/store/comment.js

import { defineStore } from 'pinia';
import {commentData} from "../../mock/comment";

export const useCommentStore = defineStore({
  id: 'comments',
  state: () => ({
    // comments: [], // 存储评论的数组
    comments: [...commentData], // 存储评论的数组
    currentPage: 1, // 当前页码
    pageSize: 10, // 每页显示的评论数量
  }),
  getters: {
    // 获取分页后的评论
    paginatedComments: (state) => {
      const start = (state.currentPage - 1) * state.pageSize;
      const end = start + state.pageSize;
      return state.comments.slice(start, end);
    },
    // 获取总评论数
    totalComments: (state) => state.comments.length,
  },
  actions: {
    // 添加评论
    addComment(comment) {
      this.comments.push(comment);
      this.currentPage = 1; // 添加新评论后，重置到第一页
    },
    // 删除评论
    removeComment(id) {
      this.comments = this.comments.filter((c) => c.id !== id);
    },
    // 更新评论
    updateComment(id, updatedComment) {
      const index = this.comments.findIndex((c) => c.id === id);
      if (index !== -1) {
        this.comments[index] = { ...this.comments[index], ...updatedComment };
      }
    },
    // 设置当前页码
    setCurrentPage(page) {
      this.currentPage = page;
    },
    // 清空评论
    clearComments() {
      this.comments = [];
    },
  },
});
