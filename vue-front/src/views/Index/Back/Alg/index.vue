<template>
  <div>
    <label for="fileInput">
      <i aria-hidden="true" class="cursor">上传文件</i>
    </label>
    <input
      v-show="true"
      type="file"
      id="fileInput"
      @change="handleFileChange"
      name="file"
      ref="file"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    handleFileChange(e) {
      let _this = this;
      let file = e.target.files;
      console.log(file,"单文件流文件流");
    }
  }
};
</script>

