<script setup>
import { rename } from '@/tools';

const props = defineProps({
  data: Object, // 包含图书信息的数据对象
  update: Function // 更新图书信息的函数
});
</script>

<template>
  <div class="book-card">
    <div class="cover-container">
      <img :src="data.coverImage" alt="Book Cover" class="cover-image"/>
    </div>
    <div class="info">
      <div class="details">
        <div class="title">
          <span>{{ data.title + "   " }}</span>
          <i class="fa-solid fa-pen-to-square interact-item" @click.stop="rename(data.id, data.title, update)"></i>
        </div>
        <div class="author">
          作者: {{ data.author }}
        </div>
        <div class="rating">
          评分: {{ data.rating }} / 5
        </div>
      </div>
      <div class="summary">
        简介: {{ data.summary }}
      </div>
    </div>
  </div>
</template>

<style scoped>
.dark .book-card { color: #d9d9d9 }

.interact-item {
  transition: 0.3s;
  &:hover {
    cursor: pointer;
    transform: scale(1.1);
    opacity: 0.8;
  }
}

.book-card {
  width: 320px;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 10px;
  box-sizing: border-box;
  color: #333333;
  transition: 0.3s;
  display: flex;
  align-items: flex-start;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);

  &:hover {
    cursor: pointer;
    transform: scale(1.03);
  }

  .cover-container {
    flex-shrink: 0;
    margin-right: 20px;
  }

  .cover-image {
    width: 100px;
    height: 150px;
    object-fit: cover;
    border-radius: 8px;
    border: 1px solid #e0e0e0;
  }

  .info {
    flex: 1;
    display: flex;
    flex-direction: column;
  }

  .details {
    margin-bottom: 10px;
  }

  .title {
    font-size: 20px;
    font-weight: 600;
    margin-bottom: 5px;
    color: #333333;
  }

  .author {
    font-size: 16px;
    color: #555555;
    margin-bottom: 5px;
  }

  .rating {
    font-size: 15px;
    color: #f39c12;
    font-weight: bold;
  }

  .summary {
    margin-top: 10px; /* 空间分隔 */
    font-size: 14px;
    color: #444444;
    text-align: left;
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; /* 限制为 2 行 */
    line-height: 1.5;
    text-overflow: ellipsis; /* 使用省略号 */
  }
}
</style>