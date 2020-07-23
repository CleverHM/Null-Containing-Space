<template>
    <div class="subbutton d-flex fixed-top">
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
    top:50px;
    background-color: #EDECEA;
}
.study-button{
    height:30px;
    width:50%;
}
.info-button{
    height:30px;
    width:50%;

}
</style>