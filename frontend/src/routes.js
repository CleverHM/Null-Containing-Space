

import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import findPassword1 from './views/user/FindPassword1.vue'
import findPassword2 from './views/user/FindPassword2.vue'
import findPassword3 from './views/user/FindPassword3.vue'

import profile from './views/user/profile.vue'

import FeedMain from './views/SNS/IndexFeed.vue'
import Components from './views/Components.vue'

import Main from './views/Main.vue'

import StudyList from './views/study/StudyList.vue'
import StudyDetail from './views/study/StudyDetail.vue'


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
        path : '/find/password/email',
        name : 'findPassword1',
        component : findPassword1
    },
    {
        path : '/find/password/question',
        name : 'findPassword2',
        component : findPassword2
    },
    {
        path : '/find/password/',
        name : 'findPassword3',
        component : findPassword3
    },
    {
        path : '/user/profile/',
        name : 'profile',
        component : profile
    },
    {
        path : '/main/',
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
        path : '/feed/',
        name : 'FeedMain',
        component : FeedMain
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    
]
