//  路由配置信息
// redirect(重定向)和component(组件)在绑定路由的时候必须有一个
// component(组件)可以是自己先定义后使用 也可以从其他文件导入 
// 定义一个名为Home的组件：const Home = {template:'<div>Home</div>'} 绑定这个组件 {path: 'home', component: Home}
// 导入格式 {path: 'home',component: () => import('')} 
export default [
    {
        path: '/',
        redirect: '/index/home'
    },
    {
        path: '/index',
        component: () => import('@/views/Index'),
        children: [
            {
                path: '',
                redirect: 'home',
            },
            {
                path: 'home',
                component: () => import('@/views/Index/Home')
            },
            {
                path: 'back',
                component: () => import('@/views/Index/Back'),
                children: [
                    {
                        path: 'userlist',
                        component: () => import('@/views/Index/Back/UserList')
                    },
                    {
                        path: 'plot',
                        component: () => import('@/views/Index/Back/Plot')
                    },
                    {
                        path: 'alg',
                        component: () => import('@/views/Index/Back/Alg')
                    },
                    {
                        path: 'bmyinfo',
                        component: () => import('@/views/Index/Back/BMyInfo')
                    },
                    {
                        path: 'seatlist',
                        component: () => import('@/views/Index/Back/SeatList')
                    },
                    {
                        path: 'noticelist',
                        component: () => import('@/views/Index/Back/NoticeList')
                    },
                    {
                        path: 'reservelist',
                        component: () => import('@/views/Index/Back/ReserveList')
                    },
                    {
                        path: 'testdetail',
                        component: () => import('@/views/Index/Front/TestDetail')
                    },
                    {
                        path: 'episodeinfo',
                        component: () => import('@/views/Index/Front/EpisodeInfo'),
                        props:true
                    }
                ]
            },
            {
                path: 'front',
                component: () => import('@/views/Index/Front'),
                children: [
                    {
                        path: 'fmyinfo',
                        component: () => import('@/views/Index/Front/MyInfo')
                    },
                    {
                        path: 'notice',
                        component: () => import('@/views/Index/Front/Notice')
                    },
                    {
                        path: 'reserveseat',
                        component: () => import('@/views/Index/Front/ReserveSeat')
                    },
                    {
                        path: 'reserveinfo',
                        component: () => import('@/views/Index/Front/ReserveInfo')
                    },
                    {
                        path: 'feedback',
                        component: () => import('@/views/Index/Front/Feedback')
                    },
                    {
                        path: 'addtest',
                        component: () => import('@/views/Index/Front/AddTest')
                    },
                    {
                        path: 'testinfo',
                        component: () => import('@/views/Index/Front/TestInfo')
                    },

                ]
            },
        ],
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/Login')
    },
]