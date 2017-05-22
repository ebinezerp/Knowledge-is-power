var BlogModule=angular.module('BlogModule',[]);
BlogModule.controller('BlogController',function(BlogServices,$cookies,$rootScope)
{
   var blogCtrl=this;
   blogCtrl.blog={};
  $rootScope.currentUser=$cookies.get('currentUserId');
  console.log($rootScope.currentUser);
  blogCtrl.newBlogdiv=false;
  blogCtrl.allBlogsdiv=true;
  blogCtrl.allBlogs=[];
   this.newBlog=function()
   {
     console.log(blogCtrl.blog);
     BlogServices.saveBlog(blogCtrl.blog,$rootScope.currentUser).then
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
    blogCtrl.newBlogdiv=true;
    blogCtrl.allBlogsdiv=false;
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


})