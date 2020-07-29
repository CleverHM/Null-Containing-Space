<template>
    <div>
        <label class="radioContainer">상
        <input type="radio">
        <span class="circle"></span>
        </label>

    </div>
</template>

<script>
export default {
    name: 'checkAbility2',

}
</script>
<style>
.radioContainer{
    display: inline-block;
    position: relative;
    cursor: pointer;
    font-size: 22px;
    user-select :none;
    padding-left: 30px;
}
.radioContainer input{
    display: none;
}
.radioContainer .circle {
    display: inline-block;
    width: 25px;
    height: 25px;
    background-color: #fff;
    position: absolute;
    left: 0;
    top: 0;
    border-radius: 50%;
}
</style>