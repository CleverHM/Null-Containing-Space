<template>
    <div id="TagBadge" class="mx-1">
        <button><span>{{ tag }}</span></button>
    </div>
</template>

<script>
export default {
    name: 'TagBadge',
    props: [
        'tag',
    ]
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