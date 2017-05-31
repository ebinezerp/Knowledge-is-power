var BlogModule=angular.module('BlogModule',['ngCookies']);
BlogModule.controller('BlogController',function(BlogServices,$cookies,$rootScope,$cookieStore)
{
    this.message="this is message blog controller";

    $rootScope.currentBlog=''

   var blogCtrl=this;
   $rootScope.indiblog=false;
   blogCtrl.blog={};
   //$rootScope.currentUser={};
  $rootScope.currentUser=$cookieStore.get('currentUser');
   var s=$rootScope.currentUser;
   console.log("var of s"+s);
 //$rootScope.currentUser=JSON.parse(user);
  //console.log('current user object'+$rootScope.currentUser.role);
  blogCtrl.newBlogdiv=false;
  blogCtrl.allBlogsdiv=true;
  blogCtrl.allBlogs=[];
   this.newBlog=function()
   {
     console.log("blog in controller"+blogCtrl.blog);
     BlogServices.saveBlog(blogCtrl.blog,$rootScope.currentUser.userId).then
     (
         function(response)
         {
             console.log(response);
             if(response.status=='200')
             {
                 blogCtrl.allblogs();
             }
         },
         function(error)
         {
             console.log(error);
         }
     )

   };


   this.button=function(button)
{
    
    blogCtrl.newBlogdiv=button;
    
    
}

 this.allblogs=function()
 {
     console.log('enterend into all blogs');
     BlogServices.allblogs().then
     (
         function(response)
         {
             console.log(response);
             blogCtrl.allBlogs=response.data;
             console.log('all blogs array'+blogCtrl.allBlogs);

         },
         function(error)
         {
             console.log(error);
         }
     )
 }
 this.allblogs();


this.decision=function(status,id)
{
    console.log(status+"  "+id);
    BlogServices.decision(status,id).then
    (
        function(response)
        {
            console.log(response);
            blogCtrl.allBlogs=response.data;
            console.log('after assigning in decision');
        },function(error)
        {
            console.log(error);
        }
    )
    
}


blogCtrl.blogLink=function(blogId)
{
    //$location.path('/indiblog/'+blogId);
    $rootScope.indiblog=true;
    $rootScope.indiblogId=blogId;
}

blogCtrl.backToAllBlogs=function()
{
    $rootScope.indiblog=false;
}

})