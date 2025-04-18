import request from '@/utils/request'

// 查询帮助与反馈列表
export function listFeedback(query) {
  return request({
    url: '/admin/feedback/list',
    method: 'get',
    params: query
  })
}

// 查询帮助与反馈详细
export function getFeedback(id) {
  return request({
    url: '/admin/feedback/' + id,
    method: 'get'
  })
}

// 新增帮助与反馈
export function addFeedback(data) {
  return request({
    url: '/admin/feedback',
    method: 'post',
    data: data
  })
}

// 修改帮助与反馈
export function updateFeedback(data) {
  return request({
    url: '/admin/feedback',
    method: 'put',
    data: data
  })
}

// 删除帮助与反馈
export function delFeedback(id) {
  return request({
    url: '/admin/feedback/' + id,
    method: 'delete'
  })
}
