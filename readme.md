## Getting Started

For instructions on importing your project into an IDE and configuring your development environment, please see the [getting started section](https://github.com/skyvers/skyve#detailed-instructions) of the Skyve readme on GitHub.

## Skyve Maven commands
These are standard maven goals using the Skyve maven plugin and can be run from the command line or from your IDE. The 6 main goals are described here:

### Generate Domain
`mvn skyve:generateDomain`
Generate domain validates and compiles the metadata (XML files) in your project and checks that the application domain is in a valid state. Errors for the developer to fix are written to your console, and if generate is successful, the domain will be compiled to produce Java domain files and unit tests.

### Generate Edit View
`mvn skyve:generateEditView`
Generate edit view requires to additional parameters, a `module` and `document` key value pair. If no edit.xml is specified for a document, Skyve will create a scaffolded view automatically using the attributes specified in the document. When customising a view, it is useful to start from the scaffolded view and extend it, this command will write a `generatedEdit.xml` file to the package specified by the module and document parameters.

### Generate Default Queries
`mvn skyve:generateDefaultQueries`
Similar to having a scaffolded edit view for new documents, when documents are shown in a list from a menu or in a lookupDescription, the /default query/ will be used which defines which columns are shown. This maven command can write out all the default queries to a file in the project root so any queries can be tweaked and included in your module.xml.

### Skyve Script
`mvn skyve:script`
This will look for a file called `skyve.md` inside a script directory in your project root. Any modules and documents found inside this file will be generated and added to your project. For more user feedback, this can also be performed via the UI from admin -> Document Creator.

### Update Resources
`mvn clean compile war:exploded`
Depending on how you configure your Wildfly, if you are not publishing changes during development into `wildfly/standalone/deployments`, you can use this maven command to update your local `/deployments/` directory with the compiled project. Your Wildfly deployment scanner can then be set to watch this location.

### Local Deploy
`man compile war:exploded skyve:touch`
This refreshes your project’s `/deployments’ directory and creates a ‘projectName.dodeploy’ file telling Wildfly to restart the module. This is used when there are any Java or module changes which are cannot be hot-reloaded.

### Add Module
```
mvn skyve:newModule
```
This will prompt you for the new module name, then create a new module directory and module.xml with the specified name. It will also update your customer.xml with the new module. Note: the new module will not pass generate domain, some required fields will be missing (such as the default view).

### Add Document
```
mvn skyve:newDocument
```
This will prompt you for a module name, and the new document name, then create the new document directory and document.xml in the correct location within your project structure. Note: the new document will not pass generate domain, some required fields will be missing.

## Updating Skyve version
To update your project with a specific Skyve version, see the [instructions in the readme](https://github.com/skyvers/skyve#updating-skyve-version) on GitHub.

## Deployment
Below are instruction on how to deploy your Skyve application.

### Docker
In order to use Docker, you will first need to install `docker` and `docker-compose`. Once you have done this all you need to do is build the project - `mvn compile war:exploded` and then run `docker-compose up` from the `docker` directory. 

If using Microsoft SQL Server, the first deployment will fail and you will need to run the `mssql_bootstrap.sql` file against the database container and then redeploy.

**Important Note:** If you are using Windows 10 Home, you will need to change the `SKYVE_HOSTNAME` variable in the `docker-compose.yaml` file from `localhost` to the IP address of the Wildfly container (see [this](https://blog.sixeyed.com/published-ports-on-windows-containers-dont-do-loopback/) post for reasons why).

### Manual Install
For a manual install, you will need to install Wildfly and your desired database server. Below are further instructions on how to configure your Wildfly install.

#### Configure the application server and database
To configure Wildfly to deploy your application:
* Copy the contents of the deployments folder to `wildfly/standalone/deployments/`
	* The deployments folder contains a data source file (`crmTest-ds.xml`) and a json instance settings file (`crmTest.json`).
* The datasource file declares the data source connection name to the nominated database engine. The JDBC connection string and associated credentials settings must be valid for the selected database engine. Skyve will create all required tables, so an empty database is required.
* The json settings file contains the settings specific to the application instance and includes credentials for a boostrap user to get your started.
	* You will need to configure the content directory path within your JSON to be a valid directory on your filesystem (i.e. manually create this directory if it does not exist, Skyve will not create this). This is where an uploaded files will be stored and indexed by Elastic Search.
* If you selected a database engine other than H2, you will need to create a schema (MySQL) or database name (MSSQL) matching your specified project name (crmTest). If you want to use a different database or schema name, you'll need to modify the `crmTest-ds.xml` file in the deployments folder accordingly.
* To deploy your application, right-click the Wildfly server node in the Eclipse server window and add your project. Then start the server using the start tool on the Server window toolbar.

### Security
See [here](https://github.com/skyvers/skyve#configuring-spring-security) for details on how to configure security.