import request from '@/utils/request'

// 查询丢失物品列表
export function listItem(query) {
  return request({
    url: '/admin/item/list',
    method: 'get',
    params: query
  })
}

// 查询丢失物品详细
export function getItem(id) {
  return request({
    url: '/admin/item/' + id,
    method: 'get'
  })
}

// 新增丢失物品
export function addItem(data) {
  return request({
    url: '/admin/item',
    method: 'post',
    data: data
  })
}

// 修改丢失物品
export function updateItem(data) {
  return request({
    url: '/admin/item',
    method: 'put',
    data: data
  })
}

// 删除丢失物品
export function delItem(id) {
  return request({
    url: '/admin/item/' + id,
    method: 'delete'
  })
}
