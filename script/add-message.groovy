import groovy.json.*

def userMessage = new JsonSlurper().parseText(context.request.body);

def messageCount = stores.open("message_count");
def userMessageCount = messageCount.load(userMessage.userId);

if (!userMessageCount) {
	userMessageCount = "0";
} else {
	userMessageCount = String.valueOf(Integer.parseInt(userMessageCount) + 1);
}

messageCount.save(userMessage.userId, userMessageCount)

def userMessages = stores.open(userMessage.userId);
userMessages.save(userMessageCount, userMessage.text);

respond()
    .withStatusCode(200);