Assumptions
1) You have 2 pods having the same  key and value pairs in the lable but different versions.Your pods are created
2) Your service is created for the pods.(One service targetting both the pods)

Sequence to create the artifacts
1) Create the Meshwide policy first for transport authentication (enables TLS on transport within the cluster,prevents man in the middle attack)
2) Create the destination rules for both the pods
3) Create the gateway for external world (to get the external ip, check the services created under istio-system)
4) Create the virtual services based on how you want to redirect the request from the gateway to the service over the pods
    4.1) Canary Deployment
	    4.1.1) All the requests should go to v1 pod only
		4.1.2) All the requests having a specific header and value go to v2 only and the rest go to v1 only (for testing purpose)
		4.1.3) Once testing done - all the requests go to v2 only.
		4.1.4) Delete the v1 pod and deployment
		4.1.5) Canary deployment complete