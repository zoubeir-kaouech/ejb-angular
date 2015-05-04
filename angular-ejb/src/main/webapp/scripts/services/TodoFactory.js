/**
 * Created by Karim on 4/13/2015.
 */
'use strict'
angular.module('mytodoApp').factory('TodoResource', function($resource){
    var resource = $resource(
        '/angular-ejb/rest/todos/:TodoId',
        {TodoId:'@id'}
    );
    return resource;

});
