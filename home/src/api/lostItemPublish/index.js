import request from '@/utils/Request'

/**
 * 提交失物信息
 * @param data
 * @returns {Promise}
 */
export function createLostItem(data) {
    return request.post('/home/lostItem/create', data)
}

/**
 *  图片上传接口（若依默认上传接口）
 * @returns {Promise}
 * @param file
 */
export function uploadFile(file) {
    // 关键修改：添加'FILE'参数触发正确处理
    return request.post('/common/upload', file, 'FILE')
}