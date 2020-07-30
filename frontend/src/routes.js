

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import findPassword from './views/user/FindPassword.vue'
import getAbility2 from './views/user/getAbility2.vue'

import profile from './views/user/profile.vue'

import FeedMain from './views/SNS/IndexFeed.vue'
import FeedDetail from './views/SNS/DetailFeed.vue'
import FeedCreate from './views/SNS/CreateFeed.vue'
import FeedComment from './views/SNS/CommentFeed.vue'

import Components from './views/Components.vue'

import Main from './views/Main.vue'

import StudyList from './views/study/StudyList.vue'
import StudyDetail from './views/study/StudyDetail.vue'
import StudyMatching from './views/study/StudyMatching.vue'

import Search from './views/search/Search.vue';


export default [
    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/find/password',
        name : 'findPassword',
        component : findPassword
    },
    {
        path : '/getAbility',
        name : 'getAbility',
        component : getAbility2
    },
    {
        path : '/user/profile',
        name : 'profile',
        component : profile
    },

    {
        path : '/main',
        name : 'Main',
        component : Main
    },

    {
        path : '/study/list',
        name : 'StudyList',
        component : StudyList
    },
    {
        path : '/study/detail',
        name : 'StudyDetail',
        component : StudyDetail
    },
    {
        path : '/study/matching',
        name : 'StudyMatching',
        component : StudyMatching
    },

    {
        path : '/feed',
        name : 'FeedMain',
        component : FeedMain
    },

    {
        path : '/feed/detail',
        name : 'FeedDetail',
        component : FeedDetail
    },
    {
        path : '/feed/create',
        name : 'FeedCreate',
        component : FeedCreate
    },
    {
        path : '/feed/comment',
        name : 'FeedComment',
        component : FeedComment
    },
    
    {
        path : '/feed/comment',
        name : 'FeedComment',
        component : FeedComment
    },

    
    {
        path : '/search',
        name : 'Search',
        component : Search
    },

    {
        path : '/components',
        name : 'Components',
        component : Components
    },
]
