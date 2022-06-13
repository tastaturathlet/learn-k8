# Learn K8 and Mircoservices

## docker on macOS

Quick guide for configuring minikube and docker on macOS, enabling docker to push images to minikube’s registry.

The first step is to enable the registry addon:
```bash
minikube addons enable registry
```
Note: Minikube will generate a port and request you use that port when enabling registry. That instruction is not related to this guide.

When enabled, the registry addon exposes its port 5000 on the minikube’s virtual machine.

In order to make docker accept pushing images to this registry, we have to redirect port 5000 on the docker virtual machine over to port 5000 on the minikube machine. We can (ab)use docker’s network configuration to instantiate a container on the docker’s host, and run socat there:

```bash
docker run --rm -it --network=host alpine ash -c "apk add socat && socat TCP-LISTEN:5000,reuseaddr,fork TCP:$(minikube ip):5000"
```
Once socat is running it’s possible to push images to the minikube registry:

docker tag my/image localhost:5000/myimage
docker push localhost:5000/myimage

After the image is pushed, refer to it by localhost:5000/{name} in kubectl specs.

Docs: 
- https://minikube.sigs.k8s.io/docs/handbook/registry/
- https://hasura.io/blog/sharing-a-local-registry-for-minikube-37c7240d0615/

## Deploy an Srping App
