const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
        }
    },
    getProjectName(){
        return {
            projectName: `Family Health and Life Management System for Obese Population`
        } 
    }
}
export default config
