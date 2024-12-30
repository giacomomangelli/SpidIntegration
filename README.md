### HOW TO BUILD AND RUN BE

1. Run inside SpidIntegration folder:
    ```bash
       mvn clean package -DskipTests
    ```
2. Versions are: Java 21 and Maven 3.9.9
3. Start the main here in: SpidIntegration/src/main/java/it/activadigital/SpidIntegration/SpidIntegrationApplication.java;
4. If jdbc error occur check the application.yaml for the right database connection url;
5. For localhost running starting the main function is enough. Scripts are maid only for deploy env;

### How to build and RUN FE

1. Check node version. The right one are:
   1. ``` npm --version ``` is 10.2.4
   2. ``` node --version ``` is 18.19.1
   3. ``` ng --version ``` is 19.0.6
2. Run:
   ```bash
   npm install
    ```
3. Run:
   ```bash
   npm start
   ```
4. Check always the environment.ts file for the right configuration of the api url.

### HOW TO BUILD FOR DOCKER

1. Run inside SpidIntegration folder: 
    ```bash
       mvn clean package -DskipTests
    ```
2. Run in the current folder:
    ```bash
    docker compose build --no-cache
    ```
3. After build, also in the current folder, run:
    ```bash
    docker compose up -d
    ```

