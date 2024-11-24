// 随机工具类

import axios from "axios";

class RandomUtil {
    constructor() {
    }

    // 生成随机boolean
    getRandomBoolean() {
        return Math.random() > 0.5;
    }

    // 生成随机日期
    getRandomDate() {
        return new Date(Math.random() * (new Date().getTime() - 86400000) + 86400000);
    }


    /**
     * 生成随机浮点数
     * @param min
     * @param max
     * @returns {*}
     */
    getRandomFloat(min, max) {
        return Math.random() * (max - min) + min;
        // 示例
        // 生成一个 1.5 到 7.5 之间的随机浮点数
        // const randomFloat = getRandomFloat(1.5, 7.5);
        // console.log(randomFloat); // 输出类似 3.7456 这样的数
    }

    /**
     * 生成随机整数
     * @param min
     * @param max
     * @returns {*}
     */
    getRandomInt(min, max) {
        // Math.floor 向下取整，Math.random() 生成 0 到 1 之间的随机数
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    // 生产随机人物emoji
    getRandomEmoji() {
        const humanEmoji = [
            '😀', '😁', '😂', '🤣', '😃', '😄', '😅', '😆', '😉', '😊', '😋', '😎', '😍', '😘', '😗', '😙', '😚', '😋', '😛', '😜', '😝', '😒', '😓', '😔', '😕', '😖', '😗', '😘', '😙', '😚', '😛', '😜', '😝', '😞', '😟', '😠', '😡', '😢', '😣', '😤', '😥',]
        return humanEmoji[this.getRandomInt(0, humanEmoji.length - 1)];
    }

    // 生成随机动物emoji
    getRandomAnimalEmoji() {
        const animalEmoji = ['🐶', '🐱', '🐭', '🐹', '🐰', '🦊', '🐻', '🐼', '🐨', '🐯', '🦁', '🐮', '🐷', '🐽', '🐸', '🐵', '🙈', '🙉', '🙊', '🐒', '🐔', '🐧', '🐦', '🐤', '🐣', '🐥', '🦆', '🦅', '🦉', '🦇', '🐺', '🐗', '🐴', '🦄', '🐝', '🐛', '🦋', '🐌', '🐞', '🐜', '🦗',]
        return animalEmoji[this.getRandomInt(0, animalEmoji.length - 1)];
    }

    // 生成随机植物emoji
    getRandomPlantEmoji() {
        const plantEmoji = ['🌱', '🌲', '🌳', '🌴', '🌵', '🌾', '🌿', '🍀', '🍁', '🍂', '🍃', '🍄', '🌰', '🌱', '🌲', '🌳', '🌴', '🌵', '🌾', '🌿', '🍀', '🍁', '🍂', '🍃', '🍄', '🌰',]
        return plantEmoji[this.getRandomInt(0, plantEmoji.length - 1)];
    }

    // 生成随机符号emoji
    getRandomSymbolEmoji() {
        const symbolEmoji = ['❤️', '🧡', '💛', '💚', '💙', '💜', '🖤', '🤍', '🤎', '💯', '💢', '💥', '💫', '💦', '💨', '💬', '👁️‍🗨️', '🗨️', '🗯️', '💭', '💤', '👋', '🤚', '🖐️', '✋', '🖖', '👌', '👍', '👎', '✊', '👊', '🤛', '🤜', '👏', '🙌', '🙏', '🤝', '🙏', '💅']
        return symbolEmoji[this.getRandomInt(0, symbolEmoji.length - 1)];
    }

    // 生成随机颜色
    getRandomColor() {
        return '#' + Math.floor(Math.random() * 16777215).toString(16);
    }

    // 生成随机男人头像
    getRandomMenAvatar() {
        return 'https://randomuser.me/api/portraits/men/' + this.getRandomInt(0, 99) + ".jpg";
    }

    // 生成随机女人头像
    getRandomWomenAvatar() {
        return 'https://randomuser.me/api/portraits/women/' + this.getRandomInt(0, 99) + ".jpg";
    }

    // 生成随机头像（男女随机）
    getRandomAvatar() {
        return this.getRandomInt(0, 1) === 0 ? this.getRandomMenAvatar() : this.getRandomWomenAvatar();
    }

    // 生成随机图片
    getRandomImage() {
        return 'https://picsum.photos/id/' + this.getRandomInt(1, 1000) + '/300/200';
    }

    // 生成指定尺寸的图片
    getRandomImageBySize(width, height) {
        return 'https://picsum.photos/id/' + this.getRandomInt(0, 1084) + '/' + width + '/' + height;
    }

    // 生成随机灰度背景图片，图源：https://picsum.photos/200/300?grayscale
    getRandomGrayImageBySize(width, height) {
        return 'https://picsum.photos/id/' + width + '/' + height + '?grayscale';
    }

    // 生成高斯模糊的图片，图源：https://picsum.photos/200/300/?blur
    getRandomBlurImageBySize(width, height) {
        return 'https://picsum.photos/id/' + width + '/' + height + '/?blur';
    }

    // 高斯模糊的图片，width和height是图片的宽高，blur是模糊度，默认为3，范围1-10
    getRandomBlurImageBySizeAndBlur(width, height, blur) {
        blur = blur || 3;
        return 'https://picsum.photos/id/' + this.getRandomInt(0, 1084) + width + '/' + height + '/?blur=' + blur;
    }

    // 灰度+高斯模糊的图片，width和height是图片的宽高，blur是模糊度，默认为3，范围1-10, 参考：https://picsum.photos/id/870/200/300?grayscale&blur=2
    getRandomGrayBlurImageBySizeAndBlur(width, height, blur) {
        blur = blur || 3;
        return 'https://picsum.photos/id/' + this.getRandomInt(0, 1084) + width + '/' + height + '/?grayscale&blur=' + blur;
    }

    // 生成随机的二维码，data就是二维码跳转的链接，size就是图片的尺寸,图源：https://api.qrserver.com/v1/create-qr-code/?data=https://www.baidu.com&size=200x200
    getRandomQRCode(data, size) {
        return 'https://api.qrserver.com/v1/create-qr-code/?data=' + data + '&size=' + size + 'x' + size;
    }

    // webp图片，width和height是图片的宽高，图源：https://picsum.photos/200/300.webp
    getRandomWebPImageBySize(width, height) {
        return 'https://picsum.photos/id/' + width + '/' + height + '.webp';
    }

    // 图片列表，参考：https://picsum.photos/v2/list
    // 返回内容参考：{
    //         "id": "0",
    //         "author": "Alejandro Escamilla",
    //         "width": 5616,
    //         "height": 3744,
    //         "url": "https://unsplash.com/...",
    //         "download_url": "https://picsum.photos/..."
    //     }
    getRandomImageList() {
        const url = "https://picsum.photos/v2/list"
        // 发起请求
        return axios.get(url).then(res => {
            return res.data;
        }).catch(err => {
            console.log(err);
        });
    }

    // 分页获取图片列表，page是页码，limit是每页数量，参考：https://picsum.photos/v2/list?page=1&limit=10
    getRandomImageListByPage(page, limit) {
        const url = "https://picsum.photos/v2/list?page=" + page + "&limit=" + limit;
        return axios.get(url).then(res => {
            return res.data;
        }).catch(err => {
            console.log(err);
        });
    }

    // 返回图片的详情信息，id 限制 0-1084，参考：https://picsum.photos/id/0/info
    getRandomImageInfo(id) {
        const url = "https://picsum.photos/id/" + id + "/info";
        return axios.get(url).then(res => {
            return res.data;
        }).catch(err => {
            console.log(err);
        });
    }

    // 生成随机人物完整信息（非常详细非常多内容）,包括性别、住址、身高年龄、手机号、照片、照片大小等等，参考：https://randomuser.me/api
    getRandomPersonInfo() {
        const url = "https://randomuser.me/api";
        return axios.get(url).then(res => {
            return res.data;
        }).catch(err => {
            console.log(err);
        });
    }


    // 生成随机的经纬度坐标，latitude是纬度，longitude是经度，precision是精度，默认为6位小数
    getRandomCoordinate(latitude, longitude, precision) {
        precision = precision || 6;
        return [this.getRandomFloat(latitude - 0.5, latitude + 0.5).toFixed(precision), this.getRandomFloat(longitude - 0.5, longitude + 0.5).toFixed(precision)];
    }


}

export default new RandomUtil();