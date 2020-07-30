<template>
    <div>
        <Navbar />
        <subNav />
        <notice1 />
        <notice1 />
        <notice1 />
    </div>
</template>

<script>
import Navbar from '../components/common/Navigation.vue'
import subNav from '../components/common/subnav.vue'
import notice1 from '../components/notice/notice1.vue'
export default {
    name: 'notice',
    components: {
        Navbar,
        subNav,
        notice1,
    }
}
</script>