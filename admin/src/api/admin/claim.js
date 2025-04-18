import request from '@/utils/request'

// 查询认领记录列表
export function listClaim(query) {
  return request({
    url: '/admin/claim/list',
    method: 'get',
    params: query
  })
}

// 查询认领记录详细
export function getClaim(id) {
  return request({
    url: '/admin/claim/' + id,
    method: 'get'
  })
}

// 新增认领记录
export function addClaim(data) {
  return request({
    url: '/admin/claim',
    method: 'post',
    data: data
  })
}

// 修改认领记录
export function updateClaim(data) {
  return request({
    url: '/admin/claim',
    method: 'put',
    data: data
  })
}

// 删除认领记录
export function delClaim(id) {
  return request({
    url: '/admin/claim/' + id,
    method: 'delete'
  })
}
