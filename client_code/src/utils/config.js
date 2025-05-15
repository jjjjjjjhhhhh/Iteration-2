const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: 'News and Information Management',
					icon: '',
					child:[

						{
							name:'News and Information',
                            url:'/index/newsList'

						},
					]
				},
				{
					name: 'Healthy Recipe Management',
					icon: '',
					child:[

						{
							name:'Healthy Recipes ',
                            url:'/index/jiankangshipuList'

						},
					]
				},
				{
					name: 'Health Knowledge Management',
					icon: '',
					child:[

						{
							name:'Health Knowledge',
                            url:'/index/jiankangzhishiList'

						},
					]
				},
				{
					name: 'Health Expert',
					icon: '',
					child:[

						{
							name:'Health Expert',
                            url:'/index/jiankangzhuanjiaList'

						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: `Family Health and Life Management System for Obese Population`
        } 
    }
}
export default config
