var phonecatApp = angular.module('instafeedApp', []);

function calcTime(date) {
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

phonecatApp.controller('instafeedController', function ($scope, $http, $interval) {
    $scope.getData = function () {

        $http.get('rest/').success(function (data) {
            for (var index in data) {
                data[index].time = calcTime(data[index].created_time);
            }


            data.sort(function (a, b) {
                return a.created_time - b.created_time;
            });

            $scope.items = data;
        });
    };

    setInterval($scope.getData, 10*1000);
});