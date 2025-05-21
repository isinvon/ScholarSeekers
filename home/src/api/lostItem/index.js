import request from '@/utils/Request'

export function createLostItem(data) {
    return request.post('/home/lostItem/create', data)
}

/**
 * 当前用户的失物信息列表
 * @param data
 * @returns {Promise<Object>}
 */
export function lostItemListForCurrentUser(data) {
    return request.post('/home/lostItem/lostItemListForCurrentUser', data)
}

/**
 * 编辑失物信息
 * @param data
 * @returns {Promise<Object>}
 */
export function editLostItem(data) {
    return request.post('/home/lostItem/edit', data)
}

/**
 * 删除失物
 * @param data
 * @returns {Promise<Object>}
 */
export function deleteLostItem(data) {
    return request.post('/home/lostItem/delete', data)
}