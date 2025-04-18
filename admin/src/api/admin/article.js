import request from '@/utils/request'

// 查询寻物启事列表
export function listArticle(query) {
  return request({
    url: '/admin/article/list',
    method: 'get',
    params: query
  })
}

// 查询寻物启事详细
export function getArticle(id) {
  return request({
    url: '/admin/article/' + id,
    method: 'get'
  })
}

// 新增寻物启事
export function addArticle(data) {
  return request({
    url: '/admin/article',
    method: 'post',
    data: data
  })
}

// 修改寻物启事
export function updateArticle(data) {
  return request({
    url: '/admin/article',
    method: 'put',
    data: data
  })
}

// 删除寻物启事
export function delArticle(id) {
  return request({
    url: '/admin/article/' + id,
    method: 'delete'
  })
}
