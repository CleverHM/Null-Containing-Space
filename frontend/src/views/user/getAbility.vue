<template>
    <div>
        <div>
        안녕하세요. 알골마스터님
        </div>
        <checkAbility :ability="ability" v-for="ability in abilities" :key="ability.id"></checkAbility>
        
        <div id="step-progress">
            <span></span>
        </div>
    </div>
</template>

<script>
import checkAbility from '../../components/user/checkAbility.vue';
import http from "../../util/http-common.js";

export default {
    name: 'getAbility',
    components: {
        checkAbility,
    },
    data() {
        return {
            abilities: [
          { name: 'html',
            id: '1',
            score: 2},
          { name: 'css',
            id: '2',
            score: 2},
          { name: 'JavaScript',
            id: '3' ,
            score: 2},
          { name: 'cpp',
            id: '4',
            score: 2 },
          { name: 'java',
            id: '5' ,
            score: 2},
        { name: 'Python',
            id: '6' ,
            score: 2},
        { name: 'php',
            id: '7' ,
            score: 2},
        { name: 'sql',
            id: '8' ,
            score: 2},
        { name: 'nosql',
            id: '9' ,
            score: 2},
        { name: 'spring',
            id: '10',
            score: 2 },
        { name: 'django',
            id: '11',
            score: 2 },
        { name: 'bootstrap',
            id: '12',
            score: 2 },
        { name: 'vue',
            id: '13',
            score: 2 },
        { name: 'react',
            id: '14',
            score: 2 },
            ],
    }
    }

}
</script>

<style scoped>
#step-progress {
    text-align: center;
}
</style>