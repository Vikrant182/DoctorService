Canary Deployment Steps
-----------------------------------------------
-----------------------------------------------

 Version 1 of Application
 ------------------------ 
 1. Create Docker Image of your application
    - Have a Dockerfile in the root folder as present in this project
    - You can create docker image using automated Devops pipeline tools or you can do it manually
    - To do it manually, Switch to the root folder in Docker terminal
    - Run docker build . -t "imagename:tag"
    
 2. Push docker image to the container registry (Azure)
    - Create a new Tag of your previously created image and append the path to
      container registry(ACR) using below command
      docker tag "imagename:tag" "<container registry name>.azurecr.io/imagename:tag"
    - You can look up the entire path instead of remembering your container registry name by logging on the Azure
      portal and clicking on the registry within the resource group you created or are assigned to.
    - Login to the container registry using below cmd
       az acr login <registry name>
       Once logged in the command output will show "Login succeeded"
    - Then push the docker image to acr using below command
      Docker push <container registry name>.azurecr.io/<imagename:tag>
    - Refresh the azure portal and look at the registry within your resource group, 
      you should be able to see a new repository with your image name created, 
      click on the repository you will see the tag attached to it.  
      
  3. Deploy the image to K8 cluster
	   - Create a deployment file (use   deployment.yaml under kubernetes folder) and run
	   - kubectl apply -f <path of the deployment file> -n <namespace with istio injection enabled>. 
	     This should create a Deployment and a pod in the namespace mentioned.
	   - This should also create a service for the pod.
   
  4. Describe your pod and note down the ip address assigned by DNS server pod to it
     - Run kubectl get pod <pod name> -n <namespace> -o wide.
  
  5. Describe the service that was created and note the ip mentioned under endpoints object
    - Run kubectl describe svc <service name> -n <namespace>
    - The ip under the endpoints object should be same as the ip of your pod
  
  6. Deploy an ingress gateway to receive request from outside world
    - Use istiogateway.yaml, apply it to K8 cluster using Kubectl apply -f <path to yaml> -n <namespace>
    
  7. Now create a virtual service bound to Gateway which will route the request from gateway to the service
     of the pod
     - use virtualservice_roundrobin_Defaultk8.yaml ,apply it o K8
  8. Find the external ip of your cluster
    - Run kubectl get services -n istio-system, note down the external ip against the ingress service
  9. Hit your endpoint and you should be able to see the response       
         
 -----------------------------------------------

 Version 2 of Application
 ------------------------ 
 1. Create Docker Image of your application
    - Have a Dockerfile in the root folder as present in this project
    - You can create docker image using automated Devops pipeline tools or you can do it manually
    - To do it manually, Switch to the root folder in Docker terminal
    - Run docker build . -t "imagename:tag"
    
 2. Push docker image to the container registry (Azure)
    - Create a new Tag of your previously created image and append the path to
      container registry(ACR) using below command
      docker tag "imagename:tag" "<container registry name>.azurecr.io/imagename:tag"
    - You can look up the entire path instead of remembering your container registry name by logging on the Azure
      portal and clicking on the registry within the resource group you created or are assigned to.
    - Login to the container registry using below cmd
       az acr login <registry name>
       Once logged in the command output will show "Login succeeded"
    - Then push the docker image to acr using below command
      Docker push <container registry name>.azurecr.io/<imagename:tag>
    - Refresh the azure portal and look at the registry within your resource group, 
      you should be able to see a new repository with your image name created, 
      click on the repository you will see the tag attached to it.  
      
  3. Deploy the image to K8 cluster
	   - Create a deployment file (use   deployment.yaml under kubernetes/v2 folder) and run
	   - kubectl apply -f <path of the deployment file> -n <namespace with istio injection enabled>. 
	     This should create a Deployment and a pod in the namespace mentioned.
	   - This should also create a service for the pod.
   
  4. Describe your pod and note down the ip address assigned by DNS server pod to it
     - Run kubectl get pod <pod name> -n <namespace> -o wide.
  
  5. Describe the service that was created and note the ip mentioned under endpoints object
    - Run kubectl describe svc <service name> -n <namespace>
    - The ip under the endpoints object should be same as the ip of your pod
  
  6. Create a new Mesh Policy and new Destination rule
    - Apply mesh-wide.yaml and Doctor_Destination_Rule.yaml to K8
 
  6. Now update the virtual service bound to Gateway which will route the request from gateway to the service
     of the pod to route to v2 app only on certain criteria
     - use virtualservice_ScenarioBased.yaml.yaml ,apply it o K8
  7. Find the external ip of your cluster
    - Run kubectl get services -n istio-system, note down the external ip against the ingress service
  8. Hit your endpoint with the criteria, you should see the response from new version of app , else you should
     see the response from old end point
-------------------------     
Switch to latest Version app 
-------------------------
1) Updated your virtual service to re route all the request only to the latest version of the app
2) Delete the old version app
3) Canary deployment is done


-----------------------------------------------------------------------------------

Canary deployment complete
-----------------------------------------------------------------------------------  

Generate the Certificate for your Host
----------------------------------------------------------------------------------
 1. ./generate.sh <domain name> docmesh


            