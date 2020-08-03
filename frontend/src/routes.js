
// User
import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import findPassword from './views/user/FindPassword.vue'
import getAbility from './views/user/getAbility.vue'
import profile from './views/user/profile.vue'
import UserModify from './views/user/UserModify.vue'

// Feed
import FeedMain from './views/SNS/IndexFeed.vue'
import FeedDetail from './views/SNS/DetailFeed.vue'
import FeedCreate from './views/SNS/CreateFeed.vue'
import FeedComment from './views/SNS/CommentFeed.vue'

// Main
import Main from './views/Main.vue'
import Search from './views/search/Search.vue';
import Notice from './views/Notice.vue'

// study
import StudyList from './views/study/StudyList.vue'
import StudyDetail from './views/study/StudyDetail.vue'
import StudyMatching from './views/study/StudyMatching.vue'

// team
import teamMatch from './views/team/teamMatch.vue'
import Specs from './views/team/Specification.vue'
import createTeam from './views/team/createTeam.vue'
import memberForm from './views/team/memberForm.vue'


import Components from './views/Components.vue'

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
        component : getAbility
    },
    {
        path : '/user/profile',
        name : 'profile',
        component : profile
    },
    {
        path : '/user/modify',
        name : 'UserModify',
        component : UserModify
    },
    // Main
    {
        path : '/main',
        name : 'Main',
        component : Main
    },
    {
        path : '/search',
        name : 'Search',
        component : Search
    },
    {
        path : '/notice',
        name : 'Notice',
        component : Notice
    },
    // Study
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
    // Feed
    {
        path : '/feed',
        name : 'FeedMain',
        component : FeedMain
    },

    {
        path : '/feed/:postId/detail',
        name : 'FeedDetail',
        component : FeedDetail,
        props: true,
    },
    {
        path : '/feed/create',
        name : 'FeedCreate',
        component : FeedCreate
    },
    {
        path : '/feed/update',
        name : 'FeedUpdate',
        component : FeedCreate,
        props: true,
    },
    {
        path : '/feed/comment',
        name : 'FeedComment',
        component : FeedComment
    },   
    // Team
    {
        path : '/team/create',
        name : 'createTeam',
        component : createTeam
    }, 
    {
        path : '/team/specs',
        name : 'Specs',
        component : Specs
    }, 
    {
        path : '/team/match',
        name : 'teamMatch',
        component : teamMatch
    },
    {
        path: '/team/member',
        name: 'memberForm',
        component: memberForm
    },

    // Component
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
]
