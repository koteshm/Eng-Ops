/**
 * Created by Saurabh_Nayar on 5/25/2016.
 */

angular.module('engOps', []).controller('bestEstimateCtlr', function($scope, $http) {
    $scope.estimates = [
        {quarter: '2017, Q1', manager: 'M1', 'bestEstimates': 1000, 'actual': 100},
        {quarter: '2016, Q4', manager: 'M2', 'bestEstimates': 2000, 'actual': 200}

    ];


    // Simple GET request example:
    $http({
        method: 'GET',
        url: '/bestestimates/getAllEstimates'
    }).then(function successCallback(response) {
        $scope.estimates = response.data;
        // this callback will be called asynchronously
        // when the response is available
    }, function errorCallback(response) {
        alert(response)
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });



});

angular.module('engOpsTM', []).controller('bestEstimateTeamManager', function($scope, $http) {
    $scope.estimate = {quarter: '2017, Q1', manager: 'M1', 'actual': 100};

    $scope.update = function (estimate) {
        var request = $http({
            method: "post",
            url: "/bestestimates/updateEstimate",
            "Content-Type": "application/json",
            data: estimate
        });

        request.success(
            function( html ) {
                alert("Success!!!")
            }
        );
    }



});