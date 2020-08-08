<template>
    <tr>
        <td class="abilityname"> {{ abilityname.name }} </td>
        <td class="ability" v-if="ability === 1">
            <div class="high">상</div>
        </td>
        <td class="ability" v-else-if="ability === 2">
             <div class="medium">중</div> 
        </td>
        <td class="ability" v-else-if="ability === 3"> 
            <div class="low">하</div> 
        </td>
    </tr>
        
       
</template>

<script>
export default {
    props: {
        abilityname: {
            type: Object,
        },
        ability: {
            type: Number,
        }
    },
}   
</script>

<style scoped>
td{
    padding: 10px 0px;
}
.ability{
    text-align: center;
}
.abilityname{
    width: 100%;
}
.high{
    background-color: #ACCCC4;
    border-radius: 100%;
}
.medium{
    background-color: #C4BCB8;
    border-radius: 100%;
}
.low{
    background-color: #C4BCB8;
    border-radius: 100%;
}
</style>