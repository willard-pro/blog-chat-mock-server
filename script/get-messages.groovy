import groovy.json.*

def userId = context.request.pathParams.userId

def userMessages = stores.open(userId).loadAll()
def jsonList = userMessages.collect { key, value -> ["id": key, "text": value] }

logger.info(userMessages);

respond()
    .withStatusCode(200)
    .withContent(JsonOutput.prettyPrint(JsonOutput.toJson(jsonList)))