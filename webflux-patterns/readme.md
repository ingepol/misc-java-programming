# WebFlux Patterns

Udemy course -> [Design Patterns With Spring WebFlux]

## Jar external services

The current project have a jar in order simulate a bunch of external microservices and we need to interact with them.

It is a spring boot application to run it, execute the above command:

> java -jar jar/external-services.jar

Default port is 7070, to change the port we can use `--server.port=6060` property.

Then open a browser the api swagger home page `http://localhost:7070/swagger-ui/#/`

When open home page we can see see all services that would be simulate and interact to apply the different patterns.

Also we can use other property to simulate delay response for example `--sec01.delay.response=3000`

## Integration Patterns

### Gateway Aggregator Pattern

**Scenario:**

The client or the browser application has to get information from several services to do its job or build a page.

**Problems:**

> - More network calls
> - Browser limit (6)
> - Increased latency
> - Complex aggregation logic on the frontend

**Solution:**

The aggregator is a separate microservices whose job is to receive the request from the client.
On the call all the upstream services collect the information and send it back to the client.
So it hides all backend complexity.

To see more about this pattern visit the vinsguru [blog](https://www.vinsguru.com/spring-webflux-aggregation/)

### Scatter Gather Pattern

**Scenario:**

It's like aggregator, but it broadcasts the message to all the recipients and then collects the responses.

> - Let’s consider an application in which we need to do a set of tasks to complete the business workflow.
> - If these tasks do not depend on each other, then it does not make sense to do them sequentially.
> - We can do these tasks in parallel.

The difference between Aggregator and Scatter Gather is the aggregator is an assemble.
That's mean the aggregator ask for a parts from upstream services and then build a response with all this parts.

- Example: assemble part carts and return a new cart.

And Scatter Gather ask for a complete object representation from upstream services and send the response to the
client.

- Example:  A flight booking application in which user searches for flight deals. The application sends the information
  to all the airlines, find their fares and then responds back.

**Use Cases:**

- Searching for data from multiple sources
- Divide the work and do parallel processing

To see more about this pattern visit the vinsguru [blog](https://www.vinsguru.com/scatter-gather-pattern/)

### Orchestrator Pattern (parallel workflow)

### Orchestrator Pattern (sequential workflow)

### Splitter Pattern

## Resilient Patterns

### Timeout Pattern

### Retry Pattern

### Circuit Breaker Pattern

### Rate Limiter Patter

### Bulkhead Pattern