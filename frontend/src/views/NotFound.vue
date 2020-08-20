<template>
  <div id="NotFound">
    <div class="area">
        <div class="d-flex flex-column justify-content-center align-content-center align-items-center">
            <b-icon-x-circle scale="2" style="color:#C4BCB8; margin-bottom: 20px;"/>
            <h5>Page NotFound</h5>
            <button class="not-btn" @click="goMain">
                <b-icon-reply class="mr-2" variant="white" scale="1.5"/>HOME
            </button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "NotFound",
    methods: {
        goMain() {
            this.$router.push({ name: 'Main' });
        }
    }

}
</script>

<style scoped>
.area {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    color: #C4BCB8;
}

h5 {
    margin-bottom: 50px;
}

.not-btn {
    padding: 10px;
    border-radius: 10px;
    background-color: #ACCCC4;
    color: white;
}
</style>