FriendModule.service('FriendServices',function($http,$q,REST_URI)
{
    this.suggestedFriends=function(userId)
    {
        var deffered=$q.defer();
        $http.get(REST_URI+"suggestedFriends/"+userId).then
        (
            function(success)
            {
                console.log(success);
                deffered.resolve(success);
            },
            function(error)
            {
                console.log(error);
                deffered.reject(error);
            }
        )
        return deffered.promise;
       
    }
 
    this.getFriendRequests=function(userId)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+"getFriendrequests",userId).then(
            function(response)
            {
                console.log(response);
                deffered.resolve(response);
            },
            function(error)
            {
                console.log(error);
                deffered.reject(error);
            }
        )

        return deffered.promise;
    }

    this.getFriends=function(user)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+"getfriends",user)
       .then(
           function(response)
           {
               console.log(response);
               deffered.resolve(response);
           },function(error)
           {
               console.log(error);
               deffered.reject(error);
           }
       )
       return deffered.promise;
    }
    this.addFriend=function(friendShip)
    {
        console.log("friend id  "+friendShip.friendId);
        console.log("user id  "+friendShip.userId);
        var deffered=$q.defer();
        $http.post(REST_URI+"addfriend",friendShip).then
        (
            function(response)
            {
                console.log(response);
                deffered.resolve(response);
            },function(error)
            {
                console.log(error);
                deffered.reject(error);
            }
        )
        return deffered.promise;
    }

    this.updateStatus=function(friendId,userId,status)
    {
        var deffered=$q.defer();
        $http.post(REST_URI+'updateStatus/'+friendId+'/'+userId,status).then(
            function(response)
            {
                console.log(response);
                deffered.resolve(response);
            },
            function(error)
            {
                console.log(error);
                deffered.reject(error);
            }
        )
        return deffered.promise;
    }
})
