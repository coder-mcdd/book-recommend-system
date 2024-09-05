<script setup>
import PreviewCard from "@/component/PreviewCard.vue";
import { computed, reactive, ref } from "vue";
import { get } from "@/net";
import BookDetails from "@/component/BookDetails.vue"; // 修改为书籍详情组件
import RegisterCard from "@/component/RegisterCard.vue"; // 如果有添加图书的功能，可以保留此组件
import { Plus } from "@element-plus/icons-vue";
import { useRoute } from "vue-router";
import { useStore } from "@/store";
import { ElMessage } from 'element-plus'

const genres = [
  { name: '小说', desc: '小说' },
  { name: '非小说', desc: '非小说' },
  { name: '科学', desc: '科学' },
  { name: '历史', desc: '历史' },
  { name: '奇幻', desc: '奇幻' },
  { name: '传记', desc: '传记' }
];
const checkedGenres = ref([]);

const list = ref([]);
const store = useStore();

const route = useRoute();

const updateList = () => {
  if (route.name === 'manage') {
    get('/api/books/list', data => list.value = data); // 修改为获取图书列表的API
  }
};
setInterval(updateList, 1000000);
updateList(
  ElMessage.success("自动采集推送用户当前数据")
);

const detail = reactive({
  show: false,
  id: -1
});
const displayBookDetails = (id) => {
  detail.show = true;
  detail.id = id;
};

const bookList = computed(() => {
  if (checkedGenres.value.length === 0) {
    return list.value;
  } else {
    return list.value.filter(item => checkedGenres.value.indexOf(item.genre) >= 0);
  }
});

const register = reactive({
  show: false,
  token: ''
});
const refreshToken = () => get('/api/books/register', token => register.token = token); // 修改为图书注册API
</script>

<template>
  <div class="manage-main">
    <div style="display: flex; justify-content: space-between; align-items: end">
      <div>
        <div class="title"><i class="fa-solid fa-book"></i> 管理图书列表</div>
        <div class="desc">在这里管理所有已经添加的图书，查看图书详情并进行编辑操作。</div>
      </div>
      <div>
        <el-button :icon="Plus" type="primary" plain :disabled="!store.isAdmin"
                   @click="register.show = true">添加新图书
        </el-button>
      </div>
    </div>
    <el-divider style="margin: 10px 0" />
    <div style="margin-bottom: 20px">
      <el-checkbox-group v-model="checkedGenres">
        <el-checkbox v-for="genre in genres" :key="genre.name" :label="genre.name" border>
          <span style="font-size: 13px; margin-left: 10px">{{ genre.desc }}</span>
        </el-checkbox>
      </el-checkbox-group>
    </div>
    <div class="card-list" v-if="list.length">
      <preview-card v-for="item in bookList" :data="item" :update="updateList"
                    @click="displayBookDetails(item.id)" />
    </div>
    <el-empty description="还没有任何图书哦，点击右上角添加一个吧" v-else />
    <el-drawer size="520" :show-close="false" v-model="detail.show"
               :with-header="false" v-if="list.length" @close="detail.id = -1">
      <book-details :id="detail.id" :update="updateList" @delete="updateList" />
    </el-drawer>
    <el-drawer v-model="register.show" direction="btt" :with-header="false"
               style="width: 600px; margin: 10px auto" size="320" @open="refreshToken">
      <register-card :token="register.token" />
    </el-drawer>
  </div>
</template>

<style scoped>
:deep(.el-drawer__header) {
  margin-bottom: 10px;
}

:deep(.el-checkbox-group .el-checkbox) {
  margin-right: 10px;
}

:deep(.el-drawer) {
  margin: 10px;
  height: calc(100% - 20px);
  border-radius: 10px;
}

:deep(.el-drawer__body) {
  padding: 0;
}

.manage-main {
  margin: 0 50px;

  .title {
    font-size: 22px;
    font-weight: bold;
  }

  .desc {
    font-size: 15px;
    color: grey;
  }
}

.card-list {
  display: flex;
  gap: 20px;
  flex-wrap: wrap;
}
</style>
