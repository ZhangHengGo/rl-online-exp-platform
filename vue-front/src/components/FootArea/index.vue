<template>
    <div class="foot-area flex">
        <div class="info">
            底部固定区域
        </div>
    </div>
</template>

<script>
export default {
    name: 'FootArea',
}
</script>

<style lang="less" scoped>
.foot-area {
    position: fixed;
    bottom: 0;
    right: 0;
    z-index: 1;
    width: calc(100% - 180px);
    height: 60px;
    background-color: rgb(250, 250, 250);
    box-sizing: border-box;
    padding: 10px 15px;
    box-shadow: 0 0 3px rgba(0, 0, 0, .2);
    align-items: center;
}
</style>