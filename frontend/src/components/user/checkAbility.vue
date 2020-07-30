<template>
    <div id="radio-pillbox">
        <div>
            <span>
                {{ ability.name }} : 
            </span>
            <div class="btngroup">
                <button id="high" class="cusbtn" @click="isactiveHigh" :class="{'active':acthigh}">상</button>
                <button id="medium" class="cusbtn" @click="isactiveMedium" :class="{'active':actmd}">중</button>
                <button id="low" class="cusbtn" @click="isactiveLow" :class="{'active':actlow}">하</button>
            </div>
        </div>
        
        
    </div>
</template>

<script>
export default {
    name: "checkAbility",
    props: {
        ability: {
        type: Object,
        },

  },
    data() {
        return {
            acthigh: false,
            actmd: true,
            actlow: false,
        }
    },
    methods: {
        isactiveHigh() {
            this.acthigh = true;
            this.actmd = false;
            this.actlow = false;
        },
        isactiveMedium() {
            this.acthigh = false;
            this.actmd = true;
            this.actlow = false;
        },
        isactiveLow() {
            this.acthigh = false;
            this.actmd = false;
            this.actlow = true;
        }
    }

}
</script>
<style scoped>

.btngroup {
    background-color: white;
    display: inline-block;
}
#high{
    height: 30px;
    width: 50px;

    border-top-width: 1px;
    border-top-style: solid;
    border-top-color: #464545;

    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;

    border-bottom-width: 1px;
    border-bottom-style: solid;
    border-bottom-color: #464545;
    
    border-left-width: 1px;
    border-left-style: solid;
    border-left-color: #464545;

}
#medium{
    height: 30px;
    width: 50px;
    border-top-width: 1px;
    border-top-style: solid;
    border-top-color: #464545;

    border-right-width: 1px;
    border-right-style: solid;
    border-right-color: #464545;
    
    border-bottom-width: 1px;
    border-bottom-style: solid;
    border-bottom-color: #464545;
    
    border-left-width: 1px;
    border-left-style: solid;
    border-left-color: #464545;
}
#low{
    height: 30px;
    width: 50px;
    border-top-width: 1px;
    border-top-style: solid;
    border-top-color: #464545;

    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;

    border-right-width: 1px;
    border-right-style: solid;
    border-right-color: #464545;
    
    border-bottom-width: 1px;
    border-bottom-style: solid;
    border-bottom-color: #464545;
    
}
.active{
    background: #464545;
    color: #f7f7f7;
}
.cusbtn:visited{
    border: none;
}
.cusbtn:active{
    border: none;
}
</style>

