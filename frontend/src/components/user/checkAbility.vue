<template>
    <tr id="checkAbility">
        <!-- ability name -->
        <td>
            {{ ability.name }}
        </td>

        <!-- button -->
        <td class="radio-wrap">
            <input type="radio" value="상" :name="ability.name" checked @click="checkButton">
            <div class="checkmark"></div>  
        </td>
        <td class="radio-wrap">
            <input type="radio" value="중" :name="ability.name" @click="checkButton">
            <div class="checkmark"></div>
        </td>
        <td class="radio-wrap">
            <input type="radio" value="하" :name="ability.name" @click="checkButton">
            <div class="checkmark"></div>
        </td>
    </tr>    
</template>

<script>
export default {
    name: 'checkAbility',
    props: {
        ability: {
            type: Object,
        },
    },
    data() {
        return {
            high : false,
            medium: false,
            low: false,
        
        }
    },
    methods: {
        checkButton(event){
            var value = event.toElement.value
            if (value==='상'){
                this.$emit("getAbility", this.ability.name, 1)
            } else if (value === '중'){
                this.$emit("getAbility", this.ability.name, 2)
            } else if (value === '하') {
                this.$emit("getAbility", this.ability.name, 3)
            }
        }
    },
}
</script>


<style scoped>
.radio-wrap{
    position: relative;
}
input[type="radio"],
input[type="radio"]:checked {
    appearance: none;
    position: relative;
    left: -5px;
    width: 24px;
    height: 24px;
    border-radius: 100%;
}
input[type="radio"]{
    position: relative;
    background-color: #f7f7f7;
    border: 1px solid #464545;
    
}
input[type="radio"]:checked{
    position: relative;
    background-color: #f7f7f7;
    border: 1px solid #464545;
}

input[type="radio"]:checked + .checkmark {
    position: absolute;
    top: 25px;
    left: 20px;
    height: 13px;
    width: 13px;
    border-radius: 100%;
    background-color: #92ADA6;
}
td {
    padding: 20px;
}
</style>