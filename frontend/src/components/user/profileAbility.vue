<template>
    <tr>
        <td class="name"> {{ name.name }} </td>
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
    name: "profileAbility",
    props: [
        'name',
        'ability',
    ]
    
}
</script>

<style scoped>
td{
    padding: 10px 0px;
}
.ability{
    text-align: center;
}
.name{
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