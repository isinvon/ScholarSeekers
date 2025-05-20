import request from '@/utils/Request'

export function createLostItem(data) {
    return request.post('/home/lostItem/create', data)
}