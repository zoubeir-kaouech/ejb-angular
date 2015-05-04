'use strict';

/**
 * @ngdoc function
 * @name mytodoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the mytodoApp
 */
angular.module('mytodoApp')
  .controller('MainCtrl', function ($scope, TodoResource) {
	$scope.todos = TodoResource.query()||[];
	
	$scope.addTodo = function(){

		TodoResource.save($scope.todo, function(){
			$scope.todos.push($scope.todo);
			$scope.todo = {};
		});


	};

	$scope.removeTodo = function(index){
		TodoResource.remove({TodoId:$scope.todos[index].id},function(){
			$scope.todos.splice(index,1);
		})

	};
  });
