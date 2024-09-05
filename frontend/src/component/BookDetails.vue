<script setup>
import { reactive, watch } from 'vue';
import { get, post } from '@/net';
import { ElMessage, ElMessageBox } from 'element-plus';
import { Delete } from '@element-plus/icons-vue';

const props = defineProps({
  id: Number,
  update: Function
});
const emits = defineEmits(['delete']);

const details = reactive({
  base: {},
  editBook: false
});
const bookEdit = reactive({
  title: '',
  author: '',
  publisher: '',
  publicationDate: ''
});
const enableBookEdit = () => {
  details.editBook = true;
  bookEdit.title = details.base.title;
  bookEdit.author = details.base.author;
  bookEdit.publisher = details.base.publisher;
  bookEdit.publicationDate = details.base.publicationDate;
};
const submitBookEdit = () => {
  post('/api/books/update', {
    id: props.id,
    title: bookEdit.title,
    author: bookEdit.author,
    publisher: bookEdit.publisher,
    publicationDate: bookEdit.publicationDate
  }, () => {
    details.editBook = false;
    updateDetails();
    ElMessage.success('图书信息已更新');
  });
};

function deleteBook() {
  ElMessageBox.confirm('删除此图书后所有相关信息都将丢失，您确定要这样做吗？', '删除图书', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(() => {
    get(`/api/books/delete?id=${props.id}`, () => {
      emits('delete');
      props.update();
      ElMessage.success('图书已成功删除');
    });
  }).catch(() => {});
}

function updateDetails() {
  props.update();
  init(props.id);
}

const init = id => {
  if (id !== -1) {
    details.base = {};
    get(`/api/books/details?id=${id}`, data => Object.assign(details.base, data));
  }
};
watch(() => props.id, init, { immediate: true });
</script>

<template>
  <el-scrollbar>
    <div class="book-details" v-loading="Object.keys(details.base).length === 0">
      <div v-if="Object.keys(details.base).length">
        <div class="header">
          <div class="title">
            <i class="fa-solid fa-book"></i>
            图书信息
          </div>
          <el-button :icon="Delete" type="danger" @click="deleteBook" plain text>
            删除此图书
          </el-button>
        </div>
        <el-divider style="margin: 10px 0" />
        <div class="content">
          <div class="cover-container">
            <img :src="details.base.coverImage" alt="Book Cover" class="cover-image"/>
          </div>
          <div class="details-list">
            <div>
              <span>图书ID</span>
              <span>{{ details.base.id }}</span>
            </div>
            <div>
              <span>书名</span>
              <span>{{ details.base.title }}</span>&nbsp;
              <i @click.stop="enableBookEdit" class="fa-solid fa-pen-to-square interact-item" />
            </div>
            <div>
              <span>作者</span>
              <span>{{ details.base.author }}</span>
            </div>
            <div v-if="!details.editBook">
              <span>出版社</span>
              <span>{{ details.base.publisher }}</span>&nbsp;
              <i @click.stop="enableBookEdit" class="fa-solid fa-pen-to-square interact-item" />
            </div>
            <div v-else>
              <span>出版社</span>
              <el-input v-model="bookEdit.publisher" style="margin-left: 10px" size="small" placeholder="请输入出版社名称..." />
              <el-input v-model="bookEdit.publicationDate" style="margin-left: 10px" size="small" placeholder="请输入出版日期..." />
              <i @click.stop="submitBookEdit" class="fa-solid fa-check interact-item" />
            </div>
            <div>
              <span>出版日期</span>
              <span>{{ details.base.publicationDate }}</span>
            </div>
            <div class="summary">
              <span>简介</span>
              <div>
                {{ details.base.summary }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </el-scrollbar>
</template>

<style scoped>
.interact-item {
  transition: 0.3s;
  &:hover {
    cursor: pointer;
    scale: 1.1;
    opacity: 0.8;
  }
}

.book-details {
  height: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;

    .title {
      color: dodgerblue;
      font-size: 18px;
      font-weight: bold;
    }
  }

  .content {
    display: flex;
    gap: 20px;

    .cover-container {
      flex-shrink: 0;
    }

    .cover-image {
      width: 120px;
      height: 180px;
      object-fit: cover;
      border-radius: 8px;
      border: 1px solid #e0e0e0;
    }

    .details-list {
      flex: 1;
      font-size: 14px;

      & div {
        margin-bottom: 10px;

        & span:first-child {
          color: gray;
          font-size: 13px;
          font-weight: normal;
          width: 120px;
          display: inline-block;
        }

        & span {
          font-weight: bold;
        }
      }
    }
  }
}
</style>
