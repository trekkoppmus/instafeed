var instafeedApp = angular.module('instafeedApp', []);

instafeedApp.filter('sublist', function () {
    return function (input, range, start) {
        return input.slice(start, start + range);
    };
});

instafeedApp.controller('instafeedController', function ($scope, $http, $interval) {

    $scope.title = "Instabin";

    $scope.calcTime = function (date) {
        var now = new Date();
        date = new Date(date);

        var millisec = now - date;
        var sec = parseInt(Math.floor(millisec / 1000));
        var min = parseInt(Math.floor(sec / 60));
        var hours = parseInt(Math.floor(min / 60));
        var days = parseInt(Math.floor(hours / 24));
        var weeks = parseInt(Math.floor(days / 7));
        var years = parseInt(Math.floor(weeks / 52));

        if (years > 0) {
            return years + " y";
        } else if (weeks > 0) {
            return weeks + " w";
        } else if (days > 0) {
            return days + " d";
        } else if (hours > 0) {
            return hours + " h";
        } else if (min > 0) {
            return min + " m"
        } else {
            return sec + " s";
        }
    };

    $scope.items = [];
    $scope.tmpArray = [];

    var canUpdate;
    var content = angular.element("#content");
    var currentImage = 0;

    $scope.getData = function () {
        canUpdate = false;
        return $http.get('http://håkonjahre.no/instabin/rest/').success(function (data) {
            data.sort(function (a, b) {
                return b.created_time - a.created_time;
            });

            for (var index in data) {
                data[index].class="";
                $scope.tmpArray.push(data[index]);
            }

            if (($scope.tmpArray.length == 1) && (data.length == 1)) {
                $scope.tmpArray.push(data[0]);
            }

            //console.log("Updated");
            canUpdate = true;
        });
    };

    $scope.animate = function () {
        if($scope.tmpArray.length > 0) {
            var nextImage = (currentImage + 1) % 3,
                lastImage = (currentImage - 1) % 3;

            if(currentImage === 0) {
                lastImage = 2;
            }

            $scope.items[currentImage].class = "showMe";
            $scope.items[nextImage] = $scope.tmpArray.splice(0, 1)[0];
            $scope.items[lastImage].class = "done";

            currentImage = nextImage;
        }

        if($scope.tmpArray.length < 3) {
            $scope.getData();
        }
    };

    $scope.getData().success(function (data) {
        /*content.scrollTop(0); */

        for(var i in [0,1,2]) {
            $scope.items.push($scope.tmpArray.slice(0,1)[0]);
        }

        $scope.items[currentImage].class = "showMe";
        $interval($scope.animate, 5 * 1000);
    });
});
