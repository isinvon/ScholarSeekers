const TOKEN_KEY = "home-token";

export function getToken() {
    return localStorage.getItem(TOKEN_KEY) || ''
}

// 设置token
export function setToken(token) {
    localStorage.setItem(TOKEN_KEY, token)
}

// 删除token
export function removeToken() {
    localStorage.removeItem(TOKEN_KEY)
}