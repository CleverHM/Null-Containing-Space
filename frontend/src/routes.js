// User
import Login from './views/user/Login.vue'
import getAbility from './views/user/Join/getAbility.vue'
import step1 from './views/user/Join/step1.vue'
import step2 from './views/user/Join/step2.vue'
import step3 from './views/user/Join/step3.vue'
import step4 from './views/user/Join/step4.vue'
import step5 from './views/user/Join/step5.vue'

import findPassword from './views/user/FindPassword.vue'
import profile from './views/user/profile.vue'
import followList from './views/user/followinglist.vue'
import myPost from './views/user/myPost.vue'
import UserModify from './views/user/UserModify.vue'
import modifyAbility from './views/user/modifyAbility.vue'
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
import TeamInfo from './views/team/TeamInfo.vue'
import MatchMember from './views/team/MatchMember.vue'


import Components from './views/Components.vue'
import test from './views/test.vue'

export default [
    // User
    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/user/join/1',
        name : 'step1',
        component : step1,
        props: true
    },    
    {
        path : '/user/join/2',
        name : 'step2',
        component : step2,
        props: true
    },    
    {
        path : '/user/join/3',
        name : 'step3',
        component : step3,
        props: true
    },    
    {
        path : '/user/join/4',
        name : 'step4',
        component : step4,
        props: true
    },
    {
        path : '/user/join/5',
        name : 'step5',
        component : step5,
        props: true
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
        path : '/user/profile/:nickname',
        name : 'profile',
        component : profile,
        props: true,
    },
    {
        path : '/user/modify',
        name : 'UserModify',
        component : UserModify
    },
    {
        path : '/user/modify/ability',
        name : 'modifyAbility',
        component : modifyAbility,
        props: true,
    },
    {
        path : '/user/profile/:nickname/follow',
        name : 'followList',
        component : followList,
        props: true,
    },
    {
        path : '/user/profile/:nickname/myPost',
        name : 'myPost',
        component : myPost,
        props: true,
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
        component : createTeam,
        props: true,
    }, 
    {
        path : '/team/update',
        name : 'updateTeam',
        component : createTeam,
        props: true,
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
        path: '/team/match/list',
        name: 'MatchMember',
        component: MatchMember,
        props: true,
    },
    {
        path: '/team/subject',
        name: 'SubjectForm',
        component: SubjectForm,
    },
    {
        path: '/team/leader',
        name: 'LeaderForm',
        component: SubjectForm,
        props: true,
    },
    {
        path: '/team/leader/update',
        name: 'LeaderUpdateForm',
        component: SubjectForm,
        props: true,
    },
    {
        path: '/team/info/:teamId',
        name: 'TeamInfo',
        component: TeamInfo,
        props: true,
    },


    // Component
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    //test
    {
        path : '/test',
        name : 'test',
        component : test
    },
]