import request from '@/utils/Request'

export const getCategoryList = (data = {}) => {
    return request.post('/home/category/list', data)
}