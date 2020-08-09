<template>
    <div class="ability-body">
        <div class="ability-kinds"> {{ abilityname.name }} </div>
        <div class="ability-ability" v-if="ability === 1">
            <div class="high">
                <i class="far fa-gem gem-icon-low" aria-hidden="true"/>
                <i class="far fa-gem gem-icon-middle" aria-hidden="true"/>
                <i class="far fa-gem gem-icon-high" aria-hidden="true"/>
            </div>
        </div>
        <div class="ability-ability" v-else-if="ability === 2">
             <div class="middle">
                 <i class="far fa-gem gem-icon-low" aria-hidden="true"/>
                <i class="far fa-gem gem-icon-middle" aria-hidden="true"/>
                <i class="far fa-gem gem-icon-high" aria-hidden="true"/>
            </div> 
        </div>
        <div class="ability-ability" v-else-if="ability === 3"> 
            <div class="low">
                <i class="far fa-gem gem-icon-low" aria-hidden="true"/>
                <i class="far fa-gem gem-icon-middle" aria-hidden="true"/>
                <i class="far fa-gem gem-icon-high" aria-hidden="true"/>
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
.high, .middle, .low{
    display: inline-block;
    padding: 0 7px 0 0;
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
  color: #E3C700;
}
.middle > .gem-icon-high{
    color: #EDECEA;
}
.low > .gem-icon-high,
.low > .gem-icon-middle{
    color: #EDECEA;
}
</style>