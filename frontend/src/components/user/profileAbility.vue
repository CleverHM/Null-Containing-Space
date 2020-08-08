<template>
    <div class="ability-body">
        <div class="ability-kinds"> {{ abilityname.name }} </div>
        <div class="ability-ability" v-if="ability === 1">
            <div class="high">
                <i class="far fa-gem gem-icon" aria-hidden="true"/>상
            </div>
        </div>
        <div class="ability-ability" v-else-if="ability === 2">
             <div class="medium">
                 <i class="far fa-gem gem-icon" />중
            </div> 
        </div>
        <div class="ability-ability" v-else-if="ability === 3"> 
            <div class="low">
                <i class="far fa-gem gem-icon" />하
            </div> 
        </div>
    </div>
        
       
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
.ability-body{
    padding: 13px 0px;
    border-top: 1px solid #E2DFD8;
}
.high, .medium, .low{
    display: inline-block;
    border: 1px solid #464545;
    border-radius: 30px;
    padding: 0 7px 0 0;
}
.high{
    border: 2px solid #ACCCC4;
}
.ability-kinds, .ability-ability{
  display: inline-block;
  width: 50%;
  text-align: center;
}
.ability-kinds{
    text-align: left;
}

.fa-gem {
  margin: 0 5px 0 7px;
  color: #ADCCC4;
}

</style>