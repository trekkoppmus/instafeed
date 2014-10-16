var instafeedApp = angular.module('instafeedApp', []);

instafeedApp.filter('sublist', function(){
    return function(input, range, start){
        return input.slice(start, start+range);
    };
});

instafeedApp.controller('instafeedController', function ($scope, $http, $interval) {

    $scope.calcTime = function (date) {
        var now = new Date();
        date = new Date(date);

        var millisec = now - date;
        var sec = parseInt(millisec / 1000);
        var min = parseInt(sec / 60);
        var hours = parseInt(min / 60);
        var days = parseInt(hours / 24);
        var weeks = parseInt(days / 7);
        var years = parseInt(weeks / 52);

        if (years > 0) {
            return years + "y";
        } else if (weeks > 0) {
            return weeks + "w";
        } else if (days > 0) {
            return days + "d";
        } else if (hours > 0) {
            return hours + "h";
        } else if (min > 0) {
            return min + "m"
        } else {
            return sec + "s";
        }
    }

    $scope.items = [];

    $scope.getData = function () {
        $http.get('rest/').success(function (data) {
            data.sort(function (a, b) {
                return b.created_time - a.created_time;
            });

            for(var index in data) {
                $scope.items.push(data[index]);
            }

        });
    };

    $scope.getData();

    var content = angular.element("#content");

    $scope.animate = function() {
        content.scrollTop(content.scrollTop() +1);
    }

    var intervalId = setInterval($scope.animate, 10);

    content.scroll(function() {
        var firstChild = angular.element("#content > article").first();
        var idx = content.scrollTop();

        if(firstChild.height() + firstChild.offset().top < 0) {
            $scope.$apply(function() {
                clearInterval(intervalId);
                $scope.items.splice(0, 1);
                content.scrollTop(idx - firstChild.height());
                intervalId = setInterval($scope.animate, 10);
            });
        }

        if($scope.items.length <= 3) {
            $scope.getData();
        }
    });
});