# if-insurances

I have implemented a feature toggle for showing vehicle information in the car insurance or not. It is default false and
can be viewed with:
http://localhost:8081/features/showVehicleInfo

To alter the toggle you can use this curl:
curl -X PUT http://localhost:8081/features/showVehicleInfo -F "toggleValue=true"

To try the started server:
http://localhost:8081/insurances?byHolderId=1
HolderId 1 and 2 exists.

I have only 3 unit tests(should be more) due to lack of time, but I focused on different types of unit tests.
One for controller with MockMvc, one for domain with pure mockito mocks and one for infrastructure with MockServer.

Hoping to add a junit integration test with its own spring context.

I am a big fan of Domain Driven Design and I have used my knowledge and experience in that creating these 2 servers.
The package structure originates from DDDSample.
Comments about design choices are made in the code, particularly in InsuranceFinder, VehicleClientHttp and
InsuranceRepositoryStatic. 
 


