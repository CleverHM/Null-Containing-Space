<template>
    <div>
        <div>
        안녕하세요. 알골마스터님
        </div>
        <checkAbility></checkAbility>
        <div id="step-progress">ㅇ ㅇ ㅇ ㅇ</div>
    </div>
</template>

<script>
import checkAbility from '../../components/user/checkAbility.vue';
import http from "../../util/http-common.js";

export default {
    name: 'getAbility',
    components: {
        checkAbility,
    }

}
</script>

<style scoped>
#step-progress {
    text-align: center;
}
</style>