<script setup>
import axios from "axios";

defineProps({
  token: String
});
</script>

<template>
  <div class="register-card">
    <div class="title"><i class="fa-regular fa-square-plus"></i> 添加新书籍</div>
    <div class="desc">请按照以下步骤完成新书籍的添加，添加后即可在图书馆系统中进行管理和查询。</div>
    <el-divider style="margin: 10px 0"/>
    <div class="sub-title">1.确认书籍信息</div>
    <div class="desc">请确保书籍的标题、作者、出版社等信息的准确性，以便读者能方便地查找和引用。</div>
    <div class="sub-title" style="margin-top: 10px">2.填写书籍详细信息</div>
    <div class="desc">包括书籍的出版日期、ISBN编号以及分类等，确保信息完整，以便系统有效管理。</div>
    <el-input v-model="axios.defaults.baseURL" readonly placeholder="请输入书籍详细信息"/>
    <div class="sub-title" style="margin-top: 10px">3.输入授权码</div>
    <div class="desc">输入管理员授权码以确认您的操作权限。</div>
    <el-input :model-value="token" readonly/>
  </div>
</template>

<style scoped>
.register-card {
  margin: 15px 20px;

  .title {
    font-size: 18px;
    font-weight: bold;
  }

  .sub-title {
    font-size: 16px;
    font-weight: bold;
    color: dodgerblue;
  }

  .desc {
    font-size: 13px;
    color: grey;
    line-height: 16px;
  }
}
</style>
