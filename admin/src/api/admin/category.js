import request from '@/utils/request'

// 查询物品类别列表
export function listCategory(query) {
  return request({
    url: '/admin/category/list',
    method: 'get',
    params: query
  })
}

// 查询物品类别详细
export function getCategory(id) {
  return request({
    url: '/admin/category/' + id,
    method: 'get'
  })
}

// 新增物品类别
export function addCategory(data) {
  return request({
    url: '/admin/category',
    method: 'post',
    data: data
  })
}

// 修改物品类别
export function updateCategory(data) {
  return request({
    url: '/admin/category',
    method: 'put',
    data: data
  })
}

// 删除物品类别
export function delCategory(id) {
  return request({
    url: '/admin/category/' + id,
    method: 'delete'
  })
}
