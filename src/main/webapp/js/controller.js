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

    $scope.getData = function () {
        canUpdate = false;
        return $http.get('rest/').success(function (data) {
            data.sort(function (a, b) {
                return b.created_time - a.created_time;
            });

            for (var index in data) {
                $scope.tmpArray.push(data[index]);
            }

            if (($scope.tmpArray.length == 1) && (data.length == 1)) {
                $scope.tmpArray.push(data[0]);
            }

            if ($scope.items.length === 0) {
                $scope.items = $scope.tmpArray.splice(0, 2);
            }

            //console.log("Updated");
            canUpdate = true;
        });
    };

    $scope.animate = function () {
        var firstChild = angular.element("#content > article").first();
        var height = firstChild.height();

        console.log("Interval!");

        content.animate({scrollTop: height + 1}, 1000, "easeOutExpo", function () {

                $scope.$apply(function () {
                    $scope.items.splice(0, 1);

                    var item = $scope.tmpArray.splice(0, 1)[0];
                    $scope.items.push(item);
                    content.scrollTop(0);
                });

                if ($scope.tmpArray.length <= 3 && canUpdate) {
                    $scope.getData();
                }


                setTimeout($scope.animate, 5 * 1000);
            }
        )
    };

    $scope.getData().success(function (data) {
        content.scrollTop(0);
        setTimeout($scope.animate, 5 * 1000);
    });

    /*
     $scope.animate = function() {
     content.scrollTop(content.scrollTop() +1);
     }

     var intervalId = setInterval($scope.animate, 10);

     content.scroll(function() {
     var firstChild = angular.element("#content > article").first();
     var idx = content.scrollTop();

     if((firstChild.height() + firstChild.offset().top) <= content.offset().top) {
     // Over #content
     $scope.$apply(function() {
     var height = firstChild.height();
     $scope.items.splice(0, 1);
     content.scrollTop(content.scrollTop() - height);
     });
     }

     if($scope.items.length <= 3 && canUpdate) {
     console.log("items.length <= 3");
     $scope.getData();
     }
     });

     */
});
