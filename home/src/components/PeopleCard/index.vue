<script setup>
defineOptions({ name: 'PeopleCard' })

const props = defineProps({
  // 图片地址
  img: {
    type: String,
    default: 'https://picsum.photos/200/200'  // 提供默认值为空字符串
  },
  // 图片的alt属性
  imgAlt: {
    type: String,
    default: ''  // 提供默认值为空字符串
  },
  // 大标题
  title: {
    type: String,
    default: 'Aurora Borealis'
  },
  // 副标题
  subtitle: {
    type: String,
    default: 'Nature'
  },
  // 介绍
  content: {
    type: String,
    default: 'Aurora Borealis is a colorful luminous phenomenon that appears over the high magnetic latitude region of the planet\'s North Pole.\n' +
        'I love the aurora borealis. It\'s so beautiful.'
  }
});

</script>

<template>
  <div class="kapian">
    <div class="tu">
      <!--图片-->
      <img :src="img" :alt="imgAlt">
    </div>
    <div class="wenben">
      <!--大标题-->
      <h2>{{ title }}</h2>
      <!--副标题-->
      <p style="padding-bottom: 20px;">{{ subtitle }}</p>
      <!--内容/详细描述-->
      <p>
        {{ content }}
      </p>
    </div>
  </div>
</template>

<style scoped lang="less">
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body{
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: radial-gradient(rgb(241, 238, 238),black);
}
.kapian{
  position: relative;
  width: 300px;
  height: 400px;
  border-radius: 3px;
  background-color: #fff;
  box-shadow: 2px 3px 3px rgb(139, 138, 138);
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s;
}
.kapian:hover{
  box-shadow: 2px 3px 10px rgb(36, 35, 35);
}
.tu{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 300px;
  overflow: hidden;

}
.tu img{
  width: 100%;
  height: 100%;
  transition: all 0.5s;
}
.kapian:hover .tu img{
  transform: scale(1.2);
  filter: blur(1px);
}
.wenben{
  position: absolute;
  bottom: -200px;
  width: 100%;
  height: 300px;

  background-color: rgb(247, 242, 242);
  transition: 0.5s;
}
.kapian:hover .wenben{
  bottom: 0px;
}
.wenben h2{
  color: rgb(21, 74, 172);
  line-height: 60px;
  text-align: center;

}
.wenben p{
  padding: 0 30px;
  font-family: 'fangsong';
  font-size: 16px;
  font-weight: bold;
  line-height: 20px;
  text-align: center;
}
</style>