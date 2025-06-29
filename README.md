# if-insurances

I have implemented a feature toggle for showing vehicle information in the car insurence or not. It is default false and
can be viewed with:
http://localhost:8081/features/showVehicleInfo

To alter the toggle you can use this curl:
curl -X PUT http://localhost:8081/features/showVehicleInfo -F "toggleValue=true"

To try the started server:
http://localhost:8081/insurances?byHolderId=1
HolderId 1 and 2 exists.
 


