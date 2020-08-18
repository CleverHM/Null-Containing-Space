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
            var tag = {
                'hash': this.tag,
                'flag': true,
            }
            this.$router.push({name: 'TagResult', params: { tag: tag}})
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