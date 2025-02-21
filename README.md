# TDX25 - Heroku at Camp Mini Hacks - Code

[!IMPORTANT]
The actions in this repository are used in a **Salesforce TDX'25 Mini Hack for Agentforce**. They're here to help people who did the minihack challenge, and have access to the minihack org and its objects, apps, and agents. So, these examples won't work outside the minihack. Also, the steps below are just for reference and won't work unless you use them in an org that has the correct objects already deployed. If you want to explore Heroku in the context of Agentforce please check out this [tutorial](https://github.com/heroku-examples/heroku-agentforce-tutorial) and also further samples [here](https://github.com/heroku-examples/heroku-integration-patterns).

# Requirements
- Heroku login
- Heroku Integration Pilot enabled
- Heroku CLI installed
- Heroku Integration Pilot CLI plugin is installed
- Salesforce CLI installed
- Login information for one or more Scratch, Development or Sandbox orgs containing the TDX'25 Mini Hack apps
- Watch the [Introduction to the Heroku Integration Pilot for Developers](https://www.youtube.com/watch?v=T5kOGNuTCLE) video 

## Local Development and Testing

Code invoked from Salesforce requires specific HTTP headers to connect back to the invoking Salesforce org. Using the `invoke.sh` script supplied with this sample, it is possible to simulate requests from Salesforce with the correct headers, enabling you to develop and test locally before deploying to test from Apex, Flow, or Agentforce. This sample leverages the `sf` CLI to allow the `invoke.sh` script to access org authentication details. Run the following commands to locally authenticate, build and run the sample:

```
sf org login web --alias my-org
mvn clean install
mvn spring-boot:run
```

In a new terminal window run the following command substituting the Id values for valid **Contact** and **Vehicle Id** records from your Salesforce org.

```
./bin/invoke.sh my-org '/api/calculateFinanceAgreement' '{}'
```

You should see the following output:

```
Response from server:
{}
```

## Deploying and Testing from Apex and Flow

To test from Apex, Flow and other tools within your Salesforce org you must deploy the code and import it into your org. The following commands create a Heroku application and configure the Heroku Integration add-on. This add-on and associated buildpack allows secure authenticated access from within your code and visibility of your code from Apex, Flow and Agentforce. After this configuration, code is not accessible from the public internet, only from within an authorized Salesforce org.

```
heroku create
git push heroku main
```

Next install and configure the Heroku Integration add-on:

```
heroku addons:create heroku-integration
heroku buildpacks:add https://github.com/heroku/heroku-buildpack-heroku-integration-service-mesh
heroku salesforce:connect my-org --store-as-run-as-user
heroku salesforce:import api-docs.yaml --org-name my-org --client-name ActionsService
```
sf
Trigger an application rebuild to install the Heroku Integration buildpack

```
git commit --allow-empty -m "empty commit"
git push heroku main
```

Once imported grant permissions to users to invoke your code using the following `sf` command:

```
sf org assign permset --name ActionsService -o my-org
```

Deploy the Heroku application and confirm it has started.

```
git push heroku main
heroku logs
```

Navigate to your orgs **Setup** menu and search for **Heroku** then click **Apps** to confirm your application has been imported.

### Invoking from Apex

Now that you have imported your Heroku application. The following shows an Apex code fragment the demonstrates how to invoke your code in an synchronous manner (waits for response). Make sure to change the **Opportunity Id** `006am000006pS6P` below to a valid **Opportunity** from your org (see above).

```
echo \
"ExternalService.ActionsService service = new ExternalService.ActionsService();" \
"ExternalService.ActionsService.calculateFinanceAgreement_Request request = new ExternalService.ActionsService.calculateFinanceAgreement_Request();" \
"ExternalService.ActionsService_FinanceCalculationRequest body = new ExternalService.ActionsService_FinanceCalculationRequest();" \
"request.body = body;" \
"System.debug('Final Car Price: ' + service.calculateFinanceAgreement(request).Code200.recommendedFinanceOffer.finalCarPrice);" \
| sf apex run -o my-org
```

Inspect the debug log output sent to to the console and you should see the generated Quote ID output as follows:

```
07:56:11.212 (3213672014)|USER_DEBUG|[1]|DEBUG|...
```