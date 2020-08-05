
// User
import Login from './views/user/Login.vue'
import getAbility from './views/user/Join/getAbility.vue'
import step1 from './views/user/Join/step1.vue'
import step2 from './views/user/Join/step2.vue'
import step3 from './views/user/Join/step3.vue'
import step4 from './views/user/Join/step4.vue'
import step5 from './views/user/Join/step5.vue'

import findPassword from './views/user/FindPassword.vue'
import Join from './views/user/Join.vue'
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
import SubjectForm from './views/team/subjectForm.vue'
import leaderForm from './views/team/leaderForm.vue'


import Components from './views/Components.vue'

export default [
    // User
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
        path : '/user/step1',
        name : 'step1',
        component : step1
    },    
    {
        path : '/user/step2',
        name : 'step2',
        component : step2
    },    
    {
        path : '/user/step3',
        name : 'step3',
        component : step3
    },    
    {
        path : '/user/step4',
        name : 'step4',
        component : step4
    },
    {
        path : '/user/step5',
        name : 'step5',
        component : step5
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
        component : profile,
        props: true,
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
        path: '/team/subject',
        name: 'SubjectForm',
        component: SubjectForm,
    },
    {
        path: '/team/leader1',
        name: 'LeaderbeforeForm',
        component: SubjectForm,
        props: true,

    },
    {
        path: '/team/leader2',
        name: 'LeaderForm',
        component: leaderForm,
        props: true,
    },


    // Component
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
]
