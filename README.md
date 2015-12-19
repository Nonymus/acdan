A Cloud Doing Absolutly Nothing
========
A project using a bunch of technologies to build a cloud application, but significantly lacking any value for a user.

# Dependencies
- Java JDK 1.8
- Node.js
- npm
  - yo generator-angular generator-karma grunt-cli

# Build Order
1. ui - `cd ui && grunt`
2. ui-server - `cd ui-server && mvn clean package`

Build result from ui is packaged into ui-server, so be sure to keep the build order
