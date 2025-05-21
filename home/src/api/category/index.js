import request from '@/utils/Request'

export function getCategoryList(data) {
    return request.post('/home/category/list', data)
}

export function getCategoryTree(data) {
    return request.post('/home/category/tree', data)
}
