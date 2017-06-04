var LoginModule=angular.module('LoginModule',['ngRoute','ngCookies']);
LoginModule.controller('LoginController',function(LoginService,$rootScope,$cookies,$location,$cookieStore){
  this.user={};
  var login=this;
 this.userrole='';
 this.invalid='';
  this.login=function()
  {
      LoginService.login(login.user).then(
          function(response)
          {
                          
                console.log(response);
                console.log("hellooooo"+response.data);
                console.log('testing'+response.data.role);
                $cookies.put('authentiated',true);
                $cookieStore.put('currentUser',response.data);
                $rootScope.currentStore=$cookieStore.get('currentUser');

                $rootScope.authentiated=true;
               if(response.data.role=='admin')
                {
                $cookies.put('role', response.data.role);
                $rootScope.userrole=$cookies.get('role');
                $location.path("/admin");
            }else if(response.data.role=='trainer')
            {
                 $cookies.put('role', response.data.role);
                $rootScope.userrole=$cookies.get('role');
                $location.path('/trainer');
            }
            else if (response.data.role=='student') 
              {
                 $cookies.put('role',response.data.role);
                 $rootScope.userrole=$cookies.get('role');
                 if(response.status=='304')
                 {
                 $rootScope.notVerified=true;
                 }
                 $location.path('/student')
              }else
            {
                 $cookies.put('role', response.data.role);
                $rootScope.userrole=$cookies.get('role');
                $location.path('/index');
            }
          },
          function(error)
          {
                console.log(error);
                if(error.status=='401')
                {
                    login.invalid=true;
                }
                 if(error.status=='304')
                 {
                 login.notVerified=true;
                 }

          }
      )
      

      
  }

});

LoginModule.controller('RegController',function(RegSevice,$location){
    this.user={};
    var reg=this;
    this.register=function()
    {
        console.log(reg.user);
        RegSevice.register(reg.user).then(
            function(response)
            {
                console.log(response);
                console.log('heelllo');
                if(response.status=='200')
                {
                    $location.path("/login");
                }

            },
            function(error)
            {
                console.log(error);
            }
        );
    }
})