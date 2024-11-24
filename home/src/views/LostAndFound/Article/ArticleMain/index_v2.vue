<template>
  <div :class="['article-container', alignClass]">
    <!-- 文章目录 -->
    <el-anchor :affix="false" class="anchor">
      <el-anchor-link
          v-for="(item, index) in headings"
          :key="index"
          :href="`#${item.id}`"
          :title="item.title"
      />
    </el-anchor>

    <!-- 文章内容 -->
    <div class="content" v-html="articleContent" @scroll="handleScroll"></div>

    <!-- 评论区 -->
    <div class="comments">
      <el-input
          v-model="comment"
          placeholder="Write a comment..."
          class="comment-input"
      />
      <el-button type="primary" class="submit-button" @click="submitComment">
        Submit
      </el-button>
      <!-- 使用 EmojiPicker 组件 -->
      <EmojiPickComponent @emoji-selected="addEmoji"/>
    </div>

    <!-- 图片上传 -->
    <el-upload
        class="upload"
        action="https://jsonplaceholder.typicode.com/posts/"
        list-type="picture"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :limit="3"
        :auto-upload="true"
    >
      <el-button type="primary">Upload Image</el-button>
    </el-upload>
  </div>
</template>

<script setup>
import {ref, computed, onMounted} from 'vue';
// import EmojiPickComponent from "./components/EmojiPickComponent.vue"; // 后面再解开注释，因为emoji需要用到DB数据库，所以一直会报错

// 对齐方式，'left' 为默认值，可选left or 'center' or 'right'
const align = ref('left');

// 根据对齐方式生成对应的 class
const alignClass = computed(() => {
  return align.value === 'left' ? 'align-left' : 'align-center';
});

const headings = ref([
  {id: 'introduction', title: 'Introduction'},
  {id: 'section1', title: 'Section 1: The Basics of HTML and CSS'},
  {id: 'section2', title: 'Section 2: JavaScript and DOM Manipulation'},
  {id: 'section3', title: 'Section 3: Advanced CSS Techniques'},
  {id: 'section4', title: 'Section 4: Working with APIs'},
  {id: 'section5', title: 'Section 5: Building a Full-Stack Application'},
  {id: 'section6', title: 'Section 6: Conclusion'}
]);


const articleContent = ref(`
  <h1 id="introduction">Introduction</h1>
  <p>
    Welcome to this comprehensive guide on modern web development. In this article, we will explore various aspects of creating a dynamic and responsive web application using the latest technologies.
  </p>

  <h2 id="section1">Section 1: The Basics of HTML and CSS</h2>
  <p>
    HTML and CSS form the backbone of any web application. HTML provides the structure, while CSS is used to style and layout the content. In this section, we will revisit some fundamental concepts of HTML and CSS.
  </p>
  <ul>
    <li><strong>HTML Elements:</strong> The building blocks of web pages, such as headings, paragraphs, lists, and links.</li>
    <li><strong>CSS Styling:</strong> Applying styles using classes, IDs, and advanced selectors.</li>
    <li><strong>Responsive Design:</strong> Techniques like media queries and flexible grids to make your site mobile-friendly.</li>
  </ul>
  <img src="https://via.placeholder.com/400x200" alt="Example Image" />

  <h2 id="section2">Section 2: JavaScript and DOM Manipulation</h2>
  <p>
    JavaScript adds interactivity to web pages. Whether it's form validation, dynamic content updates, or API calls, JavaScript is essential for modern web development.
  </p>
  <pre><code>document.getElementById('example').innerHTML = 'Hello, World!';</code></pre>
  <p>
    In this section, we'll cover:
  </p>
  <ul>
    <li><strong>JavaScript Syntax:</strong> Understanding variables, functions, and control flow.</li>
    <li><strong>DOM Manipulation:</strong> How to access and modify the HTML content dynamically.</li>
    <li><strong>Event Handling:</strong> Responding to user actions like clicks and keypresses.</li>
  </ul>

  <h2 id="section3">Section 3: Advanced CSS Techniques</h2>
  <p>
    As you grow more comfortable with basic CSS, you can start exploring more advanced features. This section introduces Flexbox, Grid layout, and animations.
  </p>
  <ul>
    <li><strong>Flexbox:</strong> A one-dimensional layout method for arranging items in rows or columns.</li>
    <li><strong>CSS Grid:</strong> A two-dimensional layout system that allows for more complex and responsive layouts.</li>
    <li><strong>CSS Animations:</strong> Adding movement and transitions to your web pages.</li>
  </ul>

  <h2 id="section4">Section 4: Working with APIs</h2>
  <p>
    APIs (Application Programming Interfaces) are essential for accessing external data and services. This section will introduce you to the basics of working with APIs using JavaScript.
  </p>
  <pre><code>
  fetch('https://api.example.com/data')
    .then(response => response.json())
    .then(data => console.log(data));
  </code></pre>
  <p>
    Topics include:
  </p>
  <ul>
    <li><strong>Fetching Data:</strong> Using the Fetch API to retrieve data from a server.</li>
    <li><strong>Handling Responses:</strong> Parsing JSON data and handling errors.</li>
    <li><strong>Asynchronous Programming:</strong> Understanding promises and async/await.</li>
  </ul>

  <h2 id="section5">Section 5: Building a Full-Stack Application</h2>
  <p>
    Combining everything you've learned, you can build a full-stack web application. This section will provide a step-by-step guide on creating a simple application with a front-end and back-end.
  </p>
  <ol>
    <li><strong>Setting up the Backend:</strong> Creating a REST API using Node.js and Express.</li>
    <li><strong>Designing the Frontend:</strong> Building a responsive user interface with HTML, CSS, and JavaScript.</li>
    <li><strong>Connecting Frontend and Backend:</strong> Making API calls from the front-end to the back-end.</li>
  </ol>

  <h2 id="section6">Section 6: Conclusion</h2>
  <p>
    Web development is a continuously evolving field. By mastering the basics and staying updated with new technologies, you can create powerful and dynamic web applications. Keep experimenting, learning, and building!
  </p>
`);


const comment = ref('');

const handleScroll = () => {
  // Handle scroll events for updating anchor highlight if necessary
};

const addEmoji = (emoji) => {
  comment.value += emoji;
};

const submitComment = () => {
  console.log('Comment Submitted:', comment.value);
};

const handlePreview = (file) => {
  console.log('Preview:', file);
};

const handleRemove = (file, fileList) => {
  console.log('Removed:', file, fileList);
};

onMounted(() => {
  // Initialization code if needed
});
</script>

<style scoped>
.article-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.anchor {
  width: 200px; /* Adjust as needed */
}

.content {
  flex: 1;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow-y: auto;
}

.comments {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.comment-input {
  width: 100%;
}

.submit-button {
  align-self: flex-start;
}

.upload {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.align-left {
  text-align: left;
}

.align-center {
  text-align: center;
}
</style>