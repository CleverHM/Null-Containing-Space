<template>
    <div v-if="tag" id="TagBadge" class="mx-1" @click="goTagResult">
        <button><span>{{ tag }}</span></button>
    </div>
</template>

<script>
export default {
    name: 'TagBadge',
    props: [
        'tag',
    ],
    methods: {
        goTagResult() {
            this.$router.push({name: 'TagResult', params: { tag: this.tag}})
        },
    }
}
</script>

<style scoped>
#TagBadge {
    display: inline;
}
#TagBadge span {
    padding: 4px;
    border-radius: 3px;
    background-color: #C4BCB8;
    color: #f7f7f7;
    font-size: 15px;
}
</style>