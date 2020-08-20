<template>
    <tr id="checkAbility">
        <!-- ability name -->
        <td>
            {{ abilityName }}
        </td>

        <!-- button -->
        <td class="radio-wrap">
            <div class="radio-box">
                <input type="radio" value="상" :name="abilityName" @click="checkButton">
                <div class="checkmark"></div>  
            </div>
        </td>
        <td class="radio-wrap">
            <div class="radio-box">
                <input type="radio" value="중" :name="abilityName" @click="checkButton">
                <div class="checkmark"></div>
            </div>
        </td>
        <td class="radio-wrap">
            <div class="radio-box">
                <input type="radio" value="하" :name="abilityName" @click="checkButton">
                <div class="checkmark"></div>
            </div>
        </td>
    </tr>    
</template>

<script>
export default {
    name: 'checkAbility',
    props: [
        'abilityName',
        'abilityLevel'
    ],
    mounted() {
        var value = 0
        if (this.abilityLevel == 1) {
            value = document.getElementsByName(this.abilityName)[0].checked = true
        } else if (this.abilityLevel == 2) {
            value = document.getElementsByName(this.abilityName)[1].checked = true
        } else if (this.abilityLevel == 3) {
            value = document.getElementsByName(this.abilityName)[2].checked = true
        }
    },
    methods: {
        checkButton(event){
            var value = event.toElement.value
            if (value==='상'){
                this.$emit("getAbility", this.abilityName, 1)
            } else if (value === '중'){
                this.$emit("getAbility", this.abilityName, 2)
            } else if (value === '하') {
                this.$emit("getAbility", this.abilityName, 3)
            }
        }
    },
}
</script>


<style scoped>
.radio-wrap{
    position: relative;
}
.radio-box{
    position: relative;
    width: 24px;
    height: 24px;
}
input[type="radio"],
input[type="radio"]:checked {
    appearance: none;
    position: relative;
    width: 24px;
    height: 24px;
    border-radius: 100%;
    background-color: #f7f7f7;
    border: 1px solid #464545;
}
input[type="radio"]:checked + .checkmark {
    position: absolute;
    top: 5px;
    left: 5.5px;
    height: 13px;
    width: 13px;
    border-radius: 100%;
    background-color: #92ADA6;
}
td {
    padding: 20px;
}
</style>