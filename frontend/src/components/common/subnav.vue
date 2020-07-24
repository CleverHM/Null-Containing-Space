<template>
    <div class="subbutton d-flex fixed-top justify-content-between">
        <button class="study-button">
            공모전/스터디
        </button>
        <button class="info-button">
            SNS
        </button>
    </div>
</template>

<script>
export default {
    name: 'subnav',

}
</script>

<style scoped>
.subbutton{
    position: fixed;
    top: 50px;
    border-bottom: 1px solid black;
    background-color: #f7f7f7;
}
.study-button{
    height:30px;
    width: 48%;
}
.info-button{
    height:30px;
    width: 48%;

}
</style>