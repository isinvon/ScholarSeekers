import request from '@/utils/request'

// 查询公告新闻列表
export function listNews(query) {
  return request({
    url: '/admin/news/list',
    method: 'get',
    params: query
  })
}

// 查询公告新闻详细
export function getNews(id) {
  return request({
    url: '/admin/news/' + id,
    method: 'get'
  })
}

// 新增公告新闻
export function addNews(data) {
  return request({
    url: '/admin/news',
    method: 'post',
    data: data
  })
}

// 修改公告新闻
export function updateNews(data) {
  return request({
    url: '/admin/news',
    method: 'put',
    data: data
  })
}

// 删除公告新闻
export function delNews(id) {
  return request({
    url: '/admin/news/' + id,
    method: 'delete'
  })
}
