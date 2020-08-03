<template>
    <div>
        <Navbar></Navbar>
        <subNav></subNav>
    </div>
</template>

<script>
import Navbar from '../../components/common/Navigation.vue'
import subNav from '../../components/common/subnav.vue'
export default {
    name: 'ModifyUser',
    components: {
        Navbar,
        subNav,
    }
}
</script>

<style scoped>

</style>