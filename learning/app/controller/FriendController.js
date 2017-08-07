var FriendModule=angular.module('FriendModule',[]);

FriendModule.controller('FriendController',function(FriendServices,$rootScope,$cookies,$cookieStore)
{
    this.message="this is friend controller";
  var  frndCtrl=this;
   this.tab='suggested'
    this.allUsers={}
    frndCtrl.friendShip={};
    frndCtrl.currentUser=$cookieStore.get("currentUser");
    this.suggestedFriends=function(userId)
    {
    	 console.log(userId);
         FriendServices.suggestedFriends(userId).then
         (
             function(success)
             {
                 console.log("suggested friends::::"+success);
                 
                 $cookieStore.put('allUsers',success.data);
                 frndCtrl.allUsers=success.data;

             },
             function(error)
             {
                 console.log(error);
             }
         )
    }

     this.getFriends=function()
     {
        FriendServices.getFriends(frndCtrl.currentUser).then(

            function(response)
            {
                console.log("friends:::::"+response);
                frndCtrl.friends=response.data;

            },
            function(error)
            {
                console.log(error);
            }
        )
     }

     this.getFriendRequests=function()
     {
          FriendServices.getFriendRequests(frndCtrl.currentUser.userId).then(
              function(response)
              {
                  console.log("friends Requests:::::::::::::::"+response.data);
                  frndCtrl.frndRequests=response.data;
              },
              function(error)
              {
                  console.log(error);
              }
          )
     }

    this.suggestedFriends(frndCtrl.currentUser.userId);
    this.getFriends();
    this.getFriendRequests();

    this.addFriend=function(friendid)
    {
        alert("fid"+friendid);
       alert("uid"+ frndCtrl.currentUser.userId)
       
       angular.forEach(frndCtrl.allUsers,function(value,key)
       {
           console.log(value.userId==friendid)
           if(value.userId==friendid)
           {
               frndCtrl.friend=value;
           }
       });
       frndCtrl.friendShip.userId=frndCtrl.currentUser.userId;
       frndCtrl.friendShip.friendId=friendid;
       
       console.log("friendship object"+frndCtrl.friendShip);
       
       FriendServices.addFriend(frndCtrl.friendShip).then(

           function(response)
           {
               console.log(response);
               frndCtrl.allUsers=response.data;
               frndCtrl.suggestedFriends(frndCtrl.currentUser.userId);
               
           },function(error)
           {
               console.log(error);
           }
       )
       
    }

    this.selectTab=function(tabvalue)
    {
        frndCtrl.tab=tabvalue;
    }

   

    this.updateStatus=function(friendId,status)
    {
        console.log(friendId+"   "+status);

        FriendServices.updateStatus(frndCtrl.currentUser.userId,friendId,status).then(
            function(response)
            {
                console.log(response);
                frndCtrl.frndRequests=response.data;
                frndCtrl.getFriends();
                frndCtrl.getFriendRequests();
                
            },
            function(error)
            {
                console.log(error);
            }
        )
    }
})