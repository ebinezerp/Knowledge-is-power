var LoginService=LoginModule.service('LoginService',function($http,$q,REST_URI){

    this.login=function(user)
    {
        //alert(user.email+"\n"+user.password);
        var deffered=$q.defer();
        $http.post(REST_URI+'login',user).then
        (
            function(response)
            {
                console.log(response);
                deffered.resolve(response)
            },function(error)
            {
                deffered.reject(error);
            }
       
        );
        return deffered.promise;
    }

});

var RegSevice=LoginModule.service('RegSevice',function($http,$q,REST_URI){
    this.register=function(user)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+'adduser',user).then(
            function(response)
            {
                console.log(response);
                deffered.resolve(response);
                console.log('hiiiii');
            },
            function(error)
            {
                console.log(error.data);
                deffered.reject(error);
            }
        );
        return deffered.promise;

    }
})